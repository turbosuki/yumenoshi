package base;

import org.testng.annotations.*;
import utils.TestConfig;
import utils.TestContext;

import java.net.MalformedURLException;

public class BaseTest
{
    protected TestContext context;

    @Parameters({"browser", "remote"})
    @BeforeClass()
    public void initialiseTest(@Optional("default") String browser, @Optional("false") String remoteExec)
            throws MalformedURLException
    {
        TestConfig config = new TestConfig().initialiseTestConfig();
        config.setRemoteExec(remoteExec);
        config.setBrowser(browser);
        context = new TestContext(config);
    }

    @AfterClass
    public void teardown()
    {
        if (context.getDriver() != null)
        {
            context.getDriver().quit();
        }
    }
}
