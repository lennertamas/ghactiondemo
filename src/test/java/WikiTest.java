import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

public class WikiTest {

    WebDriver webdriver;

    @BeforeEach
    public void Init()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        webdriver = new ChromeDriver(options);

        webdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webdriver.manage().window().maximize();

    }

    @Test
    @DisplayName("Test for clicking English button on menu page")
    public void TestEnglishButton()
    {
        webdriver.get("https://www.wikipedia.org/");
        WebElement englishButton = webdriver.findElement(By.id("js-link-box-en"));
        englishButton.click();
        String expectedTitle = "Welcome to Wikipedia,";
        WebElement titleDiv = webdriver.findElement(By.xpath("/html/body/div[3]/div[3]/div[5]/div[1]/div[1]/div/div[1]"));
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot)webdriver).getScreenshotAs(OutputType.BYTES)));
        Assertions.assertEquals(expectedTitle, titleDiv.getText());
    }

    @Test
    public void TestFalse()
    {
        Assertions.assertTrue(false);
    }

    @AfterEach
    public void Close()
    {
        webdriver.quit();
    }

}
