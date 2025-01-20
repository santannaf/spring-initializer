plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.graalvm.buildtools.native") apply true
}

dependencies {
    
}

tasks.bootJar {
    archiveFileName.set("app.jar")
}

tasks.register<Exec>("extractJar") {
    group = "build"
    description = "Extracts the JAR file contents"
    val jarFile = file("./build/libs/app.jar")
    commandLine("jar", "-xvf", jarFile.absolutePath)
}

tasks.register<Exec>("runCustomJar") {
    group = "application"
    description = "Execute custom jar"
    dependsOn("bootJar")
    val name = "app.jar"
    val address = "./build/libs/$name"
    commandLine(
        "java",
        "-agentlib:native-image-agent=config-merge-dir=./src/main/resources/META-INF/native-image/",
        "-jar",
        address
    )
}

// "-cp .:BOOT-INF/classes:`find BOOT-INF/lib | tr '\\n' ':'`",
// "BOOT-INF/classes:BOOT-INF/lib/*",
tasks.register<Exec>("runCustomBootJar") {
    group = "application"
    description = "Execute custom boot jar"
    dependsOn("bootJar")
    val mainClass = "santannaf.custody.RestApplicationKt"
    commandLine(
        "java",
        "-agentlib:native-image-agent=config-merge-dir=./src/main/resources/META-INF/native-image/",
        "-cp", "BOOT-INF/classes:BOOT-INF/lib/*",
        mainClass
    )
}

graalvmNative {
    binaries {
        named("main") {
            val module = "stocks-custody-rest-api"
            imageName.set("app")
            configurationFileDirectories.from(file("$module/src/main/resources/META-INF/native-image/"))
            mainClass.set("santannaf.custody.RestApplicationKt")
            verbose.set(true)
            debug.set(true)
            buildArgs(
                "-J-Dfile.encoding=UTF-8",
                "-J-Duser.country=BR",
                "--enable-preview",
                "-march=native",
                "--color=always",
                "--allow-incomplete-classpath"
            )
        }
    }
}
