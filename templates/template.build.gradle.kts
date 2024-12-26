import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.graalvm.buildtools.native") version "0.10.4" apply false
}

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "application")
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    dependencies {
        //Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")

        //Kotlin
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        //Oracle
        runtimeOnly("com.oracle.database.jdbc:ojdbc11")

        //Unit Tests
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
