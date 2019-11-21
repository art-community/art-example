import org.gradle.api.JavaVersion.*
import org.jetbrains.kotlin.gradle.tasks.*

/*
 * ART Java
 *
 * Copyright 2019 ART
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0awdawdwa
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("io.github.art.project") version "1.0.97"
}

tasks.withType(Wrapper::class.java) {
    gradleVersion = "6.0"
}

val bintrayUser: String? by project
val bintrayKey: String? by project
val version: String? by project

group = "io.github.art"

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

repositories {
    jcenter()
    mavenCentral()
}

apply(plugin = "io.github.art.project")

art {
    idea()
    lombok()
    tests()
    mainClass("ru.art.example.Main")
    embeddedModules {
        kit()
    }
}

tasks.withType<KotlinCompile> {
    sourceCompatibility = VERSION_11.toString()
    targetCompatibility = VERSION_11.toString()

    kotlinOptions {
        jvmTarget = VERSION_11.toString()
    }
}

allprojects {
    afterConfiguring {
        run {
            this@allprojects.delete("node_modules")
        }
    }
}