# Android UI Tests with Appium

This project runs UI tests on the Wikipedia Android app using **Appium**, **JUnit 5**, and **Kotlin**.

---

## Getting Started

### Prerequisites

Make sure you have installed:

- Java 21+
- Node.js + npm
- Appium v2.18
- Android SDK with `emulator`, `platform-tools`, and a system image for API 29+
- AVD (Android Virtual Device) set up and bootable
- Gradle wrapper present (`./gradlew`)

---

## Configuration

### 1. Appium Server

Start Appium in the background **before** running tests

### 2. Environment Variables

Set credentials and configuration in your terminal or CI environment:

```bash
export WIKI_USER=your_username
export WIKI_PASS=your_password
export APPIUM_APP=/absolute/path/to/org.wikipedia.apk
export APPIUM_SERVER=http://127.0.0.1:4723
```


### 3. Or Use Property Files

If environment variables are not set, the framework will fall back to property files.

Create the following files in `src/test/resources/`:

`credentials.properties` (follow the example *credentials.properties.example*)
```properties
wikipedia.username=your_username
wikipedia.password=your_password
```

`test-config.properties`
```properties
appium.app=/absolute/path/to/org.wikipedia.apk
appium.server=http://127.0.0.1:4723
```


### 4. Running Tests

Start your Android Emulator:

```bash
emulator -avd your_avd_name
```

Then run the tests:
```bash
./gradlew clean test
```

To generate the Allure report:
```bash
./gradlew allureReport
```

The report will be available at:
```bash
build/reports/allure-report/index.html
```



