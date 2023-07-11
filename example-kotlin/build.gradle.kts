import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("art-jvm")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        apiVersion = "1.5"
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
        native()
    }
}
