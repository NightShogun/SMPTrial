import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "xyz.nightshogun.smptrial"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

    compileOnly("org.spigotmc:spigot:1.18.1-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:minecraft-server:1.18.1-R0.1-SNAPSHOT")

    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

}

tasks {

    withType<ShadowJar> {

        archiveBaseName.set("SMPTrial")
        archiveVersion.set(version.toString())
        archiveClassifier.set("")
        destinationDirectory.set(file("/home/skylar/Desktop/mc/localhost/spigot1.18.1/plugins"))

        minimize()
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"

        //ok so, this tells the compiler to preserve the method parameters names in the bytecode
        options.compilerArgs.add("-parameters")
    }

    withType<ProcessResources> {
        from(sourceSets.main.get().resources)
        filteringCharset = "UTF-8"
        include("plugin.yml")

        filter {line ->
            line.replace("%version%", project.version.toString())
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    named("assemble"){
        dependsOn("shadowJar")
    }

}