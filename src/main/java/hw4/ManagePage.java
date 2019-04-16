package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagePage extends AbstractBasePage{

    @FindBy(linkText = "Manage Users")
    WebElement manageUsersButton;


    public ManagePage(WebDriver driver)
    {
        super(driver);
    }

    public void openManageUsersPage()
    {
        manageUsersButton.click();
    }


}
