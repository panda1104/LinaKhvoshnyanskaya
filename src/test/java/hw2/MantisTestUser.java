package hw2;

// TODO unused imports
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
import org.testng.asserts.SoftAssert;

// TODO Class неотформатирован по Java Code Convention (CTRL + ALT + L)
// TODO Имена методов не по Java Code Convention
public class MantisTestUser {

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
        // TODO Автосгенерированный локатор
        driver.findElement(By.xpath("//input[@value='Login']")).click();
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
        // TODO Не понятно на какой элемент кликается
        // TODO Автосгенерированный локатор
        driver.findElement(By.linkText("Manage")).click();
    }

    @Test(priority = 6)
    public void ManageUser()
    {
        // Check and Press button
        // TODO Не понятно на какой элемент кликается
        // TODO Автосгенерированный локатор
        assertTrue(driver.findElement(By.linkText("Manage Users")).isDisplayed());
        driver.findElement(By.linkText("Manage Users")).click();
    }

    @Test(priority = 7)
    public void CreateAccount()
    {
        //Press Create New Account
        // TODO Не понятно на какой элемент кликается
        // TODO Автосгенерированный локатор
        assertTrue(driver.findElement(By.linkText("Create New Account")).isDisplayed());
        driver.findElement(By.linkText("Create New Account")).click();

        //Check Fields
        // TODO Не понятно на какой элемент кликается
        // TODO Автосгенерированные локаторы

        //Check fields on the "Create New Account" view
        assertEquals(driver.findElement(By.xpath("//tr[1]/td[@class='category']")).getText(), "Username");
        assertEquals(driver.findElement(By.xpath("//tr[2]/td[@class='category']")).getText(), "Real Name");
        assertEquals(driver.findElement(By.xpath("//tr[3]/td[@class='category']")).getText(), "E-mail");
        assertEquals(driver.findElement(By.xpath("//tr[4]/td[@class='category']")).getText(), "Password");
        assertEquals(driver.findElement(By.xpath("//tr[5]/td[@class='category']")).getText(), "Verify Password");
        assertEquals(driver.findElement(By.xpath("//tr[6]/td[@class='category']")).getText(), "Access Level");
        assertEquals(driver.findElement(By.xpath("//tr[7]/td[@class='category']")).getText(), "Enabled");
        assertEquals(driver.findElement(By.xpath("//tr[8]/td[@class='category']")).getText(), "Protected");

        //Fill user information
        driver.findElement(By.id("user-username")).sendKeys("qwerty");
        driver.findElement(By.id("user-realname")).sendKeys("qwerty");
        driver.findElement(By.id("email-field")).sendKeys("qwerty");
        driver.findElement(By.id("user-password")).sendKeys("qwerty");
        driver.findElement(By.id("user-verify-password")).sendKeys("qwerty");
        driver.findElement(By.xpath("//*[@id='user-access-level']")).click();
        driver.findElement(By.xpath("//*[@id='user-access-level']/option[2]")).click();

        //Click "Create User" button
        driver.findElement(By.xpath("//input[@value='Create User']")).click();

    }

    @Test(priority = 8)
    public void LogOut()
    {
        //Press Log Out
        driver.findElement(By.className("user-info")).click();
        // TODO Локатор может быть короче
        driver.findElement(By.xpath("//a[contains(., 'Logout')]")).click();
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
        // TODO Не понятно на какой элемент кликается
        // TODO Автосгенерированный локатор
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "qwerty");

    }

    @Test(priority = 10)
    public void LogOut1()
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
