package utils.file_upload;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class AndroidFileUpload implements IFileUpload
{
    private AndroidDriver<WebElement> driver;
    private WebDriverWait wait;
    private By fileIcon = By.xpath("//*[@text='Files']");
    private By allowButton = MobileBy.xpath("//*[@class='android.widget.Button'][2]");

    public AndroidFileUpload(WebDriver driver)
    {
        this.driver = (AndroidDriver<WebElement>)driver;
    }

    @Override
    public void uploadFile(By locator, String filepath)
    {
        String fileName = getFileName(filepath);
        By fileThumbnail = By.xpath("//*[@text='" + fileName + "']");

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator))
                .click()
                .perform();

        try
        {
            driver.pushFile("/sdcard/download/" + fileName, new File(filepath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        driver.context("NATIVE_APP");
        driver.findElement(allowButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileIcon));
        driver.findElement(fileIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fileThumbnail));
        driver.findElement(fileThumbnail).click();
        driver.context("CHROMIUM");
    }

    private String getFileName(String filepath)
    {
        String[] parts = filepath.split("/");
        return parts[parts.length - 1];
    }
}
