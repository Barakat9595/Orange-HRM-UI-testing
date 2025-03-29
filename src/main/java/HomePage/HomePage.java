package HomePage;

import AdminTabPage.AdminTabPage;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage {
    private WebDriver driver;
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }
    private By pageTitle = By.className("oxd-topbar-header-title");
    private By adminTab = By.xpath("//li/a[1]/span");


    public String getPageTitle()
    {
        return driver.findElement(pageTitle).getText();
    }
    public String getPageUrl()
    {
        return  driver.getCurrentUrl();
    }

    public AdminTabPage switchToAdminTab()
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(adminTab).click();
        return new AdminTabPage(driver);
    }

}
