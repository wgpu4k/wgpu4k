# Sécurisation des callbacks natifs hors Android

## Contexte

`wgpu4k` utilise actuellement `wgpu4k-native` v27 et construit plusieurs callbacks dans un `memoryScope` fermé dès le retour de l'appel natif. Cette durée de vie est insuffisante pour les callbacks différés. De plus, les opérations qui dépendent d'un poll de `wgpu-native` ne disposent pas d'un mécanisme commun garantissant leur progression.

`wgpu4k-native` v29 corrige la couche FFI avec `CallbackRegistration`, des politiques `ONCE` et `REPEATING`, un routage unique des userdata et des trampolines dont la durée de vie n'est plus liée au scope d'appel. `wgpu4k` doit maintenant adopter cette API et prendre en charge la progression, l'annulation et la fermeture au niveau Kotlin.

## Objectifs

- Utiliser `io.ygdrasil:wgpu4k-native:v29.0.0-SNAPSHOT` depuis le dépôt Sonatype Central snapshots.
- Migrer les usages FFI hors Android vers les packages et signatures de v29.
- Sécuriser tous les callbacks natifs utilisés par le source set `commonNativeMain`.
- Définir explicitement le `WGPUCallbackMode` de chaque callback ponctuel.
- Faire progresser `Queue.onSubmittedWorkDone()` et `Buffer.mapAsync()` sans bloquer le render thread.
- Propager correctement l'annulation des coroutines.
- Garantir une terminaison exactement une fois, y compris pendant une fermeture concurrente.
- Libérer toutes les registrations et références natives selon un ownership explicite.
- Ajouter des tests déterministes qui ne nécessitent pas de GPU.

## Hors périmètre

- Toute correction dans `androidMain`.
- La compilation, l'exécution ou la validation de tâches Android.
- L'implémentation manquante de fonctions dans `wgpu-native` v29 lui-même.
- Une modification incompatible des interfaces WebGPU publiques de `wgpu4k`.

Les changements dans `commonNativeMain` sont partagés techniquement avec Android, mais aucune adaptation spécifique à Android ne sera réalisée ni validée dans ce travail.

## Dépendances et résolution Gradle

La version du catalogue `wgpu4k-native` passe de `v27.0.4` à `v29.0.0-SNAPSHOT`. Le coordinate multiplateforme existant `io.ygdrasil:wgpu4k-native` est conservé afin que Gradle sélectionne le variant de la plateforme cible.

Le dépôt suivant est ajouté aux repositories de dépendances du build principal et du build inclus `generator`, car celui-ci résout aussi `wgpu4k-native-specs-jvm` avec la même version :

```kotlin
maven("https://central.sonatype.com/repository/maven-snapshots/")
```

L'ancien repository GitLab peut rester tant que d'autres artifacts en dépendent. Cette correction ne comprend pas un nettoyage général des repositories.

## Architecture retenue

### Répartition des responsabilités

`wgpu4k-native` est responsable de la sûreté de la frontière FFI : trampoline stable, userdata unique, lookup thread-safe et fermeture idempotente de `CallbackRegistration`.

`wgpu4k` est responsable du cycle de vie applicatif : choix du callback mode, progression des événements, intégration avec les coroutines, fermeture des resources et traduction des statuts natifs en `Result`.

### `DeviceEventPump`

Chaque `Device` natif possède un unique `DeviceEventPump` interne. Il coordonne toutes les opérations différées rattachées à ce device.

Le pump possède :

- un scope de coroutine basé sur `SupervisorJob` et `Dispatchers.Default` ;
- une file de commandes sérialisant l'ajout, la terminaison et l'annulation des opérations ;
- l'ensemble des opérations encore en attente ;
- une fonction de poll injectée, afin de tester le composant sans GPU ;
- une référence native supplémentaire au `WGPUDevice` pendant toute sa durée de vie.

Lorsqu'aucune opération n'est en attente, la coroutine reste suspendue et aucun poll n'est exécuté. Dès qu'une première opération est enregistrée, le pump appelle périodiquement :

```kotlin
wgpuDevicePoll(device, false, null)
```

Le poll est non bloquant. Tant qu'une opération reste en attente après un passage, le pump exécute `delay(1)` avant le suivant afin d'éviter une busy loop (boucle active consommant un cœur CPU). Toutes les opérations d'un même device partagent ce pump ; aucun poll concurrent n'est autorisé sur le même handle.

Le `Queue` reçoit une référence interne vers son `Device`. `Buffer` possède déjà cette référence. Ces objets utilisent le pump sans exposer ce détail dans l'API publique.

### Référence native du pump

Le pump appelle `wgpuDeviceAddRef` lors de sa création et conserve cette référence jusqu'à son arrêt définitif. `Device.close()` peut alors libérer la référence principale immédiatement sans créer de use-after-free pendant un dernier poll. Le pump libère sa référence avec `wgpuDeviceRelease` dans son bloc de terminaison, même en cas d'exception ou d'annulation.

