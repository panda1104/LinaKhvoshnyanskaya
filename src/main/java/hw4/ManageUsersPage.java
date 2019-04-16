package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageUsersPage extends AbstractBasePage{

    @FindBy(xpath = "//div[@class='pull-left']")
    WebElement createUserButton;

    public ManageUsersPage(WebDriver driver)
    {
        super(driver);
    }

    public void createNewUser() {
        createUserButton.click();
    }

}
