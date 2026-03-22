# Test Automation – Selenium + TestNG + Jenkins + Allure

A Java-based UI test automation project built with **Selenium WebDriver**, **TestNG**, **Maven**, and **Allure Reports**, integrated with **Jenkins** for CI/CD pipeline execution.

---

## 📁 Project Structure

```
Test_Automation-ITI_Jenkins/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com.selenium.pages/
│   │           └── WebFormPage.java
│   └── test/
│       └── java/
│           └── com.selenium.tests/
│               ├── AlertTest.java
│               ├── CookieBasedTest.java
│               ├── DynamicPageTest.java
│               ├── MouseInteractionTest.java
│               ├── PageWithFrameTest.java
│               └── WebFormTest.java
├── .gitignore
├── pom.xml
├── testng.xml
└── README.md
```

---

## 🛠️ Tech Stack

* **Language:** Java 19
* **Browser Automation:** Selenium WebDriver 4.39.0
* **Test Framework:** TestNG 7.10.2
* **Build Tool:** Maven
* **Reporting:** Allure 2.24.0
* **CI/CD:** Jenkins
* **Browser Driver:** ChromeDriver (auto-managed)


---

## 🧪 Test Scenarios

### Alert Tests

**AlertTest.java**

Test Cases:

#### alertTest

- Navigate to the alerts page
- Click the alert button
- Switch to alert and retrieve its text
- Assert the alert text equals `"cheese"`
- Accept the alert

#### promptAlertTest

- Navigate to the alerts page
- Click the prompt button
- Switch to alert and send keys `"hello"`
- Accept the alert

#### promptAlertInFrameTest

- Navigate to the alerts page
- Switch to iframe `"iframeWithAlert"`
- Click the link inside the frame
- Switch to alert and assert the text equals `"framed cheese"`
- Accept and return to default content

#### promptAlertInNewWindowTest

- Navigate to the alerts page
- Click button to open a new window with an onload alert
- Wait for two windows to be available
- Switch to the new window
- Accept the alert
- Assert the page text equals `"Page with onload event handler"`
- Switch back to the original window

#### alertInSelectTest

- Navigate to the alerts page
- Find the select dropdown and select value `"2"`
- Switch to alert and assert the text equals `"changed"`
- Accept the alert and return to default content

---

### Cookie-Based Tests

**CookieBasedTest.java**

Test Cases:

#### getCookies

- Navigate to the cookie background page
- Click the green button
- Retrieve the cookie named `"theme"`
- Assert the cookie value equals `"lightgreen"`
- Take and save a screenshot as `new.png`

---

### Dynamic Page Tests

**DynamicPageTest.java**

Test Cases:

#### checkButtonAddBox

- Open dynamic page
- Click "Add Box"
- Wait for new element with id `"box0"` to appear
- Verify background color is red (`#ff0000`)

#### checkRevealNewInput

- Open dynamic page
- Click reveal button
- Wait for input field with id `"revealed"` to appear
- Send text `"Ahmed"`
- Verify input value equals `"Ahmed"`

---

### Mouse Interaction Tests

**MouseInteractionTest.java**

Test Cases:

#### dragAndDrop

- Navigate to the mouse interaction page
- Find source element `"draggable"` and destination `"droppable"`
- Perform drag-and-drop action
- Assert the drop status text contains `"dropped"`

#### moveMouse

- Navigate to the mouse interaction page
- Move the mouse to the `"mouse-tracker"` element with offset (5, 5)
- Assert the relative location text contains `"5, 5"`

#### hoverMouse

- Navigate to the mouse interaction page
- Move the mouse to the `"hover"` element
- Assert the move status text contains `"hovered"`

---

### Page With Frame Tests

**PageWithFrameTest.java**

Test Cases:

#### switchToFrameThenToDefaultThenToNewWindow

