import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest {

    private AppiumDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Xiaomi Redmi Note 8");
            caps.setCapability("appPackage", "com.example.elearningapp02");
            caps.setCapability("appActivity", "com.example.elearningapp02.MainActivity");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

            // 🔥 Try using "localhost" instead of "127.0.0.1"
            driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        } catch (Exception e) {
            System.err.println("❌ Error in setup: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testAdminLogin() {
        try {
            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/etAdminEmail")));
            WebElement passwordField = driver.findElement(By.id("com.example.elearningapp02:id/etAdminPassword"));
            WebElement loginButton = driver.findElement(By.id("com.example.elearningapp02:id/btnAdminLogin"));

            emailField.sendKeys("tom@as.com");
            passwordField.sendKeys("1212");
            ((HidesKeyboard) driver).hideKeyboard();
            loginButton.click();

            WebElement dashboardTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Dashboard']")));
            System.out.println("✅ Test Passed: Login successful.");
        } catch (Exception e) {
            System.err.println("❌ Test Failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
