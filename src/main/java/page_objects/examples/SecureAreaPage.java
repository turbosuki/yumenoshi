package page_objects.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestContext;

public class SecureAreaPage
{
    private TestContext context;
    private WebDriver driver;
    private By statusAlert = By.id("flash");

    public SecureAreaPage(TestContext context)
    {
        this.context = context;
        this.driver = context.getDriver();
    }

    public String getAlertText()
    {
        return driver.findElement(statusAlert).getText();
    }
}