# 📌 FuelCleanCode

A clean‑code and architecture‑oriented Java project demonstrating a modular structure with different domain and service layers.  
This repository uses **Maven** to manage dependencies and build lifecycles.

---

## 🧱 Project Modules

| Module | Description |
|--------|-------------|
| `Domain` | Core business entities and domain logic |
| `Service` | Application services implementing business use cases |
| `Document‑Service` | Service for managing files |
| `Desktop` | JavaFx client |

---

## 🚀 Features

- Modular architecture with clear separation of concerns
- Clean code principles applied
- Multiple report types (DriverReport, OfficeReport, region reports)
- Maven‑based build and dependency management
- Sample log files included for analysis or test cases
- **JasperReports**

---

## 🔧 Prerequisites

Make sure you have the following installed:

- **Java JDK 17+**
- **Maven 3.6+**
- **ZKTeco Fingerprint Scanner SDK**  
  You can download it from [ZKTeco Download Center](https://www.zkteco.com/en/download_center)  

> The SDK is required to enable fingerprint-based login functionality.

---

## 📦 Build & Install

### 1. Clone the repository

```bash
git clone https://github.com/KawaMamo/FuelCleanCode.git
cd FuelCleanCode
```
### 2. Install modules to local Maven repo

the project is multi-module:
```bash
mvn clean install
```
This builds all modules (Domain, Service, Document‑Service, etc.) and ensures dependencies are installed locally.

---
### 📌 Common Build Issues
If you encounter:
```bash
Could not find artifact com.zkteco:… not found …
```
Install manually into your local Maven repo:
```bash
mvn install:install-file \
  -Dfile=path/to/zkteco.jar \
  -DgroupId=com.zkteco \
  -DartifactId=zkteco \
  -Dversion=v1 \
  -Dpackaging=jar
```
Then run agian:
```bash
mvn clean install -U
```
---
### 📂 Directory Structure
```bash
FuelCleanCode/
├── Domain/
├── Service/
├── Document-Service/
├── Desktop/
├── src/main/java/org/example/
├── gas_station.log
└── pom.xml
```
---
## 📖 Usage
After building:
**1. Run the application (via your IDE or from command line):**
```bash
mvn exec:java
```
**2. Login using Identity Provider.**

**3. Inspect generated reports in the root or designated report folders.**

**4. Use logs (gas_station.log) to simulate input data.**

---
## 🛠 Contributing
Contributions are welcome!

**1. Fork the repository**

**2. Create a feature branch**

**3. Run tests & ensure checks pass**

**4. Open a pull request**

---
### 📄 License
Apache 2.0

---
### 📬 Contact

If you have questions or need help:

**- Open an issue on this repo**

**- @KawaMamo on GitHub**

---
