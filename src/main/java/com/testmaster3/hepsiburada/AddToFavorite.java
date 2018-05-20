package com.testmaster3.hepsiburada;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddToFavorite {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public AddToFavorite(WebDriver driver){
        this.webDriver = driver;
        this.webDriverWait = new WebDriverWait(driver, 30, 150);
    }

    public void addFavorite(String email, String password, String searchKey){
        webDriver.get("https://www.hepsiburada.com/");
        login(email,password);
        productSearch(searchKey); // urun arama ve favorilere ekleme
        webDriver.findElement(By.id("ctl00_ContentPlaceHolder1_rptShoppingList_ctl01_chkSelect")).click();
        List<WebElement> deleteButton = webDriver.findElements(By.cssSelector(".link-button-trans.sprite"));
        deleteButton.get(0).click();
        webDriver.switchTo().alert().accept(); //silme onayi





    }

    public  void productSearch(String searchKey){
        WebElement search = webDriver.findElement(By.id("productSearch"));
        search.click();
        search.sendKeys(searchKey);
        webDriver.findElement(By.id("buttonProductSearch")).click();
        webDriver.findElement(By.cssSelector(".product-image.owl-lazy")).click();
        String productName = webDriver.findElement(By.cssSelector(".product-name.best-price-trick")).getText();
        webDriver.findElement(By.cssSelector(".favorite")).click(); // urunu favlara ekleme
        webDriver.findElement(By.linkText("Favori Listeme Git")).click();
        String productFavoriteListName = webDriver.findElement(By.id("ctl00_ContentPlaceHolder1_rptShoppingList_ctl01_hplProductName")).getText();
        Assert.assertEquals("Ürün fiyatı hatalı olarak sepette gözüküyor!!!",productName,productFavoriteListName);

    }

    public void login(String email, String password){
        webDriver.findElement(
                By.cssSelector(".insider-opt-in-notification-button.insider-opt-in-disallow-button"))
                .click();
        openLoginMenu();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
        webDriver.findElement(By.id("email")).sendKeys(email);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.cssSelector(".btn.full.btn-login-submit")).click();
    }

    public void openLoginMenu() {
        WebElement mainLoginButton = webDriver.findElement(By.id("myAccount"));
        mainLoginButton.click();
        Actions actions = new Actions(webDriver);
        actions.moveToElement(mainLoginButton).build().perform();
    }
}
