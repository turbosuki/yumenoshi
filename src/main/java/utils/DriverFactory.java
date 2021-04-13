package utils;

import enums.RemoteBrowsers;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory
{
    private static WebDriver driver;

    public static WebDriver createRemoteDriver(TestConfig config) throws MalformedURLException
    {
        DesiredCapabilities caps = RemoteBrowsers.valueOf(config.getBrowser().toUpperCase()).getDesiredCapabilities();

        switch (config.getBrowser())
        {
            case "android_chrome":
                driver = new AndroidDriver<WebElement>(new URL(config.getBrowserStackUrl()), caps);
                break;
            case "chrome":
            case "firefox":
                driver = new RemoteWebDriver(new URL(config.getBrowserStackUrl()), caps);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + config.getBrowser());
        }

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getImplicitWait()),
                TimeUnit.MILLISECONDS);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        return driver;
    }

    public static WebDriver createLocalDriver(TestConfig config)
    {
        String browser;

        if (config.getBrowser().equals("default"))
        {
            browser = config.getDefaultLocalBrowser();
        }
        else
        {
            browser = config.getBrowser();
        }

        switch (browser)
        {
            case "chrome":
                driver = setUpChrome();
                break;
            case "firefox":
                driver = setUpFirefox();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getImplicitWait()),
                TimeUnit.MILLISECONDS);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        return driver;
    }

    private static WebDriver setUpChrome()
    {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver setUpFirefox()
    {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
