package hw2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;
// TODO Комментарии ровно такие же как и для MantisTestUser.class
public class MantisTest {

    private WebDriver driver;
    private WebElement manageButton;

    @BeforeClass
    public void SetUp()
    {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resourses\\webdriver\\chrome\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://mantis.tiulp.in/login_page.php");


    }



    @Test(priority = 1)
    public void checkBrouserTitle()
    {
        assertEquals(driver.getTitle(), "MantisBT");
    }

    @Test(priority = 2)
    public void loginMantisBt() {

        // Check title
        assertEquals(driver.getTitle(), "MantisBT");

        // Login
        driver.findElement(By.id("username")).sendKeys("administrator");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

    }



    @Test (priority = 3)
    public void passwordMantisBt() {

        driver.findElement(By.id("password")).sendKeys("rootroot");
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");


    }

    @Test(priority = 4)
    public void leftSideMenu()
    {
        // Check menu
        assertTrue(driver.findElement(By.id("sidebar")).isEnabled());
    }

    @Test (priority = 5)
    public void manageButton()
    {
        // Check and Press button
        WebElement manageButton = driver.findElement(By.linkText("Manage"));
        manageButton.click();
    }

    @Test(priority = 6)
    public void ManageProject()
    {
        // Check and Press button
        WebElement manageProjectsButton = driver.findElement(By.linkText("Manage Projects"));

        assertTrue(manageProjectsButton.isEnabled());

        manageProjectsButton.click();


    }

    @Test(priority = 7)
    public void CreateNewProject()
    {
        //Check Fields
        WebElement createNewProjectButton = driver.findElement(By.xpath("//fieldset/button"));
        createNewProjectButton.click();

        WebElement projectName = driver.findElement(By.id("project-name"));
        WebElement status = driver.findElement(By.id("project-status"));
        WebElement inhetitGlobalCategories = driver.findElement(By.id("project-inherit-global"));
        WebElement viewStatus = driver.findElement(By.id("project-view-state"));
        WebElement description = driver.findElement(By.id("project-description"));

        SoftAssert asert = new SoftAssert();

        asert.assertTrue(projectName.isEnabled());
        asert.assertTrue(status.isEnabled());
        asert.assertTrue(inhetitGlobalCategories.isEnabled());
        asert.assertTrue(viewStatus.isEnabled());
        asert.assertTrue(description.isEnabled());
        asert.assertAll();
    }

    @Test(priority = 8)
    public void FillFields()
    {
        //Fill fields
        driver.findElement(By.xpath("//*[@id=\"project-name\"]")).sendKeys("qwerty");
        Select dropdown1 = new Select(driver.findElement(By.id("project-status")));
        dropdown1.selectByValue("10");

        driver.findElement(By.className("lbl")).click();

        Select dropdown2 = new Select(driver.findElement(By.id("project-view-state")));
        dropdown2.selectByValue("10");

        driver.findElement(By.xpath("//*[@id=\"project-description\"]")).sendKeys("qwerty");

        driver.findElement(By.xpath("//input[@value='Add Project']")).click();

    }

    @Test(priority = 9)
    public void LogOut()
    {
        //Press Log Out
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();
    }


    @AfterClass
    public void closeBrouser() {

        // Close driver
           driver.close();
    }

}
