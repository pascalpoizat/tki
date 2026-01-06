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
    // Picocli pour gérer les arguments de la ligne de commande (CLI)
    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")

    // Jsoup pour le scraping HTML (récupérer les infos sur Google Scholar)
    implementation("org.jsoup:jsoup:1.17.2")
}

application {
    // Classe principale qui sera exécutée
    mainClass.set("com.tki.TkiApp")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}