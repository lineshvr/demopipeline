package seleniumgrid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * Created by lra on 21.06.2017.
 */
public class Gridest {

    WebDriver driver;
    public String URL, Node;
    @Test
    @Parameters("browser")
    public void verifyPageTitle(String browserName) throws MalformedURLException {
        String URL = "https://www.google.no/";
        if (browserName.equalsIgnoreCase("firefox")) {
         /*   System.setProperty("webdriver.gecko.driver", "C:\\Users\\lra\\Downloads\\geckodriver-v0.17.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();*/

            System.out.println(" Executing on FireFox");
            String Node = "http://10.243.201.35:5566/wd/hub";
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");
            cap.setPlatform(Platform.WIN10);
             driver = new RemoteWebDriver(new URL(Node), cap);
            // Puts an Implicit wait, Will wait for 10 seconds before throwing
            // exception
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Launch website
            driver.navigate().to(URL);
         //   driver.manage().window().maximize();

        } else if (browserName.equalsIgnoreCase("chrome")) {
             System.out.println(" Executing on CHROME");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            String Node = "http://10.243.201.35:5567/wd/hub";
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        } else if (browserName.equalsIgnoreCase("safari")) {
            System.out.println(" Executing on SAFARI");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("safari");
            String Node = "http://10.243.201.35:5568/wd/hub";
            cap.setPlatform(Platform.WINDOWS);
            cap.setVersion("5.1.4");
            driver = new RemoteWebDriver(new URL(Node), cap);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }
        System.out.println("Title" + driver.getTitle());
        driver.quit();
    }
}


