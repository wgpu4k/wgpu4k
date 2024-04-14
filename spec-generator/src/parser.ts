// Source: https://github.com/toji/bikeshed-to-ts/blob/main/src/parser.js

import { readFileLines } from  "bikeshed-to-ts/src/reader.js";
import { matchEndDFN, matchEndIDL, matchStartDFN, matchStartIDL } from  "bikeshed-to-ts/src/regex.js";
import { IDLRootType, parse as parseIDL } from  "webidl2";

////////////////////////////////////////////////////////////////////////////////

async function parseBikeShedFile(filePath) {
    const exposed = new Set();

    const dfnBlocks = new Map();
    const idlBlocks = new Map<string, IDLRootType[]>();

    let idlRecording = null;
    let dfnRecording = null;

    await readFileLines(filePath, line => {
        /* DNF Matching */
        const dfnMatch = matchStartDFN(line);
        if (dfnMatch) {
            dfnRecording = {
                target: dfnMatch[2],
                type: dfnMatch[1],
                lines: [],
            }
            return;
        }

        if (dfnRecording && matchEndDFN(line)) {
            if (!dfnBlocks.has(dfnRecording.target)) {
                dfnBlocks.set(dfnRecording.target, []);
            }
            dfnBlocks.get(dfnRecording.target).push(dfnRecording);
            dfnRecording = null;
            return;
        }

        if (dfnRecording) {
            dfnRecording.lines.push(line);
            return;
        }

        /* IDL Matching */
        if (matchStartIDL(line)) {
            idlRecording = [];
            return;
        }

        if (idlRecording && matchEndIDL(line)) {
            const idlText = idlRecording.join('\n');
            idlRecording = null;

            const idlBlock = parseIDL(idlText) as any;
            for (const idlNode of idlBlock) {
                const typeName = idlNode.name || idlNode.target;

                if (!idlBlocks.has(typeName)) {
                    idlBlocks.set(typeName, []);
                }

                idlBlocks.get(typeName).push(idlNode);

                if (isExposed(idlNode)) {
                    exposed.add(typeName);
                }
            }

            return;
        }

        if (idlRecording) {
            idlRecording.push(line);
        }
    });

    

    return {
        dfn: dfnBlocks,
        idl: idlBlocks,
        //ts: tsBlocks,
        exposed,
    };
}

function isExposed(idlNode) {
    if (idlNode.extAttrs.length) {
        for (const attr of idlNode.extAttrs) {
            if (attr.name === 'Exposed') {
                return true;
            }
        }
    }
    return false;
}

export {
    parseBikeShedFile,
    isExposed,
};