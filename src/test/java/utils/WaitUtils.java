package utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitUtils {
    public final int explicitWaitDefault = PropertyUtils.getIntegerProperty("explicitWait", 20);

    /**
     * This method is for static wait
     *
     * @param millis
     */
    public void staticWait(final long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (final InterruptedException e) {
        }
    }

    /**
     * To wait for button to be clickable
     *
     * @param driver
     * @param element
     */
    public void waitForElementToBeClickable(final WebElement element, final WebDriver driver) {
        new WebDriverWait(driver, this.explicitWaitDefault).until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * To wait for element (By) to be invisible
     *
     * @param driver
     * @param locator
     */
    public void waitForElementToBeInvisible(final By locator,
                                            final WebDriver driver) {
        long s = System.currentTimeMillis();
        new WebDriverWait(driver, this.explicitWaitDefault).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * To wait for given element (By) to be present
     *
     * @param driver
     * @param locator
     */
    public void waitForElementToBePresent(final By locator, final WebDriver driver) {
        new WebDriverWait(driver, this.explicitWaitDefault).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * To wait for element to be visible
     *
     * @param driver
     * @param element
     */
    public void waitForElementToBeVisible(final WebElement element, final WebDriver driver) {
        long s = System.currentTimeMillis();
        new WebDriverWait(driver, this.explicitWaitDefault).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * To wait for element to be visible for given amount of
     * time
     *
     * @param element
     */

    public void waitForElementToBeVisibleAndroid(final AndroidElement element, final WebDriver driver, int time) {
        long s = System.currentTimeMillis();
        new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForElementsToBeVisibleList(final List<MobileElement> element, final WebDriver driver, int index) {
        final long s = System.currentTimeMillis();
        new WebDriverWait(driver, this.explicitWaitDefault).until(ExpectedConditions.visibilityOf(element.get(index)));
    }

    public void waitForElementToBeNotPresent(final By element, WebDriver driver) {
        long s = System.currentTimeMillis();
        new WebDriverWait(driver, this.explicitWaitDefault).until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(element)));
    }
    public void waitUntilNestedElementPresent(WebElement element, By locator, WebDriver driver) {
        new WebDriverWait(driver, explicitWaitDefault).until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
    }
}
