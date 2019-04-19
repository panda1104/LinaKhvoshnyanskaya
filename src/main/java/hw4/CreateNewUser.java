package hw4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewUser extends AbstractBasePage{


    @FindBy(id = "user-username")
    private WebElement userName;

    @FindBy(id = "user-realname")
    private WebElement realName;

    @FindBy(id = "email-field")
    private WebElement email;

    @FindBy(id = "user-password")
    private WebElement password;

    @FindBy(id = "user-verify-password")
    private WebElement verifyPassword;

    @FindBy(xpath = "//*[@id='user-access-level']")
    private WebElement accessLevelFirst;

    @FindBy(xpath = "//*[@id='user-access-level']/option[2]")
    private WebElement accessLevelSecond;

    @FindBy(xpath = "//input[@value='Create User']")
    private WebElement addUser;

    public CreateNewUser(WebDriver driver)
    {
        super(driver);
    }

    // TODO Java Code convention
    public void FillFields ()
    {
        // TODO не должно быть захардкоженных данных
        userName.sendKeys("qwerty");
        realName.sendKeys("qwerty");
        email.sendKeys("qwerty");
        password.sendKeys("qwerty");
        verifyPassword.sendKeys("qwerty");

        accessLevelFirst.click();
        accessLevelSecond.click();
    }

    public void addUser()
    {
        addUser.click();
    }




}