## Cycle de vie d'une opération ponctuelle

Une opération différée suit cet ordre :

1. Demander un ticket au `DeviceEventPump`. L'acceptation et la vérification de l'état fermé forment une seule transition atomique ; une fermeture concurrente ne peut donc pas laisser une opération non suivie.
2. Créer une `CallbackRegistration` avec `CallbackPolicy.ONCE`.
3. Enregistrer une opération auprès du `DeviceEventPump` avant l'appel natif.
4. Construire le callback info dans un `memoryScope`. Le callback info peut être temporaire car il est transmis par valeur ; la registration et ses userdata restent vivantes indépendamment du scope.
5. Appeler la fonction native.
6. Au callback, convertir immédiatement les données natives nécessaires, puis tenter la reprise avec `tryResume` et `completeResume`.
7. Fermer idempotemment la registration et retirer l'opération du pump, quel que soit le chemin de sortie.

L'annulation de la coroutine ferme la registration et retire l'opération. Un callback natif tardif ne trouve plus sa userdata dans le registry de v29 et est ignoré. Une exception Kotlin ne doit jamais traverser le callback FFI.

Si un callback fournit une nouvelle resource native, mais que la continuation a déjà été annulée ou fermée, le handle reçu est libéré au lieu d'être abandonné.

## Fermeture

`Device.close()` devient idempotent et effectue les actions suivantes :

1. Marquer le device comme fermé afin de refuser immédiatement toute nouvelle attente.
2. Terminer les opérations en cours avec `Result.failure(IllegalStateException("Device is closed"))`.
3. Fermer la registration `REPEATING` de `onUncapturedError`, si elle existe.
4. Demander l'arrêt du `DeviceEventPump`.
5. Libérer exactement une fois la référence native principale.

Le pump traite sa terminaison, cesse les polls, puis libère sa référence supplémentaire.

`Buffer.close()` devient également idempotent. Une opération `mapAsync` propre à ce buffer encore en attente est terminée avec `Result.failure(IllegalStateException("Buffer is closed"))` avant la libération du handle. La fermeture du device termine aussi cette opération par l'intermédiaire du ticket enregistré auprès du pump.

## Modes et stratégie par callback

| API | Registration | Mode | Progression | Ownership |
|---|---|---|---|---|
| `Queue.onSubmittedWorkDone()` | `ONCE` | `AllowProcessEvents` | `DeviceEventPump` | opération suspendue |
| `Buffer.mapAsync()` | `ONCE` | `AllowProcessEvents` | `DeviceEventPump` | opération suspendue et buffer |
| `Adapter.requestDevice()` | `ONCE` | `AllowSpontaneous` | callback inline/spontané de v29 | opération suspendue |
| `Device.popErrorScope()` | `ONCE` | `AllowSpontaneous` | callback inline/spontané de v29 | opération suspendue |
| `WGPU.requestAdapter()` | `ONCE` | `AllowSpontaneous` | callback inline de v29 | durée de l'appel synchrone |
| `onUncapturedError` | `REPEATING` | sans champ mode dans ce callback info | événements device | `Device` |

`AllowProcessEvents` est réservé aux opérations dont la progression est assurée explicitement par le pump. `AllowSpontaneous` est utilisé lorsque l'API publique ou l'implémentation v29 attend une livraison inline ou spontanée. La valeur reste explicite même si wgpu-native v29 ne l'exploite pas encore partout.

`WGPU.requestAdapter()` conserve sa signature synchrone actuelle. Cette décision dépend du comportement inline de wgpu-native v29 et évite une rupture d'API. Une évolution future de wgpu-native vers un callback strictement différé nécessitera une API suspendue distincte.

## Callback `onUncapturedError`

Le callback d'erreur non capturée ne doit plus utiliser une allocation globale non libérable. Le mapping du `GPUDeviceDescriptor` produit à la fois le descriptor natif temporaire et une registration `REPEATING` optionnelle.

Lors d'un `requestDevice` réussi, l'ownership de cette registration est transféré au nouveau `Device`. En cas d'erreur, d'annulation ou de device natif reçu trop tard, la registration est fermée par l'opération de requête. `Device.close()` la ferme après un succès.

Le callback utilisateur est protégé : une exception qu'il déclenche est rapportée par le mécanisme de gestion d'exceptions de callback de kffi et ne remonte jamais dans le code natif.

## Résultats, erreurs et annulation

- Un statut natif réussi produit `Result.success`.
- Un statut natif en erreur produit `Result.failure` avec le statut et le message natif copié avant le retour du callback.
- Une valeur native obligatoire absente produit `Result.failure(IllegalStateException(...))`.
- Une fermeture de resource produit `Result.failure(IllegalStateException("<Resource> is closed"))`.
- L'annulation du `Job` propage `CancellationException` normalement ; elle n'est jamais encapsulée dans `Result.failure`.
- Les courses callback/annulation/fermeture sont arbitrées par une terminaison atomique et `tryResume`. Un seul chemin peut publier le résultat.

