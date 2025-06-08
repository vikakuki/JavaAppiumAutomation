import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.*;

public class FirstTest {

    private AppiumDriver driver;
    private WebDriverWait wait;


    @Before
    public void setUp() throws Exception {
        String appPath = System.getProperty("user.dir") + "/apks/org.wikipedia.apk";

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("AndroidTestDevice")
                .setAutomationName("UiAutomator2")
                .setApp(appPath)
                .setAppPackage("org.wikipedia")
                .setAppActivity("org.wikipedia.main.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void firstTest() {
        System.out.println("App launched successfully!");
    }

    @Test
    public void testSearchFieldText() {
        WebElement skipButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("org.wikipedia:id/fragment_onboarding_skip_button"))
        );
        skipButton.click();

        WebElement searchFieldContainer = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("org.wikipedia:id/search_container"))
        );

        WebElement searchText = searchFieldContainer.findElement(By.className("android.widget.TextView"));
        String actualText = searchText.getText();

        assertEquals("Search Wikipedia", actualText);
    }
}