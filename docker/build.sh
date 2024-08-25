CHROME_URL="https://dl.google.com/linux/deb/pool/main/g/google-chrome-stable/google-chrome-stable_128.0.6613.84-1_amd64.deb"
SCRIPT_DIR=$(dirname "$(realpath "$0")")
curl -o $SCRIPT_DIR/chrome-128.deb $CHROME_URL
docker build -t ubuntu-wgpu4k $SCRIPT_DIR