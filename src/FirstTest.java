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

    private final String SKIP_BUTTON_ID = "org.wikipedia:id/fragment_onboarding_skip_button";
    private final String SEARCH_CONTAINER_ID = "org.wikipedia:id/search_container";
    private final String SEARCH_INPUT_ID = "org.wikipedia:id/search_src_text";
    private final String SEARCH_RESULT_TITLE_ID = "org.wikipedia:id/page_list_item_title";
    private final String SEARCH_CLOSE_BTN_ID = "org.wikipedia:id/search_close_btn";



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
    public void testAppLaunchesSuccessfully() {
        System.out.println("App launched successfully!");
    }

    @Test
    public void testSearchFieldText() {
        skipOnboarding();

        WebElement searchContainer = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_CONTAINER_ID))
        );

        WebElement searchText = searchContainer.findElement(By.className("android.widget.TextView"));
        assertEquals("Search Wikipedia", searchText.getText());
    }

    private void skipOnboarding() {
        WebElement skip = wait.until(ExpectedConditions.elementToBeClickable(By.id(SKIP_BUTTON_ID)));
        skip.click();
    }


    @Test
    public void testSearchResultsContainWord() {
        skipOnboarding();

        WebElement searchField = wait.until(
                ExpectedConditions.elementToBeClickable(By.id(SEARCH_CONTAINER_ID))
        );
        searchField.click();

        WebElement input = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id(SEARCH_INPUT_ID))
        );
        input.sendKeys("Java");

        List<WebElement> results = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(SEARCH_RESULT_TITLE_ID))
        );
        assertFalse("No search results found", results.isEmpty());

        driver.findElement(By.id(SEARCH_CLOSE_BTN_ID)).click();

        List<WebElement> clearedResults = driver.findElements(By.id(SEARCH_RESULT_TITLE_ID));
        assertEquals("Search results should be cleared after cancel", 0, clearedResults.size());
    }
}