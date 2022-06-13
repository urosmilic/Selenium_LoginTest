package Base;

import Pages.LoginPage;
import Pages.SecurePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class Base {
    public WebDriver driver;
    public WebDriverWait wdwait;    //na kraju ga nisam koristio jer sve radi fluentno i nema potrebe za cekanjem
    public LoginPage loginPage;
    public SecurePage securePage;
    public ExcelReader excelReader;

    @BeforeClass
    public void setUp () throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginPage = new LoginPage(driver);
        securePage = new SecurePage(driver);
        excelReader = new ExcelReader("C:\\Users\\USER\\Desktop\\SeleniumTestData.xlsx");

    }

    @AfterClass
    public void tearDown () {
        driver.close();
        driver.quit();
    }

    //nije bilo potrebe da ovde (uopste) kreiram metode za scroll i visibilityWait jer sve, jer je stranica tako formatirana


}
