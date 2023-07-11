plugins {
    id("art-jvm")
}

group = "io.art.example"

tasks.withType(type = Wrapper::class) {
    gradleVersion = "7.2"
}

allprojects {
    group = rootProject.group
    repositories {
        mavenCentral()
    }
}
