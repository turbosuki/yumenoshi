package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TestConfig
{
    private String implicitWait;
    private String baseUrl;
    private String seleniumHubUrl;
    private String browserStackUrl;
    private Config config;
    private String defaultLocalBrowser;
    private String remoteExec;
    private String browser;

    public TestConfig initialiseTestConfig()
    {
        config = ConfigFactory.load();
        setBaseUrl();
        setSeleniumHubUrl();
        setBrowserStackUrl();
        setImplicitWait();
        setDefaultLocalBrowser();
        return this;
    }

    private void setBaseUrl()
    {
        baseUrl = config.getString("baseUrl");
    }

    private void setSeleniumHubUrl()
    {
        if (config.hasPath("seleniumHubUrl"))
        {
            seleniumHubUrl = config.getString("seleniumHubUrl");
        }
    }

    private void setDefaultLocalBrowser()
    {
        if (config.hasPath("defaultLocalBrowser"))
        {
            defaultLocalBrowser = config.getString("defaultLocalBrowser");
        }
    }

    private void setBrowserStackUrl()
    {
        browserStackUrl = "https://" + config.getString("browserStackUsername") + ":" +
                config.getString("browserStackKey") + "@hub-cloud.browserstack.com/wd/hub";
    }

    private void setImplicitWait()
    {
        implicitWait = config.getString("implicitWait");
    }

    public void setRemoteExec(String remote)
    {
        remoteExec = remote;
    }

    public void setDefaultLocalBrowser(String browser)
    {
        defaultLocalBrowser = browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public String getSeleniumHubUrl()
    {
        return seleniumHubUrl;
    }

    public String getBrowserStackUrl()
    {
        return browserStackUrl;
    }

    public String getImplicitWait()
    {
        return implicitWait;
    }

    public String getDefaultLocalBrowser()
    {
        return defaultLocalBrowser;
    }

    public String getBrowser()
    {
        return browser;
    }

    public String getRemoteExec()
    {
        return remoteExec;
    }
}
