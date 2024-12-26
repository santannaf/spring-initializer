plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
