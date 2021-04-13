package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumHelper
{
    private TestContext context;
    private WebDriver driver;
    private final int WAIT_TIMEOUT = 5;

    public SeleniumHelper(TestContext context)
    {
        this.context = context;
        this.driver = context.getDriver();
    }

    public void waitForElementToBeInvisible(By locator)
    {
        context.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        context.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void waitForElementToBePresent(By locator)
    {
        new WebDriverWait(driver, WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementPresent(By locator)
    {
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

        boolean isPresent = !driver.findElements(locator).isEmpty();

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(context.getConfig().getImplicitWait()),
                TimeUnit.MILLISECONDS);

        return isPresent;
    }
}
