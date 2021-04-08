group = "io.art.example"

tasks.withType(type = Wrapper::class) {
    gradleVersion = "7.0-rc-2"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

project(":example-java") {
    apply(plugin = "java-library")
}
