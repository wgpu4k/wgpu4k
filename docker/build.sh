CHROME_URL="https://dl.google.com/linux/deb/pool/main/g/google-chrome-stable/google-chrome-stable_128.0.6613.84-1_amd64.deb"
SCRIPT_DIR=$(dirname "$(realpath "$0")")
# Vérifier si le fichier Chrome existe déjà
if [ ! -f $SCRIPT_DIR/chrome-128.deb ]; then
  curl -o $SCRIPT_DIR/chrome-128.deb $CHROME_URL
fi
docker build --platform=linux/amd64 -t ubuntu-wgpu4k $SCRIPT_DIR