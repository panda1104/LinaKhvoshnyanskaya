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

        //Click button "Manage Projects"
        vpo.clickByLinkText("Manage Projects");

        //Click button "Create New Project"
        vpo.clickButByXpath("//button[.='Create New Project']");

        //Check fields
        ArrayList<String> Project = new ArrayList<String>();
        Project.add("project-name");
        Project.add("project-status");
        Project.add("project-inherit-global");
        Project.add("project-view-state");
        Project.add("project-description");

        for(String field : Project)
            assertTrue(vpo.checkContById(field));


        // Fill fields
        vpo.fillFields("project-name", "qwerty");
        vpo.forFields("project-status", "release");
        vpo.clickButByXpath("//span[@class='lbl']");
        vpo.forFields("project-view-state", "public");
        vpo.fillFields("project-description", "qwerty");

        vpo.clickButByXpath("//input[@value='Add Project']");

        vpo.clickByclassName("user-info");

        vpo.clickButByXpath("//a[@href='/logout_page.php']");


    }

    @AfterMethod
    public void closeBrouser() {

        // Close driver
        driver.close();
    }

}
