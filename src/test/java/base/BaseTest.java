package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserStackUtils;
import utils.DriverFactory;
import utils.TestConfig;
import utils.TestContext;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest
{
    protected static ThreadLocal<TestContext> testContext = new ThreadLocal<TestContext>();
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private BrowserStackUtils browserStackUtils = new BrowserStackUtils();

    @Parameters({"browser", "remote"})
    @BeforeMethod(alwaysRun = true)
    public void initialiseTest(@Optional("default") String browser, @Optional("false") String remoteExec, Method method)
    {
        TestConfig config = TestConfig.getInstance()
                                      .setRemoteExec(remoteExec)
                                      .setBrowser(browser)
                                      .setBuildName();

        testContext.set(new TestContext()
                .setTestConfig(config)
                .setTestMethodName(method.getName()));

        driver.set(DriverFactory.initialiseDriver(getContext()));

        getContext().setFileUploader(driver.get());
    }

    @AfterMethod
    public void teardown()
    {
        getDriver().quit();
    }

    @AfterMethod
    public void tagTestSessionOnBrowserStack(ITestResult result)
    {
        if (getContext().getConfig()
                        .getRemoteExec()
                        .equals("true") && result.isSuccess())
        {
            browserStackUtils.postPassedTestStatus(getContext(), result);
        } else if (getContext().getConfig()
                               .getRemoteExec()
                               .equals("true") && !result.isSuccess())
        {
            browserStackUtils.postFailedTestStatus(getContext(), result);
        }
    }

    @AfterMethod
    public void reportTestDurationAndFailureReason(ITestResult result)
    {
        if (result.isSuccess())
        {
            System.out.println(result.getName() + " passed in " + (result.getEndMillis() - result.getStartMillis()) + "ms");
        }
        else if (!result.isSuccess())
        {
            System.out.println(result.getName() + " failed due to error: " + result.getThrowable().getMessage());
        }
    }

    protected TestContext getContext()
    {
        return testContext.get();
    }

    protected WebDriver getDriver()
    {
        return driver.get();
    }
}