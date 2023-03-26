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

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "dev.kepler88d.Main"
    }

    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // To add all the dependencies
    from(sourceSets.main.get().output)
    archiveFileName.set("${project.name}.jar")

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

