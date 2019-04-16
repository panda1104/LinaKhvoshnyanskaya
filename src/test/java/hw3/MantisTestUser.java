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

// TODO Тест не читаем
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

        assertTrue(pageObjectVoid.checkElementById("sidebar"));

        //Click Button "Manage"
        pageObjectVoid.clickByLinkText("Manage");

        //Click button "Manage Users"
        pageObjectVoid.clickByLinkText("Manage Users");

        //Press Create New Account
        pageObjectVoid.clickButByXpath("//div[@class='pull-left']");

        //Check fields
        ArrayList<String> User = new ArrayList<String>();
        User.add("user-username");
        User.add("user-realname");
        User.add("email-field");
        User.add("user-password");
        User.add("user-verify-password");
        User.add("user-access-level");

        for(String field : User)
            assertTrue(pageObjectVoid.checkElementById(field));

        //Create New User
        pageObjectVoid.fillFields("user-username", "qwerty");
        pageObjectVoid.fillFields("user-realname", "qwerty");
        pageObjectVoid.fillFields("email-field", "qwerty");
        pageObjectVoid.fillFields("user-password", "qwerty");
        pageObjectVoid.fillFields("user-verify-password", "qwerty");
        pageObjectVoid.fillFields("user-access-level", "reporter");

        pageObjectVoid.clickButByXpath("//input[@value='Create User']");

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
