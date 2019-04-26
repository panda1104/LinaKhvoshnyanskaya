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
public class MantisTest {

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
    public void addProject() {
        // Check title
        assertEquals(pageObjectVoid.getPageTitle(), "MantisBT");

        // Login
        pageObjectVoid.login("administrator", "rootroot");

        // Check login
        assertEquals(driver.findElement(By.className("user-info")).getText(),
                "administrator");

        //Click Button "Manage"
        pageObjectVoid.leftMenu("Manage", "Manage Projects");

        //Click button "Create New Project"
        pageObjectVoid.selectTextButton("button", "Create New Project");


        //Check fields
        assertEquals(pageObjectVoid.checkField(1), "* Project Name");
        assertEquals(pageObjectVoid.checkField(2), "Status");
        assertEquals(pageObjectVoid.checkField(3), "Inherit Global Categories");
        assertEquals(pageObjectVoid.checkField(4), "View Status");
        assertEquals(pageObjectVoid.checkField(5), "Description");

        //Fill fields
        pageObjectVoid.userInformation("project-name", "qwerty");
        pageObjectVoid.selectButton("project-status", "1");
        pageObjectVoid.clickBtnByClassName("lbl");
        pageObjectVoid.selectButton("project-view-state", "1");
        pageObjectVoid.userInformation("project-description", "qwerty");

        //Press button "Add Project"
        pageObjectVoid.selectValueBtn("input", "Add Project");

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
