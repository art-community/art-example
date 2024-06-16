plugins {
    id("art-jvm")
}

group = "io.art.example"

tasks.withType(type = Wrapper::class) {
    gradleVersion = "8.5"
}

allprojects {
    group = rootProject.group
    repositories {
        mavenCentral()
    }
}
