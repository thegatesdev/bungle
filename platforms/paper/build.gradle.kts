plugins {
    id("bungle.java-conventions")
    alias(libs.plugins.paper.run)
    alias(libs.plugins.paper.gen)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc"
    }
}

dependencies {
    compileOnly(libs.paper.api)
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
    main = "${project.group}.Bungle"
}