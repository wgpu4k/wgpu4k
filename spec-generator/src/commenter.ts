// source: https://github.com/toji/bikeshed-to-ts/blob/main/src/commenter.js

import {
    cleanString,
    matchAttributeExp,
    matchCommentExp, matchMethodArgumentExp,
    matchMethodExp,
    matchXMLBlockEndExp,
    matchXMLBlockStartExp
} from "bikeshed-to-ts/src/regex.js";

////////////////////////////////////////////////////////////////////////////////

function makeXMLBlock(matchResult) {
    return {
        tag: matchResult[1],
        typeAttr: matchResult[4] && matchResult[4] !== 'for' ? matchResult[4] : matchResult[2],
        typeVal: matchResult[4] && matchResult[4] !== 'for' ? matchResult[5] : matchResult[3],
        target: matchResult[2] && matchResult[2] === 'for' ? matchResult[3] : matchResult[5],
    }
}

function processMethodComment(definition) {
    const blocks: any[] = [];
    let currentBlock: any = null;
    let comment = [];
    for (const line of definition.lines) {
        const trimmed = matchCommentExp(line);
        if (trimmed && trimmed[1] !== '::') {
            const xmlBlockStart = matchXMLBlockStartExp(trimmed[1]);
            if (xmlBlockStart) {
                currentBlock = makeXMLBlock(xmlBlockStart);
                blocks.push(currentBlock);
                continue;
            }

            const xmlBlockEnd = matchXMLBlockEndExp(trimmed[1]);
            if (xmlBlockEnd) {
                if (blocks.length && blocks[blocks.length - 1].tag === xmlBlockEnd[1]) {
                    blocks.pop();
                    currentBlock = blocks.length ? blocks[blocks.length - 1] : null;
                }
                continue;
            }

            if (!currentBlock) {
                comment.push(cleanString(trimmed[1]));
            }
            // else if (currentBlock.typeVal === 'argumentdef') {
            //     const argument = matchMethodArgumentExp(trimmed[1]);
            //     if (argument) {
            //         comment += `* @param ${cleanString(argument[1])} - ${cleanString(argument[2])}\n `;
            //     } else {
            //         comment += `* \t${cleanString(trimmed[1])}\n `;
            //     }
            // }
        }
    }

    return comment.join(" ");
}

function processAttributeComment(definition) {
    let comment = [];
    for (const line of definition.lines) {
        const trimmed = matchCommentExp(line);
        if (trimmed && trimmed[1] !== '::') {
            comment.push(cleanString(trimmed[1]));
        }
    }

    return comment.join(" ");
}

function processComment(comment) {
    if (comment.type === 'attribute' || comment.type === 'dict-member') {
        return processAttributeComment(comment);
    }
    
    if (comment.type === 'method') {
        return processMethodComment(comment);
    }
}

function processComments(dfnTypes) {
    //const memberSet = new Set(); // for overloaded functions
    const commentDefinitions = {};

    for (const dfnType of dfnTypes) {
        const typeName = dfnType[0];
        commentDefinitions[typeName] = [];
        console.log(`TYPENAME ${typeName}`);

        const dfnBlocks = dfnType[1];
        for (const block of dfnBlocks) {
            if (block.type === 'method' || block.type === 'attribute' || block.type === 'dict-member') {
                const matcher = block.type === 'method' ? matchMethodExp : matchAttributeExp;
                let commentDefinition: any = null;

                for (const line of block.lines) {
                    const memberName = matcher(line);
                    if (memberName) {
                        //const member = target.members.find(m => m.name?.escapedText === memberName[1] && !memberSet.has(m));
                        //if (member) {
                            commentDefinition = {
                                //member,
                                type: block.type,
                                lines: [],
                                comment: "",
                            };
                            commentDefinitions[typeName].push(commentDefinition);
                            //memberSet.add(member);
                        //}
                    } else if (commentDefinition) {
                        commentDefinition.lines.push(line);
                    }
                }
            } else {
                //console.log(`WARNING! SKIPPING LEADING COMMENT FOR UNRECOGNIZED DFN BLOCK TYPE: ${block.type} [${block.target}]`);
            }
        }

        commentDefinitions[typeName].forEach(cd => { cd.comment = processComment(cd) });
    }

    return commentDefinitions;
}

export {
    makeXMLBlock,
    processMethodComment,
    processAttributeComment,
    processComment,
    processComments,
};
