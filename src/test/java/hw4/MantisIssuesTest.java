package hw4;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MantisIssuesTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private ManagePage managePage;
    private ManageUsersPage manageUsersPage;
    private CreateNewUser createNewUser;
    private IssuesPage issuesPage;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        ChromeDriverManager.chromedriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open Mantis
        driver.get("https://mantis.tiulp.in/");

        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        managePage = new ManagePage(driver);
        manageUsersPage=new ManageUsersPage(driver);
        createNewUser=new CreateNewUser(driver);
        issuesPage = new IssuesPage(driver);
    }

    @Test
    public void addIssue() {
        //Login
        loginPage.login("administrator", "rootroot");

        //check leftsidebar
        assertTrue(mainPage.leftSideBarExists());

        mainPage.openViewIssuesPage();



        issuesPage.setPriority();
        issuesPage.setSeverity();
        issuesPage.setStatus();

        issuesPage.setFilterByDateSubmitted();

        issuesPage.setStartYear();
        issuesPage.setStartMonth();
        issuesPage.setStartDay();
        issuesPage.setEndYear();
        issuesPage.setEndMonth();
        issuesPage.setEndDay();

        issuesPage.applyFilter();

        assertTrue(issuesPage.checkFilterAddition());
        issuesPage.logout();

    }

   @AfterMethod
   public void closeDriver()
   {
       issuesPage.closeDriver();
   }
}
