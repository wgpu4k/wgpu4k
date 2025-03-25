# Start with the official Ubuntu base image
FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y git unzip curl zip mesa-common-dev gdebi-core xvfb libasound2t64 libglfw3-dev libxtst6 weston \
    libwayland-cursor0 libxcursor1 adwaita-icon-theme hicolor-icon-theme && \
    apt-get clean;

COPY chrome-128.deb /tmp/
RUN gdebi -n /tmp/chrome-128.deb
RUN rm /tmp/chrome-128.deb

# Créer le script wayland-run
RUN echo '#!/bin/bash\n\
WAYLAND_SOCK="wayland-test-$$"\n\
WAYLAND_PATH="/tmp/$WAYLAND_SOCK"\n\
\n\
# Démarrer Weston en arrière-plan\n\
weston --backend=headless-backend.so --socket="$WAYLAND_SOCK" --width=1024 --height=768 &\n\
WESTON_PID=$!\n\
\n\
# Attendre que le socket soit créé\n\
while [ ! -S "$WAYLAND_PATH" ]; do\n\
  sleep 0.1\n\
done\n\
\n\
# Exécuter la commande avec Wayland\n\
WAYLAND_DISPLAY="$WAYLAND_SOCK" "$@"\n\
RESULT=$?\n\
\n\
# Arrêter Weston\n\
kill $WESTON_PID\n\
\n\
exit $RESULT\n'\
> /usr/local/bin/wayland-run && chmod +x /usr/local/bin/wayland-run

ARG CANDIDATE=java
ARG CANDIDATE_VERSION=22-open

ENV SDKMAN_DIR=/home/user/.sdkman

# Créer un fichier de configuration Weston minimal
USER root
RUN mkdir -p /etc/xdg/weston && \
    echo "[core]\ncursor-theme=Adwaita\ncursor-size=24\n\n[shell]\nlocking=false\n" > /etc/xdg/weston/weston.ini


RUN useradd -ms /bin/bash user
USER user
WORKDIR /home/user

RUN curl -s "https://get.sdkman.io" | bash && \
    echo "sdkman_auto_answer=true" > $SDKMAN_DIR/etc/config && \
    echo "sdkman_auto_selfupdate=false" >> $SDKMAN_DIR/etc/config

# Source sdkman to make the sdk command available and install candidate
RUN bash -c "source $SDKMAN_DIR/bin/sdkman-init.sh && sdk install $CANDIDATE $CANDIDATE_VERSION"

# Add candidate path to $PATH environment variable
ENV JAVA_HOME="$SDKMAN_DIR/candidates/java/current"
ENV PATH="$JAVA_HOME/bin:$PATH"

# Créer un fichier d'alias pour l'utilisateur
RUN echo "alias wayland-run='/usr/local/bin/wayland-run'" >> /home/user/.bashrc
RUN mkdir /tmp/user-runtime-dir
RUN echo "export XDG_RUNTIME_DIR=/tmp/user-runtime-dir" >> ~/.bashrc

# Créer le répertoire pour les thèmes personnels
RUN mkdir -p /home/user/.icons && \
    mkdir -p /home/user/.local/share/icons

ENV DISPLAY :1

ENTRYPOINT ["/bin/bash", "-c", "source $SDKMAN_DIR/bin/sdkman-init.sh && \"$@\"", "-s"]