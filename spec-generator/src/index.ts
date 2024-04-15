import * as sourceMapSupport from "source-map-support";
sourceMapSupport.install();

import * as fs from "fs";
import * as path from "path";
import * as mkdirp from "mkdirp";

import { parseBikeShedFile } from "./parser";

////////////////////////////////////////////////////////////////////////////////

function prettyPrint(filePath: string, data: any)
{
    const json = JSON.stringify(data, null, 4);
    fs.writeFileSync(filePath, json);
}

(async function main() {
    const specPath = path.resolve(__dirname, "../libs/gpuweb/spec/index.bs")
    const blocks = await parseBikeShedFile(specPath);

    const rootPath = path.resolve(__dirname, "../..");
    mkdirp.sync(rootPath);
    prettyPrint(path.resolve(rootPath, "specifications.json"), {
        idl: Array.from(blocks.idl),
        dfn: Array.from(blocks.dfn),
        exposed: Array.from(blocks.exposed.values()),
    });

})();

