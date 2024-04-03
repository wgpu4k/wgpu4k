npm install

DIR="./node_modules/@webgpu/types/dist/index.d.ts"

if [ ! -f "$DIR" ]; then
    echo "Typescript definition not found"
    exit 1
fi

rm -r ./src
dukat -p "io.ygdrasil.wgpu.internal.js" -d ./src ./node_modules/@webgpu/types/dist/index.d.ts