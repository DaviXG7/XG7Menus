plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.xg7plugins.extension"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.opencollab.dev/main/")
    }
}

dependencies {
    implementation(files("D:/XG7Plugins/plugins/XG7Plugins/XG7Plugins/build/libs/XG7Plugins-1.0.jar"))

    compileOnly(files("D:/buildTools/spigot-1.16.1.jar"))
    compileOnly(files("D:/buildTools/spigot-1.7.8.jar"))

    implementation("com.github.cryptomorin:XSeries:13.0.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

val targetJavaVersion = 8
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

tasks.shadowJar {
    archiveFileName.set("${project.name}-${project.version}.jar")
    minimize()
}
