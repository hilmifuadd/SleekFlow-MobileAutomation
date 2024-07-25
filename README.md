### Required Environment :
* Node.js
* Appium (install via terminal/cmd after downloading Node.js or install the Appium Desktop version)
* IntelliJ IDEA with Cucumber for Java, Gherkin, and TestNG plugins
* JDK
* Android SDK Tools
* Chromedriver matching the Chrome version on the device (https://chromedriver.chromium.org/downloads)
* POM Builder plugin for Chrome -> to easily fetch path/id from the webpage

### Installation : 
* Download and install IntelliJ IDEA, JDK, Android SDK Tools, and Node.js.
* Open Terminal/CMD and type npm install -g appium to install Appium (Skip this step if you installed the Appium Desktop version). Check the installation by typing appium -v in the terminal/CMD.

#### Setting environment :
##### MacOS &Linux
1. Open Terminal/CMD. 
2. Type sudo nano ~/.bash_profile. 
3. On the first line, type the following (adjust according to your installation directory):
```
export JAVA_HOME="$(/usr/libexec/java_home -v 1.8)"
export ANDROID_HOME="/Users/<username>/Library/Android/sdk"
export PATH="${ANDROID_HOME}/tools:${PATH}"
export PATH="${ANDROID_HOME}/tools/emulator:${PATH}"
export PATH="${ANDROID_HOME}/platform-tools:${PATH}"
export PATH="${ANDROID_HOME}/platforms"
export PATH="${ANDROID_HOME}/tools/bin:${PATH}"
```
4. After entering the values, press ctrl+x, and select Y to save the configuration. 
5. Type source ~/.bash_profile to apply the configuration. 
6. Check if the installation is complete by typing adb devices in the terminal. If there are no errors, the installation is successful.

##### Windows
1. Open My Computer, then click This PC. 
2. On the left menu, select Advanced System Settings. 
3. In the popup, select the Advanced tab, then click Environment Variables. 
4. In the User Variables section, add a new variable named ANDROID_HOME with the value of the Android SDK installation directory, e.g., C:\Android\Android-sdk. 
5. Also, add a variable named JAVA_HOME with the value of the JDK installation directory, e.g., C:\Program Files\Java\jdk1.8.0\. 
6. Click the Path variable, then add the following values:
```
%ANDROID_HOME%\tools
%ANDROID_HOME%\build-tools
%ANDROID_HOME%\platform-tools
%JAVA_HOME%\bin

```
7. Save all settings and restart the computer. After that, type adb devices in the terminal. If there are no errors, the setup is successful.

#### Running the Project Locally:
* Connect your device to the computer, then type adb devices and check if the device ID appears. If the device ID list is empty:
    * Check if the device is in developer mode: Open Settings > System > Is there a Developer Options menu? If not, enable it by going to Settings > About phone > tap the build number 7 times.
    * Ensure USB debugging is enabled in Developer Options and allow connection from the computer. 
    * Make sure the cable used is a USB debug cable.
*  Open the project with IntelliJ IDEA.
*  Copy the entire content of the BaseTest.java.example file and paste it into the BaseTest.java file.
*  Change the chromedriverExecutable value to the folder where chromedriver is extracted, including the filename without the .exe extension.
*  If you want to test the Todoist application, in the src/test/resources/config.properties file, set android.appPackage=com.todoist and android.appActivity=com.todoist.activity.HomeActivity.
*  In the testng.xml file, change deviceName to the device ID from the previous step and platformVersion to the Android version on the device.
*  Ensure the Todoist application (either dev or prod version) is installed on the device.
*  Right-click the testng.xml file and select Run. 
*  Alternatively, in Terminal/CMD, type the command `mvn clean install test -DsuiteXmlFile=<file_xml>`.