- Navigate to the page with frame
- Switch to frame `"myframe"` and assert the div text contains `"Simple page with simple test."`
- Return to default content and click `"Open new window"`
- Wait for two windows to be available
- Switch to the new window titled `"Simple Page"`
- Assert the div text contains `"Simple page with simple test."`

---

### Web Form Tests

**WebFormTest.java**

Test Cases:

#### checkFormSubmittedMessage

- Fill form fields (text, password, datalist)
- Select dropdown value `"3"`
- Uncheck checkbox 1, check checkbox 2
- Select radio button 2
- Set color input to `#00ff00`
- Set range slider to `10`
- Submit form
- Wait for page title to contain `"target page"`
- Assert heading text equals `"Form submitted"`

#### checkDisabledInputIsDisable

- Navigate to the web form page
- Find the input field with name `"my-disabled"`
- Assert the input field is disabled

#### checkReadonlyInput

- Navigate to the web form page
- Find the input field with name `"my-readonly"`
- Assert the `"readonly"` attribute is present

---

## ⚙️ Prerequisites

Before running the project, make sure you have the following installed:

- **Java JDK 19+** → [Download](https://www.oracle.com/java/technologies/downloads/)
- **Apache Maven** → [Download](https://maven.apache.org/download.cgi)
- **Google Chrome** (latest version)
- **Jenkins** (for CI/CD) → [Download](https://www.jenkins.io/download/)
- **Allure CLI** (for local report generation) → [Install Guide](https://allurereport.org/docs/v2/)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/EL-Gohary1/Test_Automation-ITI_Jenkins.git
cd Test_Automation-ITI_Jenkins
```

### 2. Run Tests

```bash
mvn clean test
```

This will execute all tests defined in `testng.xml`.

### 3. Generate Allure Report Locally

```bash
allure serve target/allure-results
```

This opens the Allure report in your default browser automatically.

---

## 🔧 TestNG Suite Configuration (`testng.xml`)

The suite runs the following test methods in order:

```
AlertTest            →  alertTest, promptAlertTest, promptAlertInFrameTest, promptAlertInNewWindowTest, alertInSelectTest
CookieBasedTest      →  getCookies
DynamicPageTest      →  checkButtonAddBox, checkRevealNewInput
MouseInteractionTest →  dragAndDrop, moveMouse, hoverMouse
PageWithFrameTest    →  switchToFrameThenToDefaultThenToNewWindow
WebFormTest          →  checkFormSubmittedMessage, checkDisabledInputIsDisable, checkReadonlyInput
```


---

## 🏗️ Jenkins CI/CD Integration

### Step 1 — Start Jenkins

Download the Jenkins WAR file from the [official website](https://www.jenkins.io/download/), then open CMD and run:

```bash
java -jar jenkins.war
```

Then open your browser and go to:
```
http://localhost:8080
```

---

### Step 2 — Install Allure Plugin

Go to **Manage Jenkins** → **Plugins** → **Available Plugins**, search for and install:

* **Allure Jenkins Plugin**

---

### Step 3 — Configure Tools

Go to **Manage Jenkins** → **Tools** and configure the following:

* **JDK** → Add the path to your Java installation on your machine
   - Example: `C:\Program Files\Java\jdk-19`

* **Maven** → Add the path to your Maven installation on your machine
   - Example: `C:\Program Files\Apache\maven`

* **Allure Commandline** → Add Allure and set the path to your Allure installation
   - Example: `C:\Program Files\allure`

---

### Step 4 — Create a Freestyle Job

1. Click **New Item** → Enter a name → Select **Freestyle Project** → Click OK
2. Under Source Code Management → Select Git: Add 
   - https://github.com/EL-Gohary1/Test_Automation-ITI_Jenkins.git
3. Under **Build Steps** → Add **Invoke top-level Maven targets**:
   ```
   Goals: clean test
   ```
4. Under **Post-build Actions** → Add **Allure Report**:
   - Set Results Path to: `allure-results`
5. Click **Save** then **Build Now**
