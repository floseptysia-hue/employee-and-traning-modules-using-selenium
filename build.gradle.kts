plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.18.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.3")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("org.testng:testng:7.9.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("io.qameta.allure:allure-testng:2.25.0")

    testImplementation("io.github.cdimascio:dotenv-java:3.0.0")
}

tasks.test {
    useTestNG()

    testLogging {
        events("passed", "failed", "skipped")
    }
}
