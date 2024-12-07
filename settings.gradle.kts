pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")

        gradlePluginPortal()
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.5-beta.5"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    shared {
        versions("1.16.5", "1.17.1", "1.18.2", "1.19.4", "1.20.1", "1.21")
        vcsVersion = "1.21"
    }

    create(rootProject)
}

rootProject.name = "codelib"