import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintPlugin

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
    application
}

group = "me.sergey"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    apply<KtlintPlugin>()
}
dependencies {
    testImplementation(kotlin("test"))

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
