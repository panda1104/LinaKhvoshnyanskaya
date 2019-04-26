package hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


// TODO Это не очень сильно похоже на PageObject
public class PageObjectVoid {

    private WebDriver driver;
    private WebElement usernameTextField;
    private WebElement passwordTextField;
    private WebElement loginButton;
    private WebElement leftMenu;
    private WebElement field;



    public PageObjectVoid(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String name, String password) {
        usernameTextField = driver.findElement(By.id("username"));
        usernameTextField.sendKeys(name);

        driver.findElement(By.xpath("//input[@value='Login']")).click();

        passwordTextField = driver.findElement(By.id("password"));
        passwordTextField.sendKeys(password);

        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void leftMenu(String str1, String str2) {

        driver.findElement(By.xpath("//*[@id='sidebar']"));

        //Click "selection1" button at the left side menu
        leftMenu = driver.findElement(By.partialLinkText(str1));
        leftMenu.click();


        driver.findElement(By.partialLinkText(str2)).click();

    }

    public void selectTextButton(String str, String name){
        driver.findElement(By.xpath("//"+ str +"[contains(text(), '" + name + "')]")).click();
    }


    public String checkField(Integer i){
        return driver.findElement(By.xpath("//tr["+i.toString()+"]/td[@class='category']")).getText();
    }

    public void userInformation(String id, String str) {

        driver.findElement(By.id(id)).sendKeys(str);
    }

    public void selectButton(String id, String str){

        driver.findElement(By.xpath("//*[@id='" + id + "']")).click();
        driver.findElement(By.xpath("//*[@id='" + id + "']/option[" + str + "]")).click();
    }

    public void clickBtnByClassName(String str) {

        driver.findElement(By.className(str)).click();
    }

    public void selectValueBtn(String str1, String str2){
        driver.findElement(By.xpath("//"+ str1 +"[@value='" + str2 + "']")).click();
    }




    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickButByXpath(String xpath)
    {
        driver.findElement(By.xpath(xpath)).click();
    }


    // TODO Из имени метода не понятно, что он делает


    public void clickByclassName(String classname) {
        driver.findElement(By.className(classname)).click();
    }
}
