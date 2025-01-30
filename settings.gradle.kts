rootProject.name = "bungle"

includePlatform("paper")

fun includePlatform(name: String) { // Thank you, Creative team :]
    val fullName = ":bungle-$name"
    include(fullName)
    project(fullName).projectDir = file("platforms/$name")
}