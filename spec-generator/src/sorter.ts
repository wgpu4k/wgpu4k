import {
    IDLRootType,
    InterfaceType,
    InterfaceMixinType,
    NamespaceType,
    CallbackType,
    DictionaryType,
    EnumType,
    TypedefType,
    IncludesType,
    OperationMemberType,
} from "webidl2";

////////////////////////////////////////////////////////////////////////////////

export type MergedInterfaceType = InterfaceType & {
    includes?: string[];
};

export enum EIDLType
{
    Interface = "interface",
    InterfaceMixin = "interface mixin",
    Namespace = "namespace",
    Callback = "callback",
    Dictionary = "dictionary",
    Enum = "enum",
    Typedef = "typedef",
    Includes = "includes",
}

export const idlTypeKeys: EIDLType[] = [
    EIDLType.Interface,
    EIDLType.InterfaceMixin,
    EIDLType.Namespace,
    EIDLType.Callback,
    EIDLType.Dictionary,
    EIDLType.Enum,
    EIDLType.Typedef,
    EIDLType.Includes,
];

export interface ICreatorOperation
{
    type: MergedInterfaceType | InterfaceMixinType;
    operation: OperationMemberType;
}

export interface IDLTypes
{
    all: Record<string, IDLRootType>;
    creators: Record<string, ICreatorOperation[]>;
    [EIDLType.Interface]: MergedInterfaceType[];
    [EIDLType.InterfaceMixin]: InterfaceMixinType[];
    [EIDLType.Namespace]: NamespaceType[];
    [EIDLType.Callback]: CallbackType[];
    [EIDLType.Dictionary]: DictionaryType[];
    [EIDLType.Enum]: EnumType[];
    [EIDLType.Typedef]: TypedefType[];
    [EIDLType.Includes]: IncludesType[];
}

export function sort(nodes: Map<string, IDLRootType[]>): IDLTypes
{
    const idlTypes: IDLTypes = {
        all: {},
        creators: {},
        [EIDLType.Interface]: [],
        [EIDLType.InterfaceMixin]: [],
        [EIDLType.Namespace]: [],
        [EIDLType.Callback]: [],
        [EIDLType.Dictionary]: [],
        [EIDLType.Enum]: [],
        [EIDLType.Typedef]: [],
        [EIDLType.Includes]: [],
    };

    nodes.forEach((node, key) => idlTypes.all[key] = mergePartialInterfaces(key, node));

    const typeNames = Object.keys(idlTypes.all);

    // add each type to its category
    typeNames.forEach(typeName => {
        const idlType = idlTypes.all[typeName];
        idlTypes[idlType.type].push(idlType);
    });

    mergeMixins(idlTypes);
    mergeInherited(idlTypes);

    // collect creator functions
    typeNames.forEach(typeName => {
        const idlType = idlTypes.all[typeName];

        if (idlType.type === EIDLType.Interface || idlType.type === EIDLType.InterfaceMixin) {
            const iface = idlType as MergedInterfaceType | InterfaceMixinType;
            const ops = iface.members.filter(member => member.type === "operation") as OperationMemberType[];
            ops.forEach(op => {
                const retType = op.idlType;
                const retTypeName = (retType.generic ? retType.idlType[0].idlType : retType.idlType) as string;
                const retTypeObj = idlTypes.all[retTypeName];

                if (retTypeObj) {
                    const creators = idlTypes.creators[retTypeName]
                        || (idlTypes.creators[retTypeName] = []);
                    creators.push({ type: iface, operation: op });
                }
            });

            // sort interface members by type
            iface.members.sort((a, b) => {
                const typeA = a.type;
                const typeB = b.type;
                return typeA < typeB ? -1 : (typeA > typeB ? 1 : 0);
            });
        }
    });

    idlTypeKeys.forEach(key => {
        // for each category, sort types by name
        idlTypes[key].sort((a, b) => {
            const nameA = a.name || a.target;
            const nameB = b.name || b.target;
            return nameA < nameB ? -1 : (nameA > nameB ? 1 : 0);
        });

        console.log(`[sorter] ${key}: ${idlTypes[key].length} items`);
    });

    return idlTypes;
}

function mergePartialInterfaces(name: string, nodes: IDLRootType[]): IDLRootType
{
    if (nodes.length === 1) {
        return nodes[0];
    }

    const iface = nodes.find(node => node.type === "interface" && node.partial === false) as MergedInterfaceType;
    if (!iface) {
        console.log(nodes);
        throw Error(`no non-partial interface node found for '${name}'`);
    }

    nodes.forEach(node => {
        if (node === iface) {
            return;
        }

        switch(node.type) {
            case "interface":
                if (node.inheritance) {
                    console.warn(`partial interface '${node.name}' with inheritance from '${node.inheritance}'`);
                }
                iface.members = iface.members.concat(node.members);
                iface.extAttrs = iface.extAttrs.concat(node.extAttrs);
                break;
            case "includes":
                iface.includes = iface.includes || [];
                iface.includes.push(node.includes);
                break;
        }
    });

    return iface;
}

function mergeMixins(idlTypes: IDLTypes)
{
    const interfaces = idlTypes[EIDLType.Interface];

    interfaces.forEach(iface => {
        iface.includes?.forEach(include => {
            const mixin = idlTypes.all[include];
            if (mixin && mixin.type === EIDLType.InterfaceMixin) {
                iface.members = mixin.members.concat(iface.members as any[]);
                iface.extAttrs = mixin.extAttrs.concat(iface.extAttrs);
            }
        });
    });
}

function mergeInherited(idlTypes: IDLTypes)
{
    const ifaces = idlTypes[EIDLType.Interface];
    const dicts = idlTypes[EIDLType.Dictionary];

    const list: (MergedInterfaceType | DictionaryType)[] = ifaces.concat(dicts as any[]);

    list.forEach(item => {
        if (item.inheritance) {
            const base = idlTypes.all[item.inheritance] as MergedInterfaceType;
            if (base) {
                item.members = base.members.concat(item.members as any[]);
            }
        }
    });
}
