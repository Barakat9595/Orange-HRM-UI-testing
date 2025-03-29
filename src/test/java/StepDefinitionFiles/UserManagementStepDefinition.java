package StepDefinitionFiles;

import AddUserFormPage.AddUserFormPage;
import AdminTabPage.AdminTabPage;
import HomePage.HomePage;
import LoginPage.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class UserManagementStepDefinition {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    AdminTabPage adminTabPage;
    AddUserFormPage addUserFormPage;
    static int  defaultUsersCount;
    static int newUserCount;
    static int userCountAfterDeletion;
    SoftAssert softAssert;
    WebDriverWait wait;

    @Given("user navigates to Admin tab")
    public void user_navigates_to_admin_tab() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        softAssert = new SoftAssert();


        loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();


        homePage = new HomePage(driver);
        homePage.switchToAdminTab();
        adminTabPage = new AdminTabPage(driver); //intialize it just once
    }

    @When("user click add new")
    public void user_click_add_new() {
        defaultUsersCount = AdminTabPage.getUsersCount();
        adminTabPage.clickAddUser();
    }

    @When("enters user data and saves")
    public void enters_user_data_and_saves()  {
        addUserFormPage = new AddUserFormPage(driver);
        addUserFormPage.setUserName("new_user");

       addUserFormPage.setEmployeeName("Ma");
       addUserFormPage.chooseRole();
        addUserFormPage.chooseStatus(); // -> UPDATE LOCATORS OF DDLs INDEPENDENTLY
        addUserFormPage.setPassword("admin123");
        addUserFormPage.confirmPassword("admin123");
        addUserFormPage.submitUser();
    }

    @Then("the number of users should increase")
    public void the_number_of_users_should_increase() {


        newUserCount = AdminTabPage.getUsersCount();
        softAssert.assertEquals(newUserCount, defaultUsersCount + 1, "User count mismatch!");

    }

    @When("user clicks delete user")
    public void user_clicks_delete_user() {
       adminTabPage.searchForUsername("new_user");
       adminTabPage.clickSearch();
       adminTabPage.scrollDown();
       adminTabPage.deleteUser();
       adminTabPage.confirmDeletion();
       adminTabPage.resetPage();
    }
    @When("gets users count")
    public void gets_users_count()  {

        userCountAfterDeletion = AdminTabPage.getUsersCount();
        System.out.println("Default count: " + defaultUsersCount);
        System.out.println("New count: " + newUserCount);
        System.out.println("After deletion: " + userCountAfterDeletion);

    }
    @Then("the number of users should decrease by one")
    public void the_number_of_users_should_decrease_by_one() {

        softAssert.assertEquals(userCountAfterDeletion, newUserCount -1);
        softAssert.assertAll();

    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    }

