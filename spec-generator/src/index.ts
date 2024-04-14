import * as sourceMapSupport from "source-map-support";
sourceMapSupport.install();

import * as fs from "fs";
import * as path from "path";
import * as mkdirp from "mkdirp";

import { parseBikeShedFile } from "./parser";
import { sort } from "./sorter";
import Generator, { folderTitles } from "./Generator";

////////////////////////////////////////////////////////////////////////////////

function prettyPrint(filePath: string, data: any)
{
    const json = JSON.stringify(data, null, 4);
    fs.writeFileSync(filePath, json);
}

(async function main() {
    const specPath = path.resolve(__dirname, "../libs/gpuweb/spec/index.bs")
    const blocks = await parseBikeShedFile(specPath);

    const idlBlocks = Array.from(blocks.idl);
    const dfnBlocks = Array.from(blocks.dfn);
    //const commentDefinitions = processComments(dfnBlocks);

    const rootPath = path.resolve(__dirname, "../..");
    mkdirp.sync(rootPath);
    console.log(blocks);
    prettyPrint(path.resolve(rootPath, "specifications.json"), {
        idl: idlBlocks,
        dfn: dfnBlocks,
        //comments: commentDefinitions,
        exposed: Array.from(blocks.exposed.values()),
    });

    const idlTypes = sort(blocks.idl);
    const generator = new Generator(idlTypes, "/reference/");

    const contentTypesPath = path.resolve(__dirname, "../content/reference");
    mkdirp.sync(contentTypesPath);
    const allKeys = Object.keys(idlTypes.all);

    // create and write table of contentse
    const html = generator.getTableOfContents();
    const frontMatter = "---\nheadless: true\n---\n\n";
    const basePath = path.resolve(contentTypesPath, "toc");
    mkdirp.sync(basePath);
    fs.writeFileSync(path.resolve(basePath, "toc.html"), frontMatter + html);

    // create all reference pages
    allKeys.forEach(key => {
        const node = idlTypes.all[key];
        const html = generator.getSummary(node, false);
        if (html) {
            const typeFolder = folderTitles[node.type];
            const name = node["name"];
            const frontMatter = `---\ntitle: ${key}\n---\n`;
            const content = [ frontMatter, html ].join("\n");

            const basePath = path.resolve(contentTypesPath, typeFolder);
            mkdirp.sync(basePath);

            const filePath = path.resolve(basePath, name + ".html");
            fs.writeFileSync(filePath, content);
        }
    });

})();

