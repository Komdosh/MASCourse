import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
}

group = "pro.komdosh"
version = "0.0.1"

repositories {
    mavenCentral()
    maven(
        url = "https://jade.tilab.com/maven"
    )
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.tilab.jade", "jade", "4.5.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
