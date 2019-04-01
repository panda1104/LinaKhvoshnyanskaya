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

public class MantisTestUser {

    private WebDriver driver;
    private WebElement manageButton;

    @BeforeClass
    public void SetUp()
    {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\webdriver\\chrome\\chromedriver.exe");
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
    public void ManageUser()
    {
        // Check and Press button
        assertTrue(driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[2]/a")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/ul/li[2]/a")).click();
    }

    @Test(priority = 7)
    public void CreateAccount()
    {
        //Press Create New Account
        assertTrue(driver.findElement(By.xpath("//*[@id=\"manage-user-div\"]/div[1]/a")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"manage-user-div\"]/div[1]/a")).click();

        //Check Fields
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[1]/td[1]")).getText(), "Username");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[2]/td[1]")).getText(), "Real Name");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[3]/td[1]")).getText(), "E-mail");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[4]/td[1]")).getText(), "Password");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[5]/td[1]")).getText(), "Verify Password");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[6]/td[1]")).getText(), "Access Level");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[7]/td[1]")).getText(), "Enabled");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[2]/div/div/table/tbody/tr[8]/td[1]")).getText(), "Protected");


        //Fill fields
        driver.findElement(By.id("user-username")).sendKeys("qwerty");

        driver.findElement(By.id("user-realname")).sendKeys("qwerty");

        driver.findElement(By.id("email-field")).sendKeys("qwerty");

        driver.findElement(By.id("user-password")).sendKeys("qwerty");

        driver.findElement(By.id("user-verify-password")).sendKeys("qwerty");

        Select dropdown1 = new Select(driver.findElement(By.id("user-access-level")));
        dropdown1.selectByValue("25");

        driver.findElement(By.xpath("//*[@id=\"manage-user-create-form\"]/div/div[3]/input")).click();

    }

    @Test(priority = 8)
    public void LogOut()
    {
        //Press Log Out
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[@href='/logout_page.php']")).click();
    }

    @Test(priority = 9)
    public void NewLogIn()
    {
        // Check title
        assertEquals(driver.getTitle(), "MantisBT");

        // Login
        driver.findElement(By.id("username")).sendKeys("qwerty");
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        driver.findElement(By.id("password")).sendKeys("qwerty");
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]")).click();
        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "qwerty");

    }

    @Test(priority = 10)
    public void LogOut1()
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
