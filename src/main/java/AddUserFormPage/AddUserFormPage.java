package AddUserFormPage;

import AdminTabPage.AdminTabPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddUserFormPage {
    private WebDriver driver;
    public AddUserFormPage(WebDriver driver)
    {
        this.driver = driver;
    }
    private By usernameField = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
    private By employeeNameField = By.xpath("//div[@class='oxd-autocomplete-text-input--before']/following-sibling::input");
    private By DDL = By.xpath("//div[text()='-- Select --']");
    private By DDL2 = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]");
    private By passwordField = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    private By confirmPasswordField = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    private By saveUser = By.xpath("//button[text()=' Save ']");


    public void setUserName(String name)
    {
        driver.findElement(usernameField).sendKeys(name);
    }
    public void setEmployeeName(String employeeName) {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        // Click the field and enter the name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(employeeNameField));
        nameField.click();
        nameField.sendKeys(employeeName);

        // Add a short delay to allow suggestions to populate
        try {
            Thread.sleep(10000);  // Short delay (1 second)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Wait for the suggestions list to be visible
        By suggestionList = By.xpath("//div[contains(@class,'autocomplete')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionList));

        // Select the first suggestion
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
    }
    public void setPassword(String password)
    {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void confirmPassword(String password)
    {
        driver.findElement(confirmPasswordField).sendKeys(password);
    }
    public void chooseRole() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        // Click the dropdown to open it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(DDL));
        dropdown.click();

        // Press Arrow Down twice and then Enter to select
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public void chooseStatus()
    {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        // Click the dropdown to open it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(DDL2));
        dropdown.click();

        // Press Arrow Down twice and then Enter to select
        actions.sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public AdminTabPage submitUser()
    {
        driver.findElement(saveUser).click();
        return new AdminTabPage(driver);
    }
}
