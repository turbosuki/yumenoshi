package page_objects.examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestContext;

public class HomePage
{
    private WebDriver driver;
    private TestContext context;
    private By formAuthenticationLink = By.linkText("Form Authentication");

    public HomePage(TestContext context)
    {
        this.context = context;
        this.driver = context.getDriver();
    }

    public LoginPage clickFormAuthenticationLink()
    {
        driver.findElement(formAuthenticationLink).click();
        return new LoginPage(context);
    }

    public HomePage go()
    {
        driver.get(context.getConfig().getBaseUrl());
        return this;
    }
}