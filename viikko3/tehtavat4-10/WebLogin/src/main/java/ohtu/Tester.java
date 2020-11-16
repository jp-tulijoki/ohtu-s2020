package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.Random;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        Random r = new Random();
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");

//        sleep(2);
//        
//        WebElement element = driver.findElement(By.linkText("login"));
//        System.out.println(driver.getPageSource());
//        element.click();
//
//        sleep(2);
//
//        
//        System.out.println(driver.getPageSource());
//        element = driver.findElement(By.name("username"));
//        element.sendKeys("pekka");
//        element = driver.findElement(By.name("password"));
////        element.sendKeys("akkep");
//        element.sendKeys("Wrong");
//        element = driver.findElement(By.name("login"));
//        
//        
//        sleep(2);
//        element.submit();
//        
//        System.out.println(driver.getPageSource());
//        sleep(3);
//        
//        driver.quit();
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        System.out.println(driver.getPageSource());
        element.click();

        sleep(2);

        
        System.out.println(driver.getPageSource());
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("signup"));
        
        
        sleep(2);
        element.submit();
        
        System.out.println(driver.getPageSource());
        sleep(3);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        System.out.println(driver.getPageSource());
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        System.out.println(driver.getPageSource());
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
