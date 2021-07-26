plugins {
    id("art-internal-jvm")
}

group = "io.art.example"

tasks.withType(type = Wrapper::class) {
    gradleVersion = "7.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

project(":example-java") {
    group = rootProject.group
    apply(plugin = "java-library")
}
