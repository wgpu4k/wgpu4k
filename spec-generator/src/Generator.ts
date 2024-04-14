import {
    AbstractBase,
    Argument,
    AttributeMemberType,
    ConstantMemberType,
    ConstructorMemberType,
    DeclarationMemberType,
    DictionaryMemberType,
    DictionaryType,
    EnumType,
    FieldType,
    IDLInterfaceMemberType,
    IDLTypeDescription,
    InterfaceMixinType,
    OperationMemberType,
    TypedefType,
} from "webidl2";

import {EIDLType, ICreatorOperation, IDLTypes, MergedInterfaceType} from "./sorter";

////////////////////////////////////////////////////////////////////////////////

export const typeNames = {
    [EIDLType.Interface]: "Interface",
    [EIDLType.InterfaceMixin]: "Mixin",
    [EIDLType.Namespace]: "Namespace",
    [EIDLType.Callback]: "Callback",
    [EIDLType.Dictionary]: "Dictionary",
    [EIDLType.Enum]: "Enum",
    [EIDLType.Typedef]: "Typedef",
    [EIDLType.Includes]: "Includes",
};

export const categoryTitles = {
    [EIDLType.Interface]: "Interfaces",
    [EIDLType.InterfaceMixin]: "Mixins",
    [EIDLType.Namespace]: "Namespaces",
    [EIDLType.Callback]: "Callbacks",
    [EIDLType.Dictionary]: "Dictionaries",
    [EIDLType.Enum]: "Enums",
    [EIDLType.Typedef]: "Typedefs",
    [EIDLType.Includes]: "Includes",
};

export const folderTitles = {
    [EIDLType.Interface]: EIDLType.Interface,
    [EIDLType.InterfaceMixin]: "mixin",
    [EIDLType.Namespace]: EIDLType.Namespace,
    [EIDLType.Callback]: EIDLType.Callback,
    [EIDLType.Dictionary]: EIDLType.Dictionary,
    [EIDLType.Enum]: EIDLType.Enum,
    [EIDLType.Typedef]: EIDLType.Typedef,
    [EIDLType.Includes]: EIDLType.Includes,
};

export type MemberType =
    OperationMemberType |
    ConstructorMemberType |
    AttributeMemberType |
    ConstructorMemberType |
    DictionaryMemberType;

////////////////////////////////////////////////////////////////////////////////

export default class Generator
{
    readonly types: IDLTypes;
    readonly pathPrefix: string;

    constructor(types: IDLTypes, pathPrefix: string)
    {
        this.types = types;
        this.pathPrefix = pathPrefix;
    }

    getTableOfContents(): string
    {
        const html: string[] = [];
        html.push('<div class="idl-reference">');

        const keys = [
            EIDLType.Interface,
            EIDLType.Dictionary,
            EIDLType.Enum,
            EIDLType.Typedef,
        ];
 
        html.push(keys.map(key => this.getTocForType(key)).filter(html => !!html).join("\n"));
 
        html.push('</div>');
        return html.join("\n");
    }
    
    protected getTocForType(typeName: EIDLType): string
    {
        const typeList: any[] = this.types[typeName];
        const filteredList = typeList.filter(entry => !this.types.all[(entry.name || entry.target) + "Flags"]);
        if (filteredList.length === 0) {
            return "";
        }

        const html: string[] = [];
        html.push(`<div class="idl-${typeName}"><h2>${categoryTitles[typeName]}</h2></div>`);

        html.push('<ul class="idl-reference">');
        filteredList.forEach(entry => {
            const name = entry.name || entry.target;
            const ref =  this.getTypeRef(name);
            html.push(`<li id="idl-${name.toLowerCase()}" class="idl-reference"><a href="${ref}" _target="self">${name}</a></li>`);
        });
        html.push('</ul>');

        return html.join("\n");
    }

    protected getTypeRef(name: string)
    {
        const type = this.types.all[name];
        if (!type) {
            console.warn(`[Generator.getTypeRef] no type found for name '${name}'`);
            return "#";
        }

        const lcName = name.toLowerCase();
        return `${this.pathPrefix}${folderTitles[type.type]}/${lcName}/#idl-${lcName}`;
    }

