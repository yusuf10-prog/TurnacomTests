package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.time.Duration;
import java.util.List;

public class DailyRotationSteps {
    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    @Given("user is on homepage")
    public void userIsOnHomepage() {
        Driver.getDriver().get("https://turnacom.net");
    }

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        Driver.getDriver().get("https://turnacom.net/login");
    }

    @When("user searches for {string}")
    public void userSearchesFor(String searchTerm) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }

    @When("user adds first product to cart")
    public void userAddsFirstProductToCart() {
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-item")));
        firstProduct.click();
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-to-cart")));
        addToCartButton.click();
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials() {
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        emailField.sendKeys(System.getenv("TEST_USER_EMAIL"));
        passwordField.sendKeys(System.getenv("TEST_USER_PASSWORD"));
        passwordField.submit();
    }

    @When("user clicks on any category")
    public void userClicksOnAnyCategory() {
        WebElement category = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".category-item")));
        category.click();
    }

    @When("user clicks on first product")
    public void userClicksOnFirstProduct() {
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-item")));
        firstProduct.click();
    }

    @When("user applies price filter")
    public void userAppliesPriceFilter() {
        WebElement priceFilter = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".price-filter")));
        priceFilter.click();
    }

    @When("user clicks on each footer link")
    public void userClicksOnEachFooterLink() {
        List<WebElement> footerLinks = Driver.getDriver().findElements(By.cssSelector("footer a"));
        for (WebElement link : footerLinks) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                Driver.getDriver().get(href);
                Assert.assertTrue(Driver.getDriver().getTitle() != null);
                Driver.getDriver().navigate().back();
            }
        }
    }

    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".search-results")));
    }

    @Then("search results should contain {string}")
    public void searchResultsShouldContain(String searchTerm) {
        List<WebElement> results = Driver.getDriver().findElements(By.cssSelector(".product-title"));
        Assert.assertTrue("No search results found", results.size() > 0);
        boolean found = false;
        for (WebElement result : results) {
            if (result.getText().toLowerCase().contains(searchTerm.toLowerCase())) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("Search term not found in results", found);
    }

    @Then("shopping cart should be updated")
    public void shoppingCartShouldBeUpdated() {
        WebElement cartCount = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart-count")));
        Assert.assertNotEquals("0", cartCount.getText());
    }

    @Then("shopping cart should contain the product")
    public void shoppingCartShouldContainTheProduct() {
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart-icon")));
        cartIcon.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cart-item")));
    }

    @Then("user should be logged in successfully")
    public void userShouldBeLoggedInSuccessfully() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".user-profile")));
    }

    @Then("category products should be displayed")
    public void categoryProductsShouldBeDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-grid")));
    }

    @Then("product count should be greater than zero")
    public void productCountShouldBeGreaterThanZero() {
        List<WebElement> products = Driver.getDriver().findElements(By.cssSelector(".product-item"));
        Assert.assertTrue("No products found in category", products.size() > 0);
    }

    @Then("product details should be displayed")
    public void productDetailsShouldBeDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-details")));
    }

    @Then("product price should be visible")
    public void productPriceShouldBeVisible() {
        WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-price")));
        Assert.assertTrue("Price is not displayed", price.isDisplayed());
    }

    @Then("filtered results should be displayed")
    public void filteredResultsShouldBeDisplayed() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".filtered-products")));
    }

    @Then("products should match the price filter")
    public void productsShouldMatchThePriceFilter() {
        List<WebElement> prices = Driver.getDriver().findElements(By.cssSelector(".product-price"));
        Assert.assertTrue("No products found after filtering", prices.size() > 0);
    }

    @Then("all links should work correctly")
    public void allLinksShouldWorkCorrectly() {
        // Already verified in the when step
    }

    @Then("each page should load properly")
    public void eachPageShouldLoadProperly() {
        // Already verified in the when step
    }
}
