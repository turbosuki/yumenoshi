package example_test;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_objects.examples.HomePage;
import page_objects.examples.SecureAreaPage;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest
{
    private HomePage homePage;
    private SecureAreaPage secureAreaPage;

    @BeforeClass
    public void pageSetup()
    {
        homePage = new HomePage(context);
        secureAreaPage = new SecureAreaPage(context);
    }

    @Test
    public void testSuccessfulLogin()
    {
        homePage.go()
                .clickFormAuthenticationLink()
                .setUsername("tomsmith")
                .setPassword("SuperSecretPassword!")
                .clickLoginButton();

        assertTrue(secureAreaPage.getAlertText().contains("You logged into a secure area!"),
                "Alert text was: " + secureAreaPage.getAlertText());
    }
}