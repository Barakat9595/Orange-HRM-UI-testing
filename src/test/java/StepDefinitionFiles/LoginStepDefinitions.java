package StepDefinitionFiles;


import HomePage.HomePage;
import LoginPage.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginStepDefinitions {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @Given("user navigates to website login page")
    public void user_navigates_to_website_login_page() {
        // Initialize WebDriver and navigate to login page
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();


        loginPage = new LoginPage(driver);
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        // Enter username and password
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
    }

    @When("click login")
    public void click_login() {
        // Click login button
        loginPage.clickLogin();
        homePage = new HomePage(driver);
    }

    @Then("user should be navigated to website home page")
    public void user_should_be_navigated_to_website_home_page()
    {
        SoftAssert softAssert = new SoftAssert();
        String homePageHeader = homePage.getPageTitle();
        softAssert.assertEquals(homePageHeader,"Dashboard","mismatch");
        String pageURL = homePage.getPageUrl();
        softAssert.assertTrue(pageURL.contains("dashboard"),"URL does not match");
        softAssert.assertAll();
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}