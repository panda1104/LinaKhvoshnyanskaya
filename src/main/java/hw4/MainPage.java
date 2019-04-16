package hw4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractBasePage{

    @FindBy(linkText = "View Issues")
    WebElement viewIssuesButton;

    @FindBy(linkText = "Manage")
    WebElement manageButton;

    @FindBy(id = "sidebar")
    WebElement leftSideBar;


    public MainPage(WebDriver driver)
    {
        super(driver);
    }

    public void openManagePage()
    {
        manageButton.click();
    }

    public boolean leftSideBarExists()
    {
        return leftSideBar.isEnabled();
    }

    public void openViewIssuesPage(){viewIssuesButton.click();}
}
