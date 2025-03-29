package LoginPage;

import HomePage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginBtn = By.xpath("//div/button[1]");


    public void enterUsername(String username)
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
    }

    public HomePage clickLogin()
    {
        driver.findElement(loginBtn).click();
        return new HomePage(driver);
    }
}
