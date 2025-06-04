plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val aspectJVersion = "1.9.21"
val allureVersion = "2.25.0"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.appium:java-client:9.3.0")
    implementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    implementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    implementation("io.qameta.allure:allure-junit5")
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
    finalizedBy("allureReport")
}

kotlin {
    jvmToolchain(21)
}