package hw4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractBasePage{

    @FindBy(id = "username")
    WebElement usernameTextField;

    @FindBy(id = "password")
    WebElement passwordTextField;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginButton;
    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    void login(String username, String password)
    {
        usernameTextField.sendKeys(username);
        loginButton.click();
        passwordTextField.sendKeys(password);
        loginButton.click();
    }
}
