package hw4;

// TODO Unused imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractBasePage{

    // TODO Нужно было сделать один элемент и один метод по работе с этим меню
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
