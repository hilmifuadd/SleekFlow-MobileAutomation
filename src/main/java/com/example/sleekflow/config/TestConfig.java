package com.example.sleekflow.config;

public class TestConfig {
        public String mobileServerRemoteUrl() {
            return "http://localhost:4723/wd/hub";
        }
        public String mobilePlatformName() {
            return "Android";
        }
        public String mobileDeviceName() {
            return "emulator-5554";
        }
        public String mobileDeviceOsVersion() {
            return "9.0";
        }
        public String appUrl() {
            return "https://todoist.en.uptodown.com/android/download";
        }
        public String appName() {
            return "todoist.apk";
        }
        public String appPackage() {
            return "com.todoist";
        }
        public String appActivity() {
            return "com.todoist.activity.HomeActivity";
        }
}
