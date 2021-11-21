package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackSessionDetails
{
    public static class BrowserStackSessionDetailsBuilder
    {
        private String sessionId;
        private String testCaseName;
        private String buildName;

        public static BrowserStackSessionDetailsBuilder sessionDetailsBuilder()
        {
            return new BrowserStackSessionDetailsBuilder();
        }

        public BrowserStackSessionDetailsBuilder withSessionId(WebDriver driver)
        {
            this.sessionId = ((RemoteWebDriver)driver).getSessionId().toString();
            return this;
        }

        public BrowserStackSessionDetailsBuilder withTestCaseName(String testCaseName)
        {
            this.testCaseName = testCaseName;
            return this;
        }

        public BrowserStackSessionDetailsBuilder withBuildName(String buildName)
        {
            this.buildName = buildName;
            return this;
        }

        public BrowserStackSessionDetails build()
        {
            BrowserStackSessionDetails browserStackSessionDetails = new BrowserStackSessionDetails();
            browserStackSessionDetails.sessionId = this.sessionId;
            browserStackSessionDetails.testCaseName = this.testCaseName;
            browserStackSessionDetails.buildName = this.buildName;
            return browserStackSessionDetails;
        }
    }

    private String sessionId;
    private String testCaseName;
    private String buildName;

    public String getSessionId()
    {
        return sessionId;
    }

    public String getTestCaseName()
    {
        return testCaseName;
    }

    public String getBuildName()
    {
        return buildName;
    }
}