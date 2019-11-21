# Versions:
# Gradle: 6.0
# Nodejs: 10.
# Yarn: 1.19.1
# Maven: 3.6.2
# JDK: openjdk 11_0_2

# Building image
FROM openjdk:11

# Enable DNS
RUN echo "nameserver 8.8.8.8" | tee /etc/resolv.conf > /dev/null

# Copy files
COPY build/libs /example
COPY src/main/resources /example
WORKDIR /example

# Running
ENTRYPOINT ["java", "-jar", "example.jar", "-server -XX:GCTimeRatio=2 -Xms1g -Xmx1g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:SurvivorRatio=8 -XX:TargetSurvivorRatio=90 -XX:MinHeapFreeRatio=40 -XX:MaxHeapFreeRatio=90 -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:InitiatingHeapOccupancyPercent=30 -XX:+AggressiveOpts -XX:+UseTLAB -XX:CompileThreshold=100 -XX:ThreadStackSize=4096 -XX:+UseFastAccessorMethods -XX:MaxTenuringThreshold=5 -XX:ReservedCodeCacheSize=256m"]