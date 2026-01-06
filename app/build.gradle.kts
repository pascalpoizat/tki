plugins {
    java
    application
}

group = "com.tki"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
    implementation("org.jsoup:jsoup:1.17.2")
}

application {
    mainClass.set("com.tki.TkiApp")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// Configuration pour créer un "Fat Jar" (inclus les dépendances)
tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.tki.TkiApp"
    }
    // On prend les sources compilées du projet
    from(sourceSets.main.get().output)
    
    // On ajoute toutes les dépendances décompressées (zipTree)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
    
    // Stratégie en cas de fichiers en doublon dans les libs (ex: LICENSE, META-INF)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}