    getSummary(node: AbstractBase, noDetails: boolean): string | null
    {
        switch(node.type) {
            case "interface":
                return this.getInterface(node as MergedInterfaceType, noDetails);
            case "interface mixin":
                return this.getMixin(node as InterfaceMixinType, noDetails);
            case "dictionary":
                return this.getDictionary(node as DictionaryType, noDetails);
            case "enum":
                return this.getEnum(node as EnumType);
            case "typedef":
                return this.getTypedef(node as TypedefType);
            default:
                return null;
        }
    }

    protected getInterface(node: MergedInterfaceType, noDetails: boolean)
    {
        const html: string[] = [];
        html.push('<div class="idl-interface">');
        html.push(`<h2>${node.name}</h2>`);
        
        const creators = this.types.creators[node.name];
        if (creators) {
            html.push('<ul class="idl-creators">');
            creators.forEach(creator => html.push(`<li>${this.getCreator(creator, noDetails)}</li>`));
            html.push('</ul>');
        }

        html.push(this.getMemberList(node, noDetails));

        html.push("</div>");
        return html.join("\n");
    }

    protected getMemberList(node: MergedInterfaceType | InterfaceMixinType, noDetails: boolean): string
    {
        const html: string[] = [];
        html.push('<ul>');

        node.members.forEach((member: IDLInterfaceMemberType) => {
            switch(member.type) {
                case "constructor":
                    html.push(`<li class="idl-line">${this.getConstructor(member)}</li>`);
                    break;
                case "operation":
                    html.push(`<li class="idl-line">${this.getOperation(member, noDetails)}</li>`);
                    break;
                case "attribute":
                    html.push(`<li class="idl-line">${this.getAttribute(member)}</li>`);
                    break;
                case "const":
                    html.push(`<li class="idl-line">${this.getConst(member)}</li>`);
                    break;
                case "setlike":
                case "maplike":
                case "iterable":
                    html.push(`<li class="idl-line">${this.getDeclaration(member)}</li>`);
                    break;
            }
        });
        html.push("</ul>");
        return html.join("\n");
    }

    protected getConstructor(member: ConstructorMemberType): string
    {
        const html: string[] = [];
    
        const args = member.arguments.map(arg => `${arg.name}: ${this.getType(arg.idlType)}`);
        const name = member.parent.name;
        const ref = this.getTypeRef(name);
        const ret = `<a href="${ref}" class="idl-type">${name}</a>`;

        html.push('<div class="idl-member idl-constructor">');
        html.push(`<span class="idl-name">Constructor</span>(${args.join(", ")}): ${ret}</span>`);
        html.push('</div>');
        return html.join("\n");
    }

    protected getCreator(creator: ICreatorOperation, noDetails: boolean): string
    {
        const typeName = creator.type.name;
        const args = this.getArguments(creator.operation.arguments);
        const ret = this.getType(creator.operation.idlType);
        
        const ref = this.getTypeRef(typeName);
        let content = `<a href="${ref}" class="idl-type idl-${creator.type.type}">${typeName}</a>`;
        content += `.<span class="idl-name">${creator.operation.name}</span>(${args}): ${ret}`

        if (noDetails) {
            return `<div class="idl-creator">${content}</div>`;
        }

        const argTypes = creator.operation.arguments.map(arg => arg.idlType);

        let detail:string = undefined;
        if (!noDetails) {
            detail = this.getDetail(argTypes);
        }
        if (!detail) {
            return `<div class="idl-creator">${content}</div>`;
        }

        return `<details><summary class="idl-creator">${content}</summary><div class="idl-detail-block">${detail}</div></details>`;
    }

    protected getOperation(member: OperationMemberType, noDetails: boolean): string
    {
        const args = this.getArguments(member.arguments);
        const ret = this.getType(member.idlType);
        const content = `<span class="idl-name">${member.name}</span>(${args}): ${ret}`;

        if (noDetails) {
            return `<span class="idl-member idl-operation">${content}</span>`;
        }

        const argTypes = member.arguments.map(arg => arg.idlType);

        let detail:string = undefined;
        if (!noDetails) {
            detail = this.getDetail(argTypes);
        }
        if (!detail) {
            return `<div class="idl-member idl-operation">${content}</div>`;
        }

        return `<details><summary class="idl-member idl-operation">${content}</summary><div class="idl-detail-block">${detail}</div></details>`;
    }

