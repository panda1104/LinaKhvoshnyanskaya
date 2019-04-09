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

        assertTrue(pageObjectVoid.checkElementById("sidebar"));

        //Click Button "Manage"
        pageObjectVoid.clickByLinkText("Manage");

        //Click button "Manage Projects"
        pageObjectVoid.clickByLinkText("Manage Projects");

        //Click button "Create New Project"
        pageObjectVoid.clickButByXpath("//button[.='Create New Project']");

        //Check fields
        ArrayList<String> Project = new ArrayList<String>();
        Project.add("project-name");
        Project.add("project-status");
        Project.add("project-inherit-global");
        Project.add("project-view-state");
        Project.add("project-description");

        for(String field : Project)
            assertTrue(pageObjectVoid.checkElementById(field));


        // Fill fields
        pageObjectVoid.fillFields("project-name", "qwerty");
        pageObjectVoid.fillFields("project-status", "release");
        pageObjectVoid.clickButByXpath("//span[@class='lbl']");
        pageObjectVoid.fillFields("project-view-state", "public");
        pageObjectVoid.fillFields("project-description", "qwerty");

        pageObjectVoid.clickButByXpath("//input[@value='Add Project']");

        pageObjectVoid.clickByclassName("user-info");

        pageObjectVoid.clickButByXpath("//a[@href='/logout_page.php']");


    }

    @AfterMethod
    public void closeBrouser() {

        // Close driver
        driver.close();
    }

}
