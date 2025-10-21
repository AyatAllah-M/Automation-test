package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.time.Duration;

public class Task1 {
    public static void main(String[] args) {

        //Open browser
        WebDriver driver = new ChromeDriver();

        //Navigate to page
        driver.navigate().to("https://www.saucedemo.com/v1/index.html");

        //login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //assert login
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");
        Assert.assertTrue(driver.findElement(By.className("product_label")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.className("product_label")).getText(), "Products");

        //add to cart
        driver.findElement(By.xpath("//button[@class=\"btn_primary btn_inventory\"][1]")).click();

        //click cart icon
        driver.findElement(By.xpath("//a[@href=\"./cart.html\"]")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //assert cart
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/cart.html");
        Assert.assertTrue(driver.findElement(By.className("subheader")).isDisplayed());

        //close browser
        driver.quit();
    }

}
