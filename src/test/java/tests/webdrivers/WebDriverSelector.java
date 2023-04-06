package tests.webdrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSelector {

    private static WebDriver instance=null;

    public static WebDriver getInstance() {
        String browserType = System.getProperty("browser", "chrome");
        return switch (browserType) {
            case "chrome" -> getChromeDriver();
            default -> throw new RuntimeException("BrowserType is not supported" + browserType);
        };
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Claudiu/Desktop/chromedriver_win32/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        if (instance == null || ((ChromeDriver) instance).getSessionId() == null) {
            instance = new ChromeDriver(chromeOptions);
        }
        return instance;
    }

}
