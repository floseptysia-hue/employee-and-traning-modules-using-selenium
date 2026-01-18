package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.Env;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Env.get("BASE_URL") + "/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(Env.get("USER_EMAIL"), Env.get("USER_PASSWORD"));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
