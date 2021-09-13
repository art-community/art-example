import org.gradle.internal.jvm.Jvm.current
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("art-jvm")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        apiVersion = "1.5"
        jvmTarget = current().javaVersion!!.toString()
    }
}

art {
    modules {
        embedded {
            kotlin {
                kit()
            }
        }
    }
    generator {
        source("Example") {
            jvm()
            modulePackage("ru")
        }
    }
    executable {
        main("ru.ExampleKt")
        jar()
        native {
            graalVersion("21.3.0-dev")
            graalUrl("https://github.com/graalvm/graalvm-ce-dev-builds/releases/download/21.3.0-dev-20210910_2147/graalvm-ce-java11-darwin-amd64-dev.tar.gz")
        }
    }
}
