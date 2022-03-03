package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends DriverManager {

    @BeforeMethod
    public void appSetup()  {
        initializeDriver();
    }

    @AfterMethod
    public void tearDown() {
        androidDriver.closeApp();
        androidDriver.quit();
    }

}
