package enums;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum RemoteBrowsers
{
    EDGE(getEdgeCaps()),
    FIREFOX(getFirefoxCaps()),
    CHROME(getChromeCaps()),
    SAFARI(getSafariCaps()),
    IPHONE_SAFARI(getIphoneSafariCaps()),
    ANDROID_CHROME(getAndroidChromeCaps());

    private DesiredCapabilities caps;

    RemoteBrowsers(DesiredCapabilities caps)
    {
        this.caps = caps;
    }

    public DesiredCapabilities getDesiredCapabilities()
    {
        return this.caps;
    }

    private static DesiredCapabilities getEdgeCaps()
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "8.1");
        caps.setCapability("browser", "Edge");
        caps.setCapability("browser_version", "latest-beta");
        caps.setCapability("browserstack.selenium_version", "3.5.2");
        caps.setCapability("browserstack.console", "errors");
        caps.setCapability("name", "Edge");

        return caps;
    }

    private static DesiredCapabilities getChromeCaps()
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "latest-beta");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.14.0");
        caps.setCapability("browserstack.console", "errors");
        caps.setCapability("name", "Chrome");

        return caps;
    }

    private static DesiredCapabilities getFirefoxCaps()
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "latest-beta");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.5.2");
        caps.setCapability("browserstack.console", "errors");
        caps.setCapability("name", "Firefox");

        return caps;
    }

    private static DesiredCapabilities getSafariCaps()
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "Catalina");
        caps.setCapability("browser", "Safari");
        caps.setCapability("browser_version", "13.0");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.14.0");
        caps.setCapability("browserstack.console", "errors");

        return caps;
    }

    private static DesiredCapabilities getAndroidChromeCaps()
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "9.0");
        caps.setCapability("device", "Samsung Galaxy Note 10");
        caps.setCapability("real_mobile", "true");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.console", "errors");
        caps.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        return caps;
    }

    private static DesiredCapabilities getIphoneSafariCaps()
    {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os_version", "14");
        caps.setCapability("device", "iPhone XS");
        caps.setCapability("real_mobile", "true");
        caps.setCapability("browserstack.console", "verbose");
        caps.setCapability("browserstack.console", "errors");
        caps.setCapability("browserstack.local", "false");

        return caps;
    }
}
