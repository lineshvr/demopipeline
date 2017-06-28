/**
 * Created by lra on 15.05.2017.
 */


import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class ScreenCapture {

    public static WebDriver driver;

    public static WebDriver getWebDriver() {
        if (driver == null) {
            driver = startRemoteWebDriver();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }
        return driver;
    }


    public static WebDriver startRemoteWebDriver() {
        DesiredCapabilities capabilitiy = DesiredCapabilities.firefox();
        capabilitiy.setBrowserName("firefox");
        capabilitiy.setCapability("acceptSslCerts", true);
        capabilitiy.setPlatform(Platform.LINUX);
        try {
            return new RemoteWebDriver(new URL("http://selenium_host:4444/wd/hub"), capabilitiy);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stopWebDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }


    public static void main(String[] args) {

        getWebDriver().navigate().to("https://eid-systest-web01.dmz.local/blockpincode/");

        try {
            Robot robot = new Robot();
            String format = "jpg";
            String fileName = "PartialScreenshot." + format;

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle captureRect = new Rectangle(770, 460, 250, 48);
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
            File imageFile = new File(fileName);
            Tesseract instance = new Tesseract(); //

            try {

                String result = instance.doOCR(imageFile);
                System.out.println(result);
                getWebDriver().findElement(By.id("number")).sendKeys("23423423423422");
      //          getWebDriver().findElement(By.id("captcha")).sendKeys(result);
               // getWebDriver().findElement(By.className("submit")).click();
                stopWebDriver();
            } catch (TesseractException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("A partial screenshot saved!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }


    }
}