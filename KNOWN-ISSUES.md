# Known issues on this branch
## Crash on Windows
On windows, `System.load` crash with error `Can't find dependent libraries`.
Actual dependencies are :

    OPENGL32.dll
    kernel32.dll
    user32.dll
    gdi32.dll
    d3dcompiler_47.dll
    advapi32.dll
    ntdll.dll
    bcrypt.dll
    VCRUNTIME140.dll
    api-ms-win-crt-string-l1-1-0.dll
    api-ms-win-crt-math-l1-1-0.dll
    api-ms-win-crt-runtime-l1-1-0.dll
    api-ms-win-crt-heap-l1-1-0.dll


As a workaround, WGPU.dll must be placed on a folder listed on java.library.path