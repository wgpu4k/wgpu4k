# Start with the official Ubuntu base image
FROM ubuntu:latest

RUN apt-get update && \
    apt-get install -y git unzip curl zip mesa-common-dev gdebi-core xvfb libasound2t64 libglfw3-dev &&  \
    apt-get clean;

COPY chrome-128.deb /tmp/
RUN gdebi -n /tmp/chrome-128.deb
RUN rm /tmp/chrome-128.deb

ARG CANDIDATE=java
ARG CANDIDATE_VERSION=22-open

ENV SDKMAN_DIR=/home/user/.sdkman

RUN useradd -ms /bin/bash user
USER user
WORKDIR /home/user

RUN curl -s "https://get.sdkman.io" | bash && \
    echo "sdkman_auto_answer=true" > $SDKMAN_DIR/etc/config && \
    echo "sdkman_auto_selfupdate=false" >> $SDKMAN_DIR/etc/config

# Source sdkman to make the sdk command available and install candidate
RUN bash -c "source $SDKMAN_DIR/bin/sdkman-init.sh && sdk install $CANDIDATE $CANDIDATE_VERSION"

# Add candidate path to $PATH environment variable
ENV JAVA_HOME="$SDKMAN_DIR/candidates/java/current"
ENV PATH="$JAVA_HOME/bin:$PATH"


ENV DISPLAY :1

ENTRYPOINT ["/bin/bash", "-c", "source $SDKMAN_DIR/bin/sdkman-init.sh && \"$@\"", "-s"]
