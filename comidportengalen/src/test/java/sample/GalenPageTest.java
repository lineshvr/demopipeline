package sample;
import com.galenframework.testng.GalenTestNgTestBase;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

import static java.util.Arrays.asList;
import com.galenframework.testng.GalenTestNgTestBase;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by lra on 11.05.2017.
 */


    public abstract class GalenPageTest extends GalenTestNgTestBase {

        private static final String ENV_URL = "http://testapp.galenframework.com";

        @Override
        public WebDriver createDriver(Object[] args) {
            System.setProperty("webdriver.gecko.driver", "C:\\Software\\geckodriver-v0.16.1-win64\\geckodriver.exe");
            WebDriver driver = new FirefoxDriver();
            DesiredCapabilities capabilitiy = DesiredCapabilities.firefox();
            capabilitiy.setBrowserName("firefox");
            capabilitiy.setCapability("acceptSslCerts", true);
            capabilitiy.setPlatform(Platform.WINDOWS);
            if (args.length > 0) {
                if (args[0] != null && args[0] instanceof TestDevice) {
                    TestDevice device = (TestDevice)args[0];
                    if (device.getScreenSize() != null) {
                        driver.manage().window().setSize(device.getScreenSize());
                    }
                }
            }
            return driver;
        }

        public void load(String uri) {
            getDriver().get(ENV_URL + uri);
        }

        @DataProvider(name = "devices")
        public Object [][] devices () {
            return new Object[][] {
                    {new TestDevice("mobile", new Dimension(450, 800), asList("mobile"))},
                    {new TestDevice("tablet", new Dimension(750, 800), asList("tablet"))},
                    {new TestDevice("desktop", new Dimension(1024, 800), asList("desktop"))}
            };
        }

        public static class TestDevice {
            private final String name;
            private final Dimension screenSize;
            private final List<String> tags;

            public TestDevice(String name, Dimension screenSize, List<String> tags) {
                this.name = name;
                this.screenSize = screenSize;
                this.tags = tags;
            }

            public String getName() {
                return name;
            }

            public Dimension getScreenSize() {
                return screenSize;
            }

            public List<String> getTags() {
                return tags;
            }

            @Override
            public String toString() {
                return String.format("%s %dx%d", name, screenSize.width, screenSize.height);
            }
        }
    }
