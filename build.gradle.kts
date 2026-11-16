plugins {
    kotlin("jvm") version "2.3.21"
    id("com.gradleup.shadow") version "8.3.5"
    id("xyz.jpenilla.run-paper") version "3.0.2"
}

group = "net.halflove"
version = "1.6"

repositories {
    mavenCentral()

    // Spigot
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

    // PlaceholderAPI
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    // Spigot
    compileOnly("org.spigotmc:spigot-api:26.1-R0.1-SNAPSHOT")
    // PAPI
    compileOnly("me.clip:placeholderapi:2.11.6")

    implementation(kotlin("stdlib"))
}

kotlin {
    jvmToolchain(25)
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    processResources {
        val props = mapOf("version" to project.version.toString())
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching(listOf("plugin.yml")) {
            expand(props)
        }
    }

    shadowJar {
        archiveClassifier.set("shaded")

        dependencies {
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib:.*"))
        }

        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")

        // Copy compiled jar to test server
        doLast {
            copy {
                from(archiveFile)
                into("B:/Servers/Survival 1.21 Project/plugins/")
            }
        }
    }

    runServer {
        minecraftVersion("26.3")
        jvmArgs("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005")
        pluginJars(shadowJar.flatMap { it.archiveFile })
    }
}