    protected getDetail(idlTypes: IDLTypeDescription[]): string
    {
        // create a set with the names of all types with detail available
        const nameSet = new Set<string>();
        idlTypes.map(idlType => this.getTypeNamesRecursive(idlType))
            .flat()
            .filter(name => !!name && !!this.types.all[name])
            .forEach(name => nameSet.add(name));

        const types = Array.from(nameSet.values()).map(name => this.types.all[name]);
        if (types.length === 0) {
            return "";
        }

        return types.map(type => `<div class="idl-detail">${this.getSummary(type, true)}</div>`).join("\n");
    }

    protected getArguments(args: Argument[]): string
    {
        return args.map(arg => `<span class="idl-var">${arg.name}</span>: ${this.getType(arg.idlType)}`).join(", ");
    }

    protected getAttribute(member: AttributeMemberType): string
    {
        return `<div class="idl-member idl-attribute"><span class="idl-name">${member.name}</span>: ${this.getType(member.idlType)}</div>`;
    }

    protected getConst(member: ConstantMemberType): string
    {
        return `<span class="idl-member idl-const"><span class="idl-name">${member.name}</span> = ${member.value["value"]}</span>`;
    }

    protected getDeclaration(member: DeclarationMemberType): string
    {
        const typeArgs = member.idlType.map(t => this.getType(t));

        return `<div class="idl-member idl-declaration"><span class="idl-name">${member.type}</span>&lt;${typeArgs}&gt;</div>`;
    }

    protected getField(member: FieldType, noDetails = false): string
    {
        const html: string[] = [];
        const defaultDesc = member.default as any;
        let defaultValue: any = undefined;
        if (defaultDesc) {
            if (defaultDesc.type === "boolean") {
                defaultValue = `'${defaultDesc.value}'`;
            }
            else if (defaultDesc.type === "string") {
                defaultValue = `'${defaultDesc.value}'`;
            }
            else if (defaultDesc.type === "dictionary") {
                defaultValue = "{}";
            }
            else if (defaultDesc.type === "sequence") {
                defaultValue = "[]";
            }
            else {
                defaultValue = defaultDesc.value ?? defaultDesc.type;
            }
        }

        //html.push('<div class="idl-member idl-field">');
        html.push(`<span class="idl-name">${member.name}</span>: ${this.getType(member.idlType)}`);
        html.push(member.required ? '<span class="idl-required">required</span>' : '');
        html.push(defaultValue ? `<span class="idl-default">= ${defaultValue}</span>` : '');
        //html.push('</div>');

        const content = html.join("");

        let detail:string = undefined;
        if (!noDetails) {
            detail = this.getDetail([ member.idlType ]);
        }
        if (!detail) {
            return `<div class="idl-member idl-field">${content}</div>`;
        }

        return `<details><summary class="idl-member idl-field">${content}</summary><div class="idl-detail-block">${detail}</div></details>`;
    }

    protected getTypeNamesRecursive(idlType: IDLTypeDescription): string[]
    {
        if (Array.isArray(idlType.idlType)) {
            return idlType.idlType.map(t => this.getTypeNamesRecursive(t)).flat();
        }

        if (idlType.generic) {
            return this.getTypeNamesRecursive(idlType.idlType);
        }

        const typeName = idlType.idlType as string;
        const type = this.types.all[typeName];

        if (!type) {
            return [];
        }

        if (type.type === "typedef") {
            return [ typeName, ...this.getTypeNamesRecursive(type.idlType) ];
        }
        if (type.type === "dictionary") {
            const members = type.members.filter(m => this.types.all[m.idlType.idlType as string]?.type !== "interface");
            return [ typeName, ...members.map(m => this.getTypeNamesRecursive(m.idlType)).flat()];
        }

        return [ typeName ];
    }

