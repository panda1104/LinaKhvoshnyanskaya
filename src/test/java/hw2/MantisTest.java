package hw2;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebElement;

import java.util.Objects;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static java.lang.System.setProperty;
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
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();
        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");


    }

    @Test(priority = 4)
    public void leftSideMenu()
    {
        // Check menu
        assertTrue(driver.findElement(By.id("sidebar")).isDisplayed());
    }

    @Test (priority = 5)
    public void manageButton()
    {
        // Check and Press button
        driver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[7]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[7]/a")).click();
    }

    @Test(priority = 6)
    public void ManageProject()
    {
        // Check and Press button
        assertTrue(driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[3]/a")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[3]/a")).click();
    }

    @Test(priority = 7)
    public void CreateNewProject()
    {
        //Check Fields
        assertTrue(driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/form/fieldset/button")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[1]/form/fieldset/button")).click();
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[1]/td[1]")).getText(), "* Project Name");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[2]/td[1]")).getText(), "Status");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[3]/td[1]")).getText(), "Inherit Global Categories");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[4]/td[1]")).getText(), "View Status");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[2]/div/div/table/tbody/tr[5]/td[1]")).getText(), "Description");
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

        driver.findElement(By.xpath("//*[@id=\"manage-project-create-form\"]/div/div[3]/input")).click();

    }

    @Test(priority = 9)
    public void LogOut()
    {
        //Press Log Out
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[@href='/logout_page.php']")).click();
    }


    @AfterClass
    public void closeBrouser() {

        // Close driver
           driver.close();
    }

}
