package tests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import tests.runners.TestsRunner;

import java.time.Duration;
import java.util.List;

public class Steps extends TestsRunner {

    @FindBy(id = "inventory_container")
    private WebElement products;

    @FindBy(className="inventory_item_name")
    private List<WebElement> productList;

    @Given("Login page is opened")
    public void loginPageIsOpened() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("Username and password {string} and {string} are entered")
    public void usernameAndPasswordAndAreEntered(String username, String password) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("[data-test=username]")))).sendKeys(username);
        WebElement passwordElement = driver.findElement(By.cssSelector("[data-test=password]"));
        passwordElement.sendKeys(password);

    }

    @And("Login is attempted")
    public void loginIsAttempted() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("[data-test=login-button]")))).click();

    }

    @Then("Catalog is displayed")
    public void catalogIsDisplayed() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        WebElement menuButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bm-burger-button")));
        Assert.assertTrue(menuButton.isDisplayed());
    }

    @When("User adds to cart product {string}")
    public void userAddsToCartProduct(String productName) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn_primary.btn_inventory"))).click();
    }

    @And("User goes to shopping cart")
    public void userGoesToShoppingCart() {

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        driver.findElement(By.className("shopping_cart_badge")).click();


    }

    @Then("Product {string} is added to cart check")
    public void productIsAddedToCartCheck(String productName) {
        //click cart button

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        boolean isProductAddedToCart = false;
        for (WebElement cartItem : cartItems) {
            WebElement itemName = cartItem.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals(productName)) {
                isProductAddedToCart = true;
                break;
            }
        }
        Assert.assertTrue(isProductAddedToCart);

    }


    @When("User clicks checkout button")
    public void userChecksOut() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#checkout"))).click();
    }

    @Then("Checkout Info page is displayed")
    public void checkoutInfoPageIsDisplayed() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        WebElement checkoutInfoPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_info_container")));
        Assert.assertTrue(checkoutInfoPage.isDisplayed());
    }

    @When("Enter checkout info {string} {string} {string}")
    public void entersCheckoutInfo(String firstName, String lastName, String postalCode) {

        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement postalCodeInput = driver.findElement(By.id("postal-code"));

        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeInput.sendKeys(postalCode);
    }

    @And("Click checkout button")
    public void clickCheckoutButton() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test = continue]"))).click();
    }

    @Then("Checkout Overview is displayed")
    public void checkoutOverviewIsDisplayed() {

        WebElement checkoutSummaryContainer = driver.findElement(By.id("checkout_summary_container"));

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);

        WebElement element = wait.until(ExpectedConditions.visibilityOf(checkoutSummaryContainer));
        Assert.assertTrue("Checkout overview is not displayed", element.isDisplayed());
    }


    @When("Click finish on Checkout Overview page")
    public void clickFinishOnCheckoutOverviewPage() {

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
    }

    @Then("Checkout Complete page is displayed")
    public void checkoutCompletePageIsDisplayed() {
        WebElement checkoutCompleteContainer = driver.findElement(By.id("checkout_complete_container"));

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);

        WebElement element = wait.until(ExpectedConditions.visibilityOf(checkoutCompleteContainer));
        Assert.assertTrue("Checkout complete page is not displayed", element.isDisplayed());
    }

    @And("{string} is displayed")
    public void messageIsDisplayed(String arg0) {
        WebElement checkoutThankYouMessage = driver.findElement(By.cssSelector(".complete-header"));

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);

        WebElement element = wait.until(ExpectedConditions.visibilityOf(checkoutThankYouMessage));
        Assert.assertTrue("Checkout message is not displayed", element.isDisplayed());

    }


    @When("Go back to Products page")
    public void goBackToProductsPage() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products"))).click();
    }


    @When("Click remove button")
    public void clickRemoveButton() {

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-backpack"))).click();


    }

    @Then("Product is removed check")
    public void productIsRemovedCheck() {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        boolean isProductNotInCart = true;
        for (WebElement cartItem : cartItems) {
            WebElement itemName = cartItem.findElement(By.className("inventory_item_name"));
            if (itemName.getText().equals("Sauce Labs Backpack")) {
                isProductNotInCart = false;
                break;
            }
        }
        Assert.assertTrue(isProductNotInCart);
    }
}

