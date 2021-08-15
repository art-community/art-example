plugins {
    `java-library`
    id("art-jvm")
}


art {
    modules {
        embedded {
            java {
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
}

dependencies {
    val lombokVersion: String by project
    compileOnly("org.projectlombok", "lombok", lombokVersion)
    annotationProcessor("org.projectlombok", "lombok", lombokVersion)
}
