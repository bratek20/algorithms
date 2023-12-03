plugins {
    application
    id("io.freefair.lombok") version "8.4"
}

application {
    mainClass = "pl.bratek20.algorithms.solution.Compiler"
}

group = "pl.bratek20.algorithms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.6.1")
}

tasks.test {
    useJUnitPlatform()
}