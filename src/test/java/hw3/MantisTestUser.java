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

    private VoidPageObject vpo;

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
        vpo = new VoidPageObject(driver);
    }

    @Test
    public void addProject() {
        // Check title
        assertEquals(vpo.getPageTitle(), "MantisBT");

        // Login
        vpo.login("administrator", "rootroot");

        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");

        assertTrue(vpo.checkElementById("sidebar"));

        //Click Button "Manage"
        vpo.clickByLinkText("Manage");

        //Click button "Manage Users"
        vpo.clickByLinkText("Manage Users");

        //Press Create New Account
        vpo.clickButByXpath("//div[@class='pull-left']");

        //Check fields
        ArrayList<String> User = new ArrayList<String>();
        User.add("user-username");
        User.add("user-realname");
        User.add("email-field");
        User.add("user-password");
        User.add("user-verify-password");
        User.add("user-access-level");

        for(String field : User)
            assertTrue(vpo.checkContById(field));

        //Create New User
        vpo.fillFields("user-username", "qwerty");
        vpo.fillFields("user-realname", "qwerty");
        vpo.fillFields("email-field", "qwerty");
        vpo.fillFields("user-password", "qwerty");
        vpo.fillFields("user-verify-password", "qwerty");
        vpo.forFields("user-access-level", "reporter");

        vpo.clickButByXpath("//input[@value='Create User']");

        //LogOut

        vpo.clickByclassName("user-info");
        vpo.clickButByXpath("//a[@href='/logout_page.php']");

        assertEquals(vpo.getPageTitle(), "MantisBT");

        // Login
        vpo.login("qwerty", "qwerty");

        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "qwerty");

        //LogOut

        vpo.clickByclassName("user-info");
        vpo.clickButByXpath("//a[@href='/logout_page.php']");

    }


    @AfterMethod
    public void closeBrouser() {

        // Close driver
        driver.close();
    }


}
