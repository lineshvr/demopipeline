package stepDefs;

/**
 * Created by lra on 01.06.2017.
 */

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.remote.RemoteWebDriver;
        import org.testng.annotations.AfterTest;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.Parameters;
        import org.testng.annotations.Test;

        import java.io.File;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.concurrent.TimeUnit;

public class TestNGClass {
    public WebDriver driver;
    public String URL, Node;
    protected ThreadLocal<RemoteWebDriver> threadDriver = null;

    @Parameters("browser")
    @BeforeTest
    public void launchapp(String browser) throws MalformedURLException {
        String URL = "https://eid-inttest-web01.dmz.local/autotest-sptest1/";


        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Software\\geckodriver-v0.16.1-win64\\geckodriver.exe");
            System.out.println(" Executing on FireFox");
            String Node = "http://192.168.70.1:5566/wd/hub";
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");

            driver = new RemoteWebDriver(new URL(Node), cap);
            // Puts an Implicit wait, Will wait for 10 seconds before throwing exception
            driver = new FirefoxDriver();
            //      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {
            File file = new File("C:\\Software\\chromedriver_win32\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

            System.out.println(" Executing on CHROME");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            String Node = "http://192.168.70.1:5557/wd/hub";
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
            driver.manage().window().maximize();
        } else {
            //throw new IllegalArgumentException("The Browser Type is Undefined");
        }
    }

    @Test
    public void calculatepercent() {
        driver.findElement(By.name("logg_inn")).click();
        driver.findElement(By.id("MinIDChain")).click();
        driver.quit();
    }

    @AfterTest
    public void closeBrowser() {
        // driver.quit();
    }
}


<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Suite" parallel="tests">

    <test name="FirefoxTest">
        <parameter name="browser" value="firefox" />
        <classes>
            <class name="stepDefs.TestNGClass" />
        </classes>
    </test>

    <test name="ChromeTest">
        <parameter name="browser" value="chrome" />
        <classes>
            <class name="stepDefs.TestNGClass" />
        </classes>
    </test>


</suite>