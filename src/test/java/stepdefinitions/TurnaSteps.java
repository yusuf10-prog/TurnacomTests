package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.TurnaHomePage;

@Epic("Turna.com Navigation Tests")
@Feature("Website Navigation")
public class TurnaSteps {
    private WebDriver driver;
    private TurnaHomePage turnaHomePage;

    @Before
    public void setup() {
        System.out.println("\n====== Starting Test ======");
        System.out.println("Setting up Firefox driver...");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        turnaHomePage = new TurnaHomePage(driver);
        System.out.println("Browser setup completed");
    }

    @Given("I am on the Turna.com homepage")
    @Step("Navigating to Turna.com homepage")
    @Description("Open the Turna.com website in Firefox browser")
    public void i_am_on_the_turna_homepage() {
        System.out.println("\nStep 1: Navigating to Turna.com homepage");
        driver.get("https://www.turna.com");
    }

    @When("I close the advertisement")
    @Step("Closing advertisement popup")
    @Description("Locate and close any advertisement popup that appears")
    public void i_close_the_advertisement() {
        System.out.println("\nStep 2: Attempting to close advertisement");
        turnaHomePage.closeAd();
    }

    @Then("I click on the Flight button")
    @Step("Clicking Flight section")
    @Description("Navigate to the Flight booking section")
    public void i_click_on_the_flight_button() {
        System.out.println("\nStep 3: Navigating to Flight section");
        turnaHomePage.clickFlightButton();
    }

    @Then("I click on the Hotel button")
    @Step("Clicking Hotel section")
    @Description("Navigate to the Hotel booking section")
    public void i_click_on_the_hotel_button() {
        System.out.println("\nStep 4: Navigating to Hotel section");
        turnaHomePage.clickHotelButton();
    }

    @Then("I click on the Bus button")
    @Step("Clicking Bus section")
    @Description("Navigate to the Bus booking section")
    public void i_click_on_the_bus_button() {
        System.out.println("\nStep 5: Navigating to Bus section");
        turnaHomePage.clickBusButton();
    }

    @Then("I click on the Car Rental button")
    @Step("Clicking Car Rental section")
    @Description("Navigate to the Car Rental section")
    public void i_click_on_the_car_rental_button() {
        System.out.println("\nStep 6: Navigating to Car Rental section");
        turnaHomePage.clickCarRentalButton();
    }

    @Then("I click on the Ferry button")
    @Step("Clicking Ferry section")
    @Description("Navigate to the Ferry booking section")
    public void i_click_on_the_ferry_button() {
        System.out.println("\nStep 7: Navigating to Ferry section");
        turnaHomePage.clickFerryButton();
    }

    @After
    public void tearDown() {
        System.out.println("\nTest completed, closing browser");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("====== Test Finished ======\n");
    }
}
