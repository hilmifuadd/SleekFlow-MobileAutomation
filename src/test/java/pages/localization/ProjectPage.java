package pages.localization;

import driver.ThreadLocalDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import pages.BasePage;
import utils.WaitUtils;


public class ProjectPage extends BasePage {
    public ProjectPage(ThreadLocalDriver driver){
        super(driver);
    }
    public static WaitUtils wait = new WaitUtils();
    @AndroidFindBy(id = "com.todoist:id/logo")
    AndroidElement landingPage;
    @AndroidFindBy(id = "com.todoist:id/btn_email")
    AndroidElement continueWithEmail;
    @AndroidFindBy(id = "com.todoist:id/email_login")
    AndroidElement loginWithEmail;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Login']")
    AndroidElement loginPage;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id ='email']")
    AndroidElement inputEmail;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id ='password']")
    AndroidElement inputPassword;
    @AndroidFindBy(className = "android.widget.Button")
    AndroidElement clickLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text ='Today']")
    AndroidElement validateLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Browse']")
    AndroidElement menuBrowse;
    @AndroidFindBy(id = "com.todoist:id/profile_view")
    AndroidElement pageProfile;
    @AndroidFindBy(className = "androidx.compose.ui.platform.ComposeView")
    AndroidElement composeProject;

   public void userChooseToLogin(){
       wait.waitForElementToBeVisibleAndroid(landingPage,ThreadLocalDriver.getTLDriver(), 500);
       continueWithEmail.click();
       wait.waitForElementToBeVisibleAndroid(loginWithEmail, ThreadLocalDriver.getTLDriver(), 500);
       loginWithEmail.click();
   }

   public void userDoingLogin(String email,String password){
       wait.waitForElementToBeVisibleAndroid(loginPage, ThreadLocalDriver.getTLDriver(), 500);
       inputEmail.sendKeys(email);
       inputPassword.sendKeys(password);
       clickLogin.click();
   }

   public void clickProject(){
       menuBrowse.click();
       wait.waitForElementToBeVisibleAndroid(pageProfile, ThreadLocalDriver.getTLDriver(), 500);
   }

//    public boolean isUserLoggedIn() {
//        try {
//            wait.waitForElementToBeVisibleAndroid(validateLogin, ThreadLocalDriver.getTLDriver(), 500);
//            return validateLogin.isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

   public void testValueIncreasing(){
       try {
           int previousValue = -1;
           boolean increasing = true;
           //check 5 times interval
           for (int i=0; i<5;i++){
               wait.staticWait(1000);
               int currentValue = getComposeViewValue();
               System.out.println("current value: "+ currentValue);
               if (previousValue != -1 && currentValue <= previousValue) {
                   increasing = false;
                   break;
               }
               previousValue = currentValue;
           }
           if (!increasing) {
               throw new AssertionError("The ComposeView's value is not increasing.");
           }

       } catch (Exception e) {
           System.out.println("Exception during test execution: " + e.getMessage());
       }
   }
    public int getComposeViewValue() {
        return Integer.parseInt(composeProject.getText());
    }

}
