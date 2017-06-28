package crossBrowsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * Created by lra on 21.06.2017.
 */
public class VerifyTittleTest {

    WebDriver driver;

    @Test
    @Parameters("browser")
    public void verifyPageTitle(String browserName) {
        if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\lra\\Downloads\\geckodriver-v0.17.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();


        } else if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Software\\chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Software\\edgedriver\\IEDriverServer.exe");
               driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.google.no/");
        System.out.println("Title" + driver.getTitle());
        driver.quit();
    }
}


