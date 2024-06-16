plugins {
    kotlin("jvm")
    id("art-jvm")
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
