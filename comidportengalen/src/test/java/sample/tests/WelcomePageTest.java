package sample.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import sample.components.GalenTestBase;

import java.io.IOException;


public class WelcomePageTest extends GalenTestBase {

 /*   @Test(dataProvider = "devices")
    public void welcomePage_shouldLookGood_onDevice(TestDevice device) throws IOException {
    //    load("/");
  //      checkLayout("/specs/welcomePage.spec", device.getTags());
    }
*/
    @Test(dataProvider = "devices")
    public void loginPage_shouldLookGood_onDevice(TestDevice device) throws IOException, InterruptedException {
        System.out.println("devices"+device.getTags());

        load("/");
       getDriver().findElement(By.name("logg_inn")).click();
        getDriver().findElement(By.id("MinIDChain")).click();



        Thread.sleep(100);
        checkLayout("/specs/idportenPage.gspec", device.getTags());
    }

}
