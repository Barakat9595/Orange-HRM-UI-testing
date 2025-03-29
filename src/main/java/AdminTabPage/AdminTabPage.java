package AdminTabPage;

import AddUserFormPage.AddUserFormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AdminTabPage {
    private static WebDriver driver;
    public AdminTabPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private static By usersCount = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");
    private By addUserBtn = By.xpath("//button[text()=' Add ']");

    private By usernameSearchField = By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']/div[2]/input[1]");
    private By searchBtn = By.xpath("//button[@type='submit']");
    private By pageFooter = By.className("oxd-layout-footer");
    private By resetBtn = By.xpath("//button[@type='submit']/preceding-sibling::button");
    private By deleteBtn = By.xpath("//div[@class='oxd-table-cell-actions']/child::button[1]");
    private By confirmDeleteBtn = By.xpath("//div[@class='orangehrm-modal-footer']/child::button[2]");





    public static int getUsersCount()
    {
        driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
        String countText = driver.findElement(usersCount).getText(); // Example: "(4) Records Found"

        // Step 1: Split by space → ["(4)", "Records", "Found"]
        String numberPart = countText.split(" ")[0]; // Extract "(4)"

        // Step 2: Remove parentheses → "4"
        numberPart = numberPart.replaceAll("[()]", "");

        // Step 3: Convert to integer
        return Integer.parseInt(numberPart);
    }
    public AddUserFormPage clickAddUser()
    {
        driver.findElement(addUserBtn).click();
        return new AddUserFormPage(driver);
    }

    public void searchForUsername(String username)
    {
        driver.findElement(usernameSearchField).sendKeys(username);
    }

    public void clickSearch()
    {
        driver.findElement(searchBtn).click();
    }

    public void scrollDown()
    {
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement myElement = driver.findElement(pageFooter);
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor)driver).executeScript(script,myElement);
    }

    public void deleteUser()
    {
        driver.findElement(deleteBtn).click();
    }

    public void confirmDeletion()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement yesBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDeleteBtn));

        yesBtn.click();
    }

    public void resetPage()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(resetBtn));
        element.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usersCount));

    }




}
