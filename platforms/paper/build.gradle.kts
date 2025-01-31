plugins {
    id("bungle.java-conventions")
    alias(libs.plugins.paper.run)
    alias(libs.plugins.paper.gen)
    alias(libs.plugins.paper.userdev)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper.api)
}

tasks {
    runServer {
        minecraftVersion(libs.versions.minecraft.get())
    }
}

paperPluginYaml {
    name = project.name
    description = project.description
    author = "TheGatesDev"
    website = "https://github.com/thegatesdev/bungle"
    version = project.version.toString()
    apiVersion = libs.versions.minecraft
    main = "${project.group}.bungle.Bungle"
}