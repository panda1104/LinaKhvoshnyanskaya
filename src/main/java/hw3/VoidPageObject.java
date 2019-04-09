package hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// TODO Это не очень сильно похоже на PageObject
public class VoidPageObject {

    private WebDriver driver;
    private WebElement but;
    private WebElement usernameTextField;
    private WebElement passwordTextField;
    private WebElement loginButton;

    public VoidPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        usernameTextField = driver.findElement(By.id("username"));
        usernameTextField.sendKeys(username);
        loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        loginButton.click();
        passwordTextField = driver.findElement(By.id("password"));
        passwordTextField.sendKeys(password);
        loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
        loginButton.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean checkElementById(String id)
    {
        return driver.findElement(By.id(id)).isEnabled();
    }

    public void clickButByXpath(String xpath)
    {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickByLinkText(String link)
    {
        driver.findElement(By.linkText(link)).click();
    }

    public boolean checkContById(String id)
    {
        return driver.findElement(By.id(id)).isEnabled();
    }

    public void fillFields(String id, String inf)
    {
        but = driver.findElement(By.id(id));
        but.sendKeys(inf);
    }

    // TODO Из имени метода не понятно, что он делает
    public void forFields(String id, String inf)
    {
        driver.findElement(By.id(id)).sendKeys(inf);
    }


    public void clickByclassName(String classname) {
        driver.findElement(By.className(classname)).click();
    }
}
