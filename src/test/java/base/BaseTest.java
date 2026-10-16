package base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;

public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = BrowserFactory.create();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
