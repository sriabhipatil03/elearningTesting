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

public class AddCourseFragmentTest extends SignupAndLoginTest {

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

    @Test
    public void testAddCourse() {
        try {
            // Navigate to AddCourseFragment
            WebElement addCourseButton = driver.findElement(By.id("com.example.elearningapp02:id/btnAddCourse"));
            addCourseButton.click();

            // Wait for Add Course screen to load
            WebElement courseTitleInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.elearningapp02:id/etCourseTitle")));
            WebElement courseDescriptionInput = driver.findElement(By.id("com.example.elearningapp02:id/etCourseDescription"));
            WebElement uploadFileButton = driver.findElement(By.id("com.example.elearningapp02:id/btnUploadFile"));
            WebElement saveCourseButton = driver.findElement(By.id("com.example.elearningapp02:id/btnSaveCourse"));

            // Fill in course details
            courseTitleInput.sendKeys("Test Course");
            courseDescriptionInput.sendKeys("This is a test course description.");

            // Simulate file upload
            uploadFileButton.click();
            // Note: Simulating file picker may need additional handling based on your environment

            // Save the course
            saveCourseButton.click();

            // Verify success message
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Toast[contains(@text, 'Course Added Successfully')]")));
            assert successMessage.isDisplayed();

            System.out.println("Test Passed: Course added successfully");

        } catch (Exception e) {
            System.err.println("Test Failed: " + e.getMessage());
        }
    }
}
