# Versions:
# Gradle: 6.0
# Nodejs: 10.
# Yarn: 1.19.1
# Maven: 3.6.2
# JDK: openjdk 11_0_2

# Building image
FROM ubuntu:18.10

# Enable DNS
RUN echo "nameserver 8.8.8.8" | tee /etc/resolv.conf > /dev/null

# Setup APT sources
RUN DISTRIB_CODENAME=$(cat /etc/*release* | grep DISTRIB_CODENAME | cut -f2 -d'=') \
    && echo "deb http://archive.ubuntu.com/ubuntu ${DISTRIB_CODENAME} main universe\n" > /etc/apt/sources.list \
    && echo "deb http://archive.ubuntu.com/ubuntu ${DISTRIB_CODENAME}-updates main universe\n" >> /etc/apt/sources.list \
    && echo "deb http://security.ubuntu.com/ubuntu ${DISTRIB_CODENAME}-security main universe\n" >> /etc/apt/sources.list

# APT preparation
RUN apt-get update -qqy
RUN apt-get -qqy --no-install-recommends install software-properties-common

# Add APT Git repository
RUN add-apt-repository -y ppa:git-core/ppa
RUN apt-get update -qqy

# Insall APT tools
RUN apt-get -qqy --no-install-recommends install apt-utils

# Install common libraries
RUN apt-get -qqy --no-install-recommends install \
    curl \
    fontconfig \
    bzr \
    iproute2 \
    tar zip unzip \
    wget curl \
    dirmngr \
    iptables \
    build-essential \
    less nano tree \
    gnupg-agent

# Install SSH
RUN apt-get -qqy --no-install-recommends install \
    openssh-client ssh-askpass

# Install certificates
RUN apt-get -qqy --no-install-recommends install \
    ca-certificates

# Install JDK
RUN apt-get -qqy --no-install-recommends install \
        openjdk-11-jdk

# Set securerandom for Java
RUN sed -i 's/securerandom\.source=file:\/dev\/random/securerandom\.source=file:\/dev\/urandom/' ./usr/lib/jvm/java-11-openjdk-amd64/conf/security/java.security

# Copy files
COPY build/libs /example
COPY src/main/resources /example
WORKDIR /example

# Running
ENTRYPOINT ["java", "-jar", "example.jar", "-server -XX:GCTimeRatio=2 -Xms1g -Xmx1g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:SurvivorRatio=8 -XX:TargetSurvivorRatio=90 -XX:MinHeapFreeRatio=40 -XX:MaxHeapFreeRatio=90 -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:InitiatingHeapOccupancyPercent=30 -XX:+AggressiveOpts -XX:+UseTLAB -XX:CompileThreshold=100 -XX:ThreadStackSize=4096 -XX:+UseFastAccessorMethods -XX:MaxTenuringThreshold=5 -XX:ReservedCodeCacheSize=256m"]