rootProject.name = "bungle"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

includePlatform("paper")

fun includePlatform(name: String) { // Thank you, Creative team :]
    val fullName = ":bungle-$name"
    include(fullName)
    project(fullName).projectDir = file("platforms/$name")
}