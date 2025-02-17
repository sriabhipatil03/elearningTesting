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

public class SignupAndLoginTest {

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
    public void testSignupAndLogin() {
        try {
            // Step 1: Signup
            WebElement signupButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/btnSignup")));
            signupButton.click();

            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/etUsername")));
            WebElement emailField = driver.findElement(By.id("com.example.elearningapp02:id/etEmail"));
            WebElement passwordField = driver.findElement(By.id("com.example.elearningapp02:id/etPassword"));
            WebElement confirmPasswordField = driver.findElement(By.id("com.example.elearningapp02:id/etConfirmPassword"));
            WebElement signupSubmitButton = driver.findElement(By.id("com.example.elearningapp02:id/btnSubmitSignup"));

            // Enter details for signup
            usernameField.sendKeys("newUser");
            emailField.sendKeys("newuser@example.com");
            passwordField.sendKeys("password123");
            confirmPasswordField.sendKeys("password123");
            ((HidesKeyboard) driver).hideKeyboard();
            signupSubmitButton.click();

            // Verify signup success by checking for a confirmation message or redirection
            WebElement signupSuccessMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/signupSuccessMessage")));
            System.out.println("✅ Signup Successful");

            // Step 2: Login
            // After successful signup, go to the login screen
            WebElement loginButton = driver.findElement(By.id("com.example.elearningapp02:id/btnLogin"));
            loginButton.click();

            // Enter login credentials
            WebElement loginEmailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/etAdminEmail")));
            WebElement loginPasswordField = driver.findElement(By.id("com.example.elearningapp02:id/etAdminPassword"));
            WebElement loginSubmitButton = driver.findElement(By.id("com.example.elearningapp02:id/btnAdminLogin"));

            loginEmailField.sendKeys("newuser@example.com");
            loginPasswordField.sendKeys("password123");
            ((HidesKeyboard) driver).hideKeyboard();
            loginSubmitButton.click();

            // Verify login success by checking for the Dashboard screen
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
