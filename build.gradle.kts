plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "dev.kepler88d"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven (url = "https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.7")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
