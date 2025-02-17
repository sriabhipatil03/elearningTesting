import io.appium.java_client.AppiumDriver;
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

public class DashboardFragmentTest extends  SignupAndLoginTest{

    private AppiumDriver driver;
    private WebDriverWait wait;

    @Test
    public void testDashboardFragment() {
        try {

            // After login, navigate to Dashboard Fragment
            WebElement dashboardTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='Dashboard']")));
            WebElement userProfile = driver.findElement(By.id("com.example.elearningapp02:id/userProfile"));
            WebElement menuButton = driver.findElement(By.id("com.example.elearningapp02:id/menuButton"));
            WebElement welcomeText = driver.findElement(By.id("com.example.elearningapp02:id/textWelcome"));

            // Assert that the dashboard elements are displayed
            assert dashboardTitle.isDisplayed();
            assert userProfile.isDisplayed();
            assert menuButton.isDisplayed();
            assert welcomeText.isDisplayed();
            assert welcomeText.getText().contains("Welcome");

            System.out.println("Test Passed: DashboardFragment loaded successfully with all expected elements.");
            
        } catch (Exception e) {
            System.err.println("Test Failed: " + e.getMessage());
        }
    }

}