Aucun nouveau type d'exception public n'est ajouté dans ce changement.

## APIs asynchrones non implémentées dans wgpu-native v29

Les appels natifs `wgpuDeviceCreateComputePipelineAsync`, `wgpuDeviceCreateRenderPipelineAsync` et `wgpuShaderModuleGetCompilationInfo` sont actuellement non implémentés dans wgpu-native v29. Ils ne doivent pas être invoqués.

`createComputePipelineAsync()` et `createRenderPipelineAsync()` utilisent temporairement les fonctions synchrones correspondantes sur `Dispatchers.Default`. La coroutine reste annulable. Si une pipeline est créée après l'annulation mais ne peut plus être livrée, elle est fermée immédiatement. Les exceptions autres que `CancellationException` sont converties en `Result.failure`.

`getCompilationInfo()` retourne immédiatement :

```kotlin
Result.failure(
    UnsupportedOperationException(
        "Shader compilation info is not supported by wgpu-native 29.0.0.0"
    )
)
```

Cette erreur contrôlée remplace un appel natif qui provoquerait un panic.

## `GPUDevice.poll()`

L'extension publique ne doit plus exécuter directement un `wgpuDevicePoll(..., true, ...)` sur le thread appelant. Elle délègue à la même attente asynchrone que `queue.onSubmittedWorkDone()`, dont la progression est assurée par le pump non bloquant. Elle conserve ainsi son rôle d'attente de fin des soumissions sans bloquer le render thread.

## Migration source vers v29

La migration comprend les changements mécaniques nécessaires à la compilation hors Android :

- remplacement des imports `ffi.*` par `io.ygdrasil.kffi.*` ;
- adoption des nouvelles signatures de callback, notamment les `WGPUStringView` et userdata ;
- utilisation des factories de callback info v29 avec `CallbackRegistration` ;
- adaptation de tous les changements de types générés signalés par les compilations JVM et Kotlin/Native hôte, limitée aux sources hors Android.

Les fichiers `androidMain` ne sont pas modifiés. Une erreur propre au backend Android découverte pendant la migration est documentée mais non corrigée dans ce périmètre.

## Tests

### Tests unitaires sans GPU

Le `DeviceEventPump` accepte des fonctions injectées pour poll, retain et release. Les tests JVM utilisent des fakes et couvrent :

- aucun poll quand le pump est idle ;
- démarrage au premier callback différé ;
- partage d'un seul pump par plusieurs opérations simultanées ;
- sérialisation des polls ;
- arrêt quand la dernière opération se termine ;
- reprise exactement une fois ;
- callback réellement différé ;
- annulation avant callback ;
- callback tardif après annulation ;
- fermeture du device pendant l'attente ;
- refus d'une nouvelle opération après fermeture ;
- fermeture d'un buffer pendant `mapAsync` ;
- retain/release exactement une fois pour la référence du pump ;
- plusieurs notifications `onUncapturedError`, puis aucune après fermeture.

Les helpers de coroutine couvrent également la conservation de `CancellationException` et la libération d'une resource native produite après annulation.

### Tests des fallbacks

- succès et erreur de la création synchrone d'une compute pipeline ;
- succès et erreur de la création synchrone d'une render pipeline ;
- fermeture d'une pipeline produite après annulation ;
- `getCompilationInfo()` retourne `UnsupportedOperationException` sans appel natif.

### Vérification hors Android

La validation cible au minimum :

- les tests JVM du module `wgpu4k` ;
- la compilation JVM ;
- la compilation Kotlin/Native macOS du host ;
- les tests Kotlin/Native hôte qui ne nécessitent pas de GPU, si leur tâche est disponible.

Aucune tâche Android ne fait partie des critères de succès.

## Critères d'acceptation

- Le projet résout `wgpu4k-native` v29 depuis Sonatype snapshots.
- Les sources hors Android concernées compilent avec kffi v29.
- Aucun callback ponctuel n'est alloué dans un scope dont dépend sa durée de vie.
- Chaque callback info ponctuel possède un mode explicite.
- `onSubmittedWorkDone()` et `mapAsync()` progressent sans appel bloquant sur le render thread.
- Une annulation, une fermeture et un callback concurrent ne reprennent jamais une continuation plus d'une fois.
- Les callbacks tardifs sont sans effet et n'accèdent pas à des userdata libérées.
- Les registrations ponctuelles, répétées et les références natives du pump sont libérées.
- Les fonctions non implémentées de wgpu-native v29 ne sont pas appelées.
- Les tests et compilations hors Android définis ci-dessus réussissent.
