import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class openbroswer {
    WebDriver driver;
    @BeforeMethod
    public void open_browser(){
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }
    @Test(priority = 1)
    public void validLogin(){
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[class=\"radius\"][type=\"submit\"]")).click();
        //get current url
        String Currenturl= driver.getCurrentUrl();
        Assert.assertEquals(Currenturl,"https://the-internet.herokuapp.com/secure");
        //see text
        boolean actualMsg=driver.findElement(By.cssSelector("div[id=\"flash\"]")).getText().contains("You logged into a secure area");
        Assert.assertTrue(actualMsg);
        // check color
        String actualColor=driver.findElement(By.cssSelector("div[class=\"flash success\"]")).getCssValue("background-color");
        Assert.assertEquals(actualColor,"rgba(93, 164, 35, 1)");
        // Log out Button is Displayed?
        boolean logout_button=driver.findElement(By.cssSelector("a[class=\"button secondary radius\"]")).isDisplayed();
        Assert.assertTrue(logout_button);

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
