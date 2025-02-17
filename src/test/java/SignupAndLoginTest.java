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

            driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (Exception e) {
            System.err.println(" Error in setup: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testSignupAndLogin() {
        try {
            //  Signup
            WebElement signupButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/tvGoToSignup")));
            signupButton.click();

            //  signup form
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/etAdminUserName")));
            WebElement emailField = driver.findElement(By.id("com.example.elearningapp02:id/etAdminEmail"));
            WebElement passwordField = driver.findElement(By.id("com.example.elearningapp02:id/etAdminPassword"));
            WebElement confirmPasswordField = driver.findElement(By.id("com.example.elearningapp02:id/etAdminConfirmPassword"));
            WebElement signupSubmitButton = driver.findElement(By.id("com.example.elearningapp02:id/btnAdminSignup"));

            // Enter user details for signup
            usernameField.sendKeys("newUser");
            emailField.sendKeys("newuser@example.com");
            passwordField.sendKeys("password123");
            confirmPasswordField.sendKeys("password123");
            signupSubmitButton.click();

            
            try {
                WebElement signupSuccessMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/signupSuccessMessage")));
                System.out.println("Signup Successful");
            } catch (Exception e) {
                System.err.println("Signup failed or success message not found.");
            }

            //  Login
            // Navigate to login page after signup success
            WebElement loginEmailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/etAdminEmail")));
            WebElement loginPasswordField = driver.findElement(By.id("com.example.elearningapp02:id/etAdminPassword"));
            WebElement loginSubmitButton = driver.findElement(By.id("com.example.elearningapp02:id/btnAdminLogin"));

            loginEmailField.sendKeys("newuser@example.com");
            loginPasswordField.sendKeys("password123");
            loginSubmitButton.click();

            // After logging in, verify elements on the Dashboard
            WebElement dashboardTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Dashboard']")));
            WebElement userProfile = driver.findElement(By.id("com.example.elearningapp02:id/userProfile"));
            WebElement menuButton = driver.findElement(By.id("com.example.elearningapp02:id/menuButton"));

            // Assert that these elements are displayed
            assert dashboardTitle.isDisplayed();
            assert userProfile.isDisplayed();
            assert menuButton.isDisplayed();

            System.out.println("Test Passed: Dashboard loaded successfully with all expected elements.");

        } catch (Exception e) {
            System.err.println("Test Failed: " + e.getMessage());
        }
    }

}
