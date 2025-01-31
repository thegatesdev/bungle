plugins {
    java
}

group = "io.github.thegatesdev"
version = "1.0-SNAPSHOT"
description = "Minecraft addon providing extra inventory functionality"

ext.set("id", "bungle")
ext.set("website", "https://github.com/thegatesdev/bungle")
ext.set("author", "thegatesdev")

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

