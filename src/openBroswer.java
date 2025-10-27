import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class openBroswer {
    WebDriver driver;
    @BeforeMethod
    public void open_browser(){
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }
    @Test(priority = 1)
    public void validLogin(){
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[class=\"radius\"][type=\"submit\"]")).click();
    }
    @Test(priority = 2)
    public void invalidLogin(){
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[class=\"radius\"][type=\"submit\"]")).click();
    }
    @Test(priority = 3)
    public void wrongCredentials()
    {
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys("gdijohsdfg");
        driver.findElement(By.id("password")).sendKeys("dfikohofdghsdf");
        driver.findElement(By.cssSelector("button[class=\"radius\"]")).click();
    }
    @AfterMethod
    public void quit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();

    }
}
