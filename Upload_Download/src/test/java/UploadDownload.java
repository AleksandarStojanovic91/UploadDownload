import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UploadDownload {
    WebDriver driver;
    String URLupload = "http://demo.guru99.com/test/upload/";
    String URLdownload = "http://demo.guru99.com/test/yahoo.html";
    String wgetPath = "C:\\Users\\a.stojanovic\\Desktop\\wget.exe";//ovo promeniti to je lokacija wget.exe
    String downloadPath = "E:\\Test"; //ovo promeniti to je download lokacija
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void Upload(){
        driver.get(URLupload);
        driver.findElement(By.cssSelector("#uploadfile_0")).sendKeys("C:\\Users\\a.stojanovic\\Desktop\\Test.txt");
        driver.findElement(By.cssSelector("#terms")).click();
        driver.findElement(By.cssSelector(".btn.buttoncolor.has-spinner")).click();
    }
    @Test
    public void Download(){
        driver.get(URLdownload);
        WebElement downloadButton = driver.findElement(By.cssSelector("#messenger-download"));
        String source = downloadButton.getAttribute("href");
        String wgetCommand = "cmd /c "+wgetPath+" -P "+downloadPath+" --no-check-certificate "+source;

        try {
            Process exec = Runtime.getRuntime().exec(wgetCommand);
            int exitVal = exec.waitFor();
            System.out.println("Exit value: " + exitVal);
        } catch (InterruptedException | IOException ex) {
            System.out.println(ex.toString());
        }
        driver.close();
        }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}