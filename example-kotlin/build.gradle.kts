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
    }
}
