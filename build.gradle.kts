plugins {
    application
    id("io.freefair.lombok") version "8.4"

    `java-test-fixtures`
}

application {
    mainClass = "pl.bratek20.algorithms.solution.main.MainScript"
}

group = "pl.bratek20.algorithms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        url = uri("https://maven.pkg.github.com/bratek20/commons")
        credentials {
            username = (findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")).toString()
            password = (findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")).toString()
        }
    }
}

dependencies {
    implementation("pl.bratek20:bratek20-commons:1.0.3")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.6.1")
    testImplementation("org.mockito:mockito-junit-jupiter:3.12.4")

    testFixturesImplementation("org.mockito:mockito-junit-jupiter:3.12.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}