package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Improved {

    WebDriver driver;

    @BeforeMethod
    public void preconditions(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @Test
    public void verifyProductIsAddedToCart(){

        //Navigate to page
        driver.get("https://www.saucedemo.com/v1/index.html");

        //login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Add to cart
        driver.findElement(By.xpath("//button[@class=\"btn_primary btn_inventory\"][1]")).click();

        //Click cart icon to go to cart page
        driver.findElement(By.xpath("//a[@href=\"./cart.html\"]")).click();

        //Assert cart page
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/cart.html");
        Assert.assertTrue(driver.findElement(By.className("subheader")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.className("subheader")).getText(), "Your Cart");
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(), "Sauce Labs Backpack");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