    protected getType(idlType: IDLTypeDescription): string
    {
        let name: string;
        let isArray = false;
        const innerType = idlType.idlType;

        if (Array.isArray(innerType)) {
            isArray = true;
            const separator = idlType.union ? " | " : ", ";
            name = innerType.map(t => this.getType(t)).join(separator);
        }
        else if (typeof innerType === "string") {
            name = innerType;
        }
        else {
            name = this.getType(innerType);
        }

        if (name === "undefined") {
            name = "void";
        }

        if (idlType.generic) {
            return `<span class="idl-type">${idlType.generic}</span>&lt;${name}&gt;`;
        }

        const type = this.types.all[name];
        if (type) {
            if (type.type === "interface") {
                const ref = this.getTypeRef(name);
                return `<a href="${ref}" class="idl-type idl-${type.type}">${name}</a>`;
            }
            else {
                return `<span class="idl-type idl-${type.type}">${name}</span>`;
            }    
        }
    
        return isArray ? name : `<span class="idl-type idl-unknown">${name}</span>`;
    }

    protected getMixin(node: InterfaceMixinType, noDetails = false): string
    {
        const html: string[] = [];
        html.push('<div class="idl-mixin">');
        html.push(`<h2>${node.name}</h2>`);
        
        html.push(this.getMemberList(node, noDetails));

        html.push("</div>");
        return html.join("\n");
    }

    protected getDictionary(node: DictionaryType, noDetails = false): string
    {
        const html: string[] = [];
        html.push('<div class="idl-dictionary">');
        html.push(`<h2>${node.name}</h2>`);
        if (node.members.length > 0) {
            html.push('<ul>');
            node.members.forEach(m => html.push(`<li class="idl-line">${this.getField(m, noDetails)}</li>`));
            html.push("</ul>");    
        }
        else {
            html.push('<div class="idl-line">(no members)</div>');
        }
        html.push('</div>');
        return html.join("\n");
    }

    protected getEnum(node: EnumType): string
    {
        const html: string[] = [];
        html.push('<div class="idl-enum">');
        html.push(`<h2>${node.name}</h2>`);
        html.push(this.getEnumValueString(node));
        html.push('</div>');

        return html.join("\n");
    }

    protected getEnumValueList(node: EnumType): string
    {
        const html: string[] = [];
        html.push('<ul class="idl-enum-values">');
        node.values.forEach(v => html.push(`<li class="idl-line idl-enum-value">${v.value}</li>`));
        html.push('</ul>');

        return html.join("\n");
    }

    protected getEnumValueString(node: EnumType): string
    {
        const html: string[] = [];
        html.push('<div class="idl-line idl-enum-values">');
        html.push(node.values.map(v => `'${v.value}'`).join(", "));
        html.push('</div>');
        return html.join("\n");
    }

    protected getTypedef(node: TypedefType): string
    {
        const html: string[] = [];
        html.push('<div class="idl-typedef">');
        html.push(`<h2>${node.name}</h2>`);

        html.push(`<div class="idl-line"><div class="idl-member"><span class="idl-keyword">typedef</span> <span class="idl-type idl-typedef">${node.name}</span> = ${this.getType(node.idlType)}</div></div>`);

        if (node.name.endsWith("Flags")) {
            const flagTypeName = node.name.substring(0, node.name.length - 5);
            const flagNode = this.types.all[flagTypeName];

            if (flagNode && flagNode.type === "namespace") {
                html.push('<ul>');
        
                flagNode.members.forEach((member: any) => {
                    if (member.type === "const") {
                        html.push(`<li class="idl-line"><div class="idl-member idl-flag"><span class="idl-type idl-typedef">${flagTypeName}</span>.${this.getConst(member as any)}</div></li>`);
                    }
                });

                html.push("</ul>");
            }
        }

        html.push('</div>');
        return html.join("\n");
    }

    protected getTypeName(node: AbstractBase, link: boolean)
    {
        const typeName = node["name"] || node["target"];
        if (!typeName) {
            throw new Error("failed to retrieve type name");
        }

        if (link) {
            const ref = this.getTypeRef(typeName);
            return `<a href="${ref}" class="idl-type">${typeName}</a>`;
        }

        return `<span class="idl-type">${typeName}</span>`;
    }
}
