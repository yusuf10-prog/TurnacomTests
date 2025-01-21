package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import java.util.List;

public class TurnaHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public TurnaHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // Multiple possible selectors for ad close buttons
    private final String[] adCloseSelectors = {
        "button.close-button",
        "div.modal-close",
        ".modal-dialog .close",
        ".modal .close-button",
        "#closeButton",
        "button[aria-label='Close']",
        ".popup-close",
        ".advertisement-close"
    };

    // Updated flight button locators
    private final String[] flightButtonSelectors = {
        "a[href*='ucak']",
        "a[href*='flight']",
        "//a[contains(@href, 'ucak')]",
        "//a[contains(@href, 'flight')]",
        "//a[contains(text(), 'Uçak')]",
        "//a[contains(text(), 'Flight')]"
    };

    @FindBy(css = "a[href*='otel']")
    private WebElement hotelButton;

    @FindBy(css = "a[href*='otobus']")
    private WebElement busButton;

    @FindBy(css = "a[href*='arac-kiralama']")
    private WebElement carRentalButton;

    @FindBy(css = "a[href*='feribot']")
    private WebElement ferryButton;

    public void closeAd() {
        try {
            System.out.println("Waiting for page to load completely...");
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
            
            System.out.println("Waiting 5 seconds for advertisements to appear...");
            Thread.sleep(5000);

            System.out.println("Attempting to close advertisement...");
            for (String selector : adCloseSelectors) {
                try {
                    List<WebElement> closeButtons = driver.findElements(By.cssSelector(selector));
                    for (WebElement button : closeButtons) {
                        if (button.isDisplayed()) {
                            try {
                                System.out.println("Found close button with selector: " + selector);
                                button.click();
                                System.out.println("Successfully closed advertisement");
                                Thread.sleep(2000);
                                return;
                            } catch (Exception e) {
                                System.out.println("Regular click failed, trying JavaScript click...");
                                js.executeScript("arguments[0].click();", button);
                                System.out.println("Successfully closed advertisement using JavaScript");
                                Thread.sleep(2000);
                                return;
                            }
                        }
                    }
                } catch (Exception ignored) {
                    // Continue to next selector if current one fails
                }
            }
        } catch (Exception e) {
            System.out.println("Could not find or close advertisement. Continuing with test...");
        }
    }

    public void clickFlightButton() {
        System.out.println("Attempting to click Flight button...");
        
        // Wait additional time after ad closure
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Try each flight button selector
        for (String selector : flightButtonSelectors) {
            try {
                WebElement flightButton;
                try {
                    // Try CSS selector first
                    flightButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
                } catch (Exception e) {
                    // If CSS fails, try XPath
                    flightButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                }

                if (flightButton.isDisplayed() && flightButton.isEnabled()) {
                    try {
                        System.out.println("Found flight button with selector: " + selector);
                        // Scroll into view
                        js.executeScript("arguments[0].scrollIntoView(true);", flightButton);
                        Thread.sleep(1000);
                        
                        // Try regular click
                        flightButton.click();
                        System.out.println("Successfully clicked Flight button");
                        Thread.sleep(2000);
                        return;
                    } catch (Exception e) {
                        // Try JavaScript click
                        System.out.println("Regular click failed, trying JavaScript click...");
                        js.executeScript("arguments[0].click();", flightButton);
                        System.out.println("Successfully clicked Flight button using JavaScript");
                        Thread.sleep(2000);
                        return;
                    }
                }
            } catch (Exception ignored) {
                System.out.println("Could not interact with selector: " + selector);
            }
        }
        System.out.println("Failed to click Flight button with all selectors");
    }

    public void clickHotelButton() {
        try {
            System.out.println("Clicking on Hotel button...");
            wait.until(ExpectedConditions.elementToBeClickable(hotelButton)).click();
            System.out.println("Successfully clicked Hotel button");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Regular click failed, trying JavaScript click for Hotel button...");
            js.executeScript("arguments[0].click();", hotelButton);
            System.out.println("Successfully clicked Hotel button using JavaScript");
        }
    }

    public void clickBusButton() {
        try {
            System.out.println("Clicking on Bus button...");
            wait.until(ExpectedConditions.elementToBeClickable(busButton)).click();
            System.out.println("Successfully clicked Bus button");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Regular click failed, trying JavaScript click for Bus button...");
            js.executeScript("arguments[0].click();", busButton);
            System.out.println("Successfully clicked Bus button using JavaScript");
        }
    }

    public void clickCarRentalButton() {
        try {
            System.out.println("Clicking on Car Rental button...");
            wait.until(ExpectedConditions.elementToBeClickable(carRentalButton)).click();
            System.out.println("Successfully clicked Car Rental button");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Regular click failed, trying JavaScript click for Car Rental button...");
            js.executeScript("arguments[0].click();", carRentalButton);
            System.out.println("Successfully clicked Car Rental button using JavaScript");
        }
    }

    public void clickFerryButton() {
        try {
            System.out.println("Clicking on Ferry button...");
            wait.until(ExpectedConditions.elementToBeClickable(ferryButton)).click();
            System.out.println("Successfully clicked Ferry button");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Regular click failed, trying JavaScript click for Ferry button...");
            js.executeScript("arguments[0].click();", ferryButton);
            System.out.println("Successfully clicked Ferry button using JavaScript");
        }
    }
}
