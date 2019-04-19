package hw4;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class IssuesPage extends AbstractBasePage{

    @FindBy(id = "show_priority_filter")
    WebElement priority;

    @FindBy(id = "show_severity_filter")
    WebElement severity;

    @FindBy(id = "show_status_filter")
    WebElement status;

    @FindBy(name = "start_year")
    WebElement startYear;

    @FindBy(name = "start_month")
    WebElement startMonth;

    @FindBy(name = "start_day")
    WebElement startDay;

    @FindBy(name = "end_year")
    WebElement endYear;

    @FindBy(name = "end_month")
    WebElement endMonth;

    @FindBy(name = "end_day")
    WebElement endDay;

    @FindBy(xpath = "//input[@value='Apply Filter']")
    WebElement applyFilter;

    @FindBy(className = "badge")
    WebElement filterText;

    public IssuesPage(WebDriver driver)
    {
        super(driver);
    }

    private void selectMenu(String id, String number){
        driver.findElement(By.id(id)).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='" + id + "']/select/option[" + number + "]"))).click();
    }

    public void setPriority(){
        priority.click();
        // TODO не должно быть захардкоженных данных
        selectMenu("show_priority_filter_target", "5");
        //prioritySelectFirst.click();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(prioritySelectSecond)).click();
    }

    public void setSeverity()
    {
        // TODO не должно быть захардкоженных данных
        severity.click();
        selectMenu("show_severity_filter_target", "5");
    }

    public void setStatus()
    {
        status.click();
        selectMenu("show_status_filter_target", "6");
    }

    public void setFilterByDateSubmitted()
    {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("do_filter_by_date_filter"))).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.name("start_year"))).click();

    }

    public void setStartYear()
    {
        // TODO не должно быть захардкоженных данных
        this.startYear.click();
        Select select = new Select(this.startYear);
        select.selectByVisibleText("2019");
    }

    public void setStartMonth()
    {
        // TODO не должно быть захардкоженных данных
        this.startMonth.click();
        Select select = new Select(this.startMonth);
        select.selectByVisibleText("March");
    }
    public void setStartDay()
    {
        // TODO не должно быть захардкоженных данных
        this.startDay.click();
        Select select = new Select(this.startDay);
        select.selectByVisibleText("27");
    }

    public void setEndYear()
    {
        // TODO не должно быть захардкоженных данных
        this.endYear.click();
        Select select = new Select(this.endYear);
        select.selectByVisibleText("2019");
    }

    public void setEndMonth()
    {
        // TODO не должно быть захардкоженных данных
        this.endMonth.click();
        Select select = new Select(this.endMonth);
        select.selectByVisibleText("April");
    }
    public void setEndDay()
    {
        // TODO не должно быть захардкоженных данных
        this.endDay.click();
        Select select = new Select(this.endDay);
        select.selectByVisibleText("1");
    }

    public void applyFilter()
    {
        applyFilter.click();
    }

    boolean checkFilterAddition()
    {
        if(filterText.getText() == "0 - 0 / 0")
            return false;
        return true;
    }


}

