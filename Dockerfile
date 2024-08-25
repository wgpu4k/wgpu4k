# Utiliser l'image de base Ubuntu
FROM ubuntu:latest

# Install updates and OpenJDK
RUN apt-get update && \
    apt-get install -y git unzip curl zip mesa-common-dev &&  \
    apt-get clean

RUN apt-get install libglfw3-dev

RUN curl -s "https://get.sdkman.io" | bash

# this SHELL command is needed to allow using source
SHELL ["/bin/bash", "-c"]

RUN source "/root/.sdkman/bin/sdkman-init.sh" \
    echo "java=22-open" > /root/.sdkmanrc \
    sdk env install

# Mettre à jour les paquets et installer les dépendances nécessaires
RUN apt-get install -y \
    wget \
    apt-transport-https \
    software-properties-common \
    ca-certificates

# Installer VNC server, desktop environment et outils nécessaires
RUN apt-get install -y \
    xfce4 \
    xfce4-goodies \
    tightvncserver

# Configurer VNC server
RUN mkdir -p /root/.vnc
RUN touch /root/.vnc/passwd
RUN /bin/bash -c "echo -e 'password\npassword\nn' | vncpasswd" > /root/.vnc/passwd
RUN chmod 400 /root/.vnc/passwd
RUN chmod go-rwx /root/.vnc
RUN touch /root/.Xauthority
RUN touch /root/.Xresources
RUN chmod 400 /root/.Xresources

# Exposer le port VNC
EXPOSE 5901

ENV USER root
ENV DISPLAY=:1

# Démarrer le VNC server
CMD /usr/bin/tightvncserver :1 -geometry 1280x800 -depth 24 -rfbport 5901 && tail -F /root/.vnc/*.log