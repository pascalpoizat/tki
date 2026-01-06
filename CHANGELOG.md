# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-01-06

### Added

- **DBLP Integration:** Instantly fetch publication counts and profile links directly from the DBLP computer science bibliography.

- **Smart Search:** Automatically resolves author names and handles basic variations to find the correct Author PID.

- **Standalone Architecture:** Distributed as a "Fat Jar" containing all necessary dependencies (Picocli, Jsoup).

- **Strategy Pattern:** Implemented generic strategy interface for future integrations (Google Scholar, HAL, etc.).

- **One-Line Installer:** Added `install.sh` script for easy setup via curl.

### Known Issues

- CLI output messages are currently in **French** only. English localization is scheduled for v1.1.0.