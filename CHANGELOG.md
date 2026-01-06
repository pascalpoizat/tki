# v1.0.0: Initial Release 🚀

First public release of **TKI**, a lightweight Command Line Interface (CLI) tool designed to quickly retrieve academic researcher information.

## ✨ Key Features

- **DBLP Integration:** Instantly fetch publication counts and profile links directly from the DBLP computer science bibliography.

- **Smart Search:** Automatically resolves author names and handles basic variations to find the correct Author PID.

- **Standalone Architecture:** Distributed as a "Fat Jar" containing all necessary dependencies (Picocli, Jsoup).

- **Extensible Design:** Built using the Strategy Pattern, paving the way for future integrations (Google Scholar, HAL, OpenAlex, etc.).

## 📦 Installation

### Option 1: One-Line Installer (Recommended)

Run the following command in your terminal to download and configure `tki` automatically:

```bash
curl -sL [https://raw.githubusercontent.com/pascalpoizat/tki/main/install.sh](https://raw.githubusercontent.com/pascalpoizat/tki/main/install.sh) | bash
```

### Option 2: Manual Installation

1. Download `tki` (shell script) and `app-1.0.0.jar` from the Assets section below.

2. Place both files in the same directory.

3. Make the script executable: `chmod +x tki`.`

4. Run: `./tki "Researcher Name"`.

## 📋 Requirements

**Java 21** (JRE or JDK) must be installed and available in your PATH.

## ⚠️ Known Issues / Notes

**Language:** While the tool works perfectly, the CLI output messages are currently in **French**. English localization is scheduled for the next release.