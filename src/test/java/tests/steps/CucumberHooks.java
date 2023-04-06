package tests.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import tests.runners.TestsRunner;
import tests.webdrivers.WebDriverSelector;

public class CucumberHooks extends TestsRunner {
    @Before
    public void setupBeforeScenario() {
        System.out.println("Perform before scenario cucumber hook");
        System.out.println("Driver instance " +  driver);
    }

    @AfterAll
    public static void finishAll() {
        System.out.println("Perform finish after all cucumber hook");
        WebDriverSelector.getInstance().quit();
    }
}
