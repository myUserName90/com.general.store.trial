# 📱 General Store App Automation Framework

This project is a **Java-based Appium automation framework** designed for testing the **General Store Android app**. The
framework follows a modular Page Object Model (POM) architecture and is optimized for maintainability and scalability.
Resources:
- Test Cases: [Test Cases](src/main/resources/task/Test_Cases.pdf)
- APK: [General Store APK](src/main/resources/task/General-Store.apk)

---

## 🚀 Features

- ✅ **Test automation for Android (Appium with UiAutomator2)**
- 🧩 Modular **Page Object Model** (BaseScreen & Screen classes)
- 🧪 **TestNG** as the test runner
- 📄 Centralized **config.properties**
- 🔧 Thread-safe **AppiumDriver** management
- 🪵 **Log4j2** logging

---

## 📁 Project Structure

```
src/
└── main/
├── java/
│ └── com.general.store.trial/
│ ├── configuration/ # ConfigReader and Configuration loader
│ ├── driver/ # AppiumDriver factory and base classes
│ ├── enums/ # Enums like Env or ScrollDirection
│ ├── helper/ # Utility classes like WaitHelper
│ ├── model/ # Data models (e.g., ProductItem)
│ └── screens/ # BaseScreen and individual screen classes
└── resources/
└── log4j2.properties # Logging config

test/
└── java/
└── com.general.store.trial.tests/
├── BaseTest.java # Test base setup/teardown
├── ProductTests.java # Sample product interaction test
└── CartTests.java # Cart-related validation test
```

---

## 🧰 Tech Stack

| Layer       | Tool/Library               |
|-------------|----------------------------|
| Language    | Java 21                    |
| Automation  | Appium Java Client `9.5.0` |
| Device      | Android Emulator           |
| Test Runner | TestNG                     |
| Build Tool  | Maven                      |
| Logging     | Log4j2                     |

---

## 🧪 Test Execution

### Pre-requisites

- Java 21
- Android Emulator running (`emulator-5554`) [source](https://developer.android.com/studio)
- Appium Server running on `http://127.0.0.1:4723`
- Maven installed

## ⚙️ Configuration

Two property files are used:

- Defines the active environment.
`env.properties`
```properties
environment=emulator
app=com.general.store
platform=Android
```
- Device and Appium-related settings.
`emulator.properties`
```peoperties
automator2.server=UiAutomator2
device.name=emulator-5554
appium.server.url=http://127.0.0.1:4723
app.package=com.androidsample.generalstore
app.activity=com.androidsample.generalstore.MainActivity
new.command.timeout=300
```


## ▶️ How to Run

1. Install Dependencies 

``mvn clen install``

2. Run Tests

``mvn clean test``

## 📜 Author
Created by Narek Melikyan