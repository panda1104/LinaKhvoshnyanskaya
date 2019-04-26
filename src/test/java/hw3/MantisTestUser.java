package hw3;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

// TODO Java Code Convention for the variables names
public class MantisTestUser {

    private WebDriver driver;

    private PageObjectVoid pageObjectVoid;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(10000, TimeUnit.MILLISECONDS);

        // Open Mantis
        driver.get("http://mantis.tiulp.in/login_page.php");
        pageObjectVoid = new PageObjectVoid(driver);
    }

    @Test
    public void addUser() {
        // Check title
        assertEquals(pageObjectVoid.getPageTitle(), "MantisBT");

        // Login
        pageObjectVoid.login("administrator", "rootroot");

        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");

        //Click Button "Manage"
        pageObjectVoid.leftMenu("Manage", "Manage Users");

        //Press Create New Account
        pageObjectVoid.selectTextButton("a", "Create New Account");

        assertEquals(pageObjectVoid.getPageTitle(), "MantisBT");

        //Check fields
        assertEquals(pageObjectVoid.checkField(1), "Username");
        assertEquals(pageObjectVoid.checkField(2), "Real Name");
        assertEquals(pageObjectVoid.checkField(3), "E-mail");
        assertEquals(pageObjectVoid.checkField(4), "Password");
        assertEquals(pageObjectVoid.checkField(5), "Verify Password");
        assertEquals(pageObjectVoid.checkField(6), "Access Level");
        assertEquals(pageObjectVoid.checkField(7), "Enabled");
        assertEquals(pageObjectVoid.checkField(8), "Protected");



        //Create New User
        pageObjectVoid.userInformation("user-username", "qwerty");
        pageObjectVoid.userInformation("user-realname", "qwerty");
        pageObjectVoid.userInformation("email-field", "qwerty");
        pageObjectVoid.userInformation("user-password", "qwerty");
        pageObjectVoid.userInformation("user-verify-password", "qwerty");
        pageObjectVoid.selectButton("user-access-level", "2");

        pageObjectVoid.selectValueBtn("input", "Create User");
        //LogOut

        pageObjectVoid.clickByclassName("user-info");
        pageObjectVoid.clickButByXpath("//a[@href='/logout_page.php']");

        assertEquals(pageObjectVoid.getPageTitle(), "MantisBT");

        // Login
        pageObjectVoid.login("qwerty", "qwerty");

        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "qwerty");

        //LogOut

        pageObjectVoid.clickByclassName("user-info");
        pageObjectVoid.clickButByXpath("//a[@href='/logout_page.php']");

    }


    @AfterMethod
    public void closeBrouser() {

        // Close driver
        driver.close();
    }


}
