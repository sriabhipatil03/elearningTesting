import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class ManageCourseFragmentTest {

	 private AppiumDriver driver;
	    private WebDriverWait wait;

    @Test
    public void testManageCourseFragment() {
        // Navigate to Manage Course Fragment
        driver.findElement(By.id("com.example.elearningapp02:id/nav_manage_courses")).click();

        // Check if courses are listed or show "No courses available"
        WebElement courseList = driver.findElement(By.id("com.example.elearningapp02:id/lvCourses"));
        if (courseList.isDisplayed()) {
            System.out.println("Courses are listed.");
        } else {
        	WebElement emptyMessage = driver.findElement(By.id("com.example.elearningapp02:id/tvEmptyCourses"));
            assertTrue("No courses available", emptyMessage.isDisplayed());
            return;
        }

        // Select a course (first item)
        courseList.findElements(By.className("android.widget.TextView")).get(0).click();

        // Verify Edit and Delete options appear
        assertTrue(driver.findElement(By.xpath("//*[@text='Edit']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[@text='Delete']")).isDisplayed());

        // Click Edit
        driver.findElement(By.xpath("//*[@text='Edit']")).click();
        assertTrue(driver.findElement(By.id("com.example.elearningapp02:id/etCourseTitle")).isDisplayed());

        // Go back and test Delete
        driver.navigate().back();
        courseList.findElements(By.className("android.widget.TextView")).get(0).click();
        driver.findElement(By.xpath("//*[@text='Delete']")).click();
        driver.findElement(By.xpath("//*[@text='Yes']")).click();
        assertTrue(driver.findElement(By.id("com.example.elearningapp02:id/tvEmptyCourses")).isDisplayed());
    }



}
