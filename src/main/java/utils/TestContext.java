package utils;

import enums.RemoteBrowsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.file_upload.FileUpload;
import utils.file_upload.IFileUpload;

import java.net.MalformedURLException;

public class TestContext
{
    private WebDriver driver;
    private TestConfig config;
    private IFileUpload fileUploader;

    public TestContext(TestConfig config) throws MalformedURLException
    {
        this.config = config;

        if (config.getRemoteExec().equals("true"))
        {
            DesiredCapabilities caps = RemoteBrowsers.valueOf(config.getBrowser().toUpperCase()).getDesiredCapabilities();
            driver = DriverFactory.createRemoteDriver(config);
        }
        else
        {
            driver = DriverFactory.createLocalDriver(config);
        }

        fileUploader = FileUpload.New(driver, config);
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public TestConfig getConfig()
    {
        return config;
    }

    public IFileUpload getFileUploader()
    {
        return fileUploader;
    }
}