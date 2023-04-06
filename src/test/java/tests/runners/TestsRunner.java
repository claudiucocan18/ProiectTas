package tests.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import tests.webdrivers.WebDriverSelector;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {
                    "pretty",
                    "json:target/cucumber-report/cucumber.json"
            },
            features = "src/test/resources/features",
            glue = "tests.steps",
            stepNotifications = true
    )
    public class TestsRunner {
        public WebDriver driver = WebDriverSelector.getInstance();
    }

