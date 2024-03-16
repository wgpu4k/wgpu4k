import {copyAndWatch} from '../lib/copyAndWatch.js';

const watch = !!process.argv[2];

copyAndWatch(
    [
        {src: 'public/**/*', srcPrefix: 'public', dst: 'out'},
        {src: 'meshes/**/*', dst: 'out'},
        {src: 'sample/**/*', dst: 'out'},
        {src: 'samples/**/*', dst: 'out'},
        {src: 'shaders/**/*', dst: 'out'},
        {src: 'other/**/*', dst: 'out'},
        {
            src: 'build/compileSync/js/main/developmentExecutable/kotlin/*',
            srcPrefix: 'build/compileSync/js/main/developmentExecutable/kotlin/',
            dst: 'out/kotlin-libs'
        }
        ,
        {src: 'index.html', dst: 'out'},
    ],
    {watch}
);
