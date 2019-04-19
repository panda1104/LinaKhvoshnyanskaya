package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagePage extends AbstractBasePage{

    // TODO Нужно было сделать один элемент и один метод по работе с этим меню
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
