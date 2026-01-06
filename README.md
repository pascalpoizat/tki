# TKI (Researcher Info CLI)

**TKI** is a lightweight Command Line Interface (CLI) tool designed to instantly retrieve academic researcher information and publication statistics directly from your terminal.

## ✨ Key Features

- **DBLP Integration:** Fetches publication counts and official profile links directly from the DBLP computer science bibliography.

- **Smart Search:** Automatically resolves author names, handles basic variations, and finds the correct Author PID without manual lookup.

- **Standalone Architecture:** Distributed as a "Fat Jar", requiring only a Java runtime (JRE) to run.

- **Extensible Design:** Built on the Strategy Pattern to easily support future data sources (Google Scholar, HAL, OpenAlex, etc.).

## 🚀 Installation

### Option 1: Automatic Installer (Mac/Linux)

The easiest way to install TKI is using the automated script. It downloads the latest binary and configures your path.

```bash
curl -sL https://raw.githubusercontent.com/pascalpoizat/tki/main/install.sh | bash
```

### Option 2: Manual Installation

1. Go to the [Latest Release](https://github.com/pascalpoizat/tki/releases/latest) page.

2. Download `app-1.0.0.jar` and the `tki` script.

3. Place them in a folder of your choice.

4. Make the script executable:

```bash
chmod +x tki
```

## 💻 Usage

Simply run the command followed by the researcher's name. Quotes are recommended but optional.

```bash
./tki "Camille Noûs"
```


Output Example

```bash
🔍 [DBLP] Recherche de 'Camille Noûs'...
--------------------------------------------------
SOURCE         : DBLP
👤 NOM         : Camille Noûs
📚 PUBLICATIONS: 56
🔗 URL         : https://dblp.org/pid/262/3616.xml
--------------------------------------------------
```

## 🛠️ Requirements

**Java 21** (JDK or JRE) must be installed and available in your system `PATH`.

## 🏗️ Building from Source

If you want to contribute or build the project manually:

```bash
# Clone the repository
git clone https://github.com/pascalpoizat/tki.git
cd tki

# Build the Fat Jar
./gradlew jar

# Run locally
./tki "Researcher Name"
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project

2. Create your feature branch (`git checkout -b feature/AmazingFeature`)

3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)

4. Push to the branch (`git push origin feature/AmazingFeature`)

5. Open a Pull Request