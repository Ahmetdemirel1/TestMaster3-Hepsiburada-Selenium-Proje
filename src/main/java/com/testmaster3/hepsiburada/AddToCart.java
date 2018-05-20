package com.testmaster3.hepsiburada;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddToCart  {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public AddToCart(WebDriver driver){
        this.webDriver = driver;
        this.webDriverWait = new WebDriverWait(driver, 30, 150);
    }

    public void addProduct(String email, String password, String searchKey){

        webDriver.get("https://www.hepsiburada.com/");
        login(email,password); //Login islemi icin olusturlan login Methodu
        productSearch(searchKey); // Urun arama methodu
        webDriver.findElement(By.cssSelector(".cart-proceed-container")).click();
        // Adres bilgisi var ise adresi sec yok ise yeni adres bilgileri ekle
        List<WebElement> addressControl = webDriver.findElements(By.cssSelector(".box-header-desc"));
        String controlWord = "Standart Teslimat dışındaki seçimleriniz için kargo ücreti yeniden hesaplanır."; //adres bilgisi kayitli ise bu cumle yardimi ile adresi seciyoruz
        String controlWordAdress = "Lütfen teslimat adres bilgilerinizi ve teslimat seçeneğini belirtin."; //Henuz adres bilgisi olmadigi icin bu cumle ile adresin olmadigini anlayarak addNewAddress methodunu calistiriyoruz
        if (controlWord.equals(addressControl.get(0).getText())) {// adres bilgisi yok ise yeni adres ekleme
            addNewAddress();
        }
        else if(controlWordAdress.equals(addressControl.get(0).getText())){ // adres bilgisi var ise adresi secerek devam etme
            List<WebElement> addressSelect = webDriver.findElements(By.cssSelector(".selectbox-choice"));
            addressSelect.get(0).click();
            webDriver.findElement(By.cssSelector(".invoice-with-order")).click();
        }
        webDriver.findElement(By.cssSelector(".proceed-container")).click();
        CreditCartDetails();

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


    public void CreditCartDetails(){

        WebElement cardNo = webDriver.findElement(By.id("card-no"));
        cardNo.click();
        cardNo.sendKeys("2525252525252525");
        WebElement cardName = webDriver.findElement(By.id("holder-Name"));
        cardName.click();
        cardName.sendKeys("Ahmet Demirel");
        List<WebElement> creditCartDate = webDriver.findElements(By.cssSelector(".btn.dropdown-toggle.selectpicker.btn-default"));
        creditCartDate.get(0).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'text')]  [contains(text(),'04')]")).click();
        creditCartDate.get(1).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'text')]  [contains(text(),'2028')]")).click();
        WebElement cvcNumber = webDriver.findElement(By.id("cvc"));
        cvcNumber.click();
        cvcNumber.sendKeys("123");
    }

    public  void productSearch(String searchKey){
        WebElement search = webDriver.findElement(By.id("productSearch"));
        search.click();
        search.sendKeys(searchKey);
        webDriver.findElement(By.id("buttonProductSearch")).click();
        webDriver.findElement(By.cssSelector(".product-image.owl-lazy")).click();
        String productPrice = webDriver.findElement(By.id("offering-price")).getText();
        webDriver.findElement(By.id("addToCart")).click(); // urunu sepete ekleme
        List<WebElement> cartPrice = webDriver.findElements(By.cssSelector(".total-price"));
        String cartPriceString = cartPrice.get(0).getText();
        Assert.assertEquals("Ürün fiyatı hatalı olarak sepette gözüküyor!!!",productPrice,cartPriceString);
    }


    public void addNewAddress(){
        webDriver.findElement(By.id("first-name")).sendKeys("Ahmet");
        webDriver.findElement(By.id("last-name")).sendKeys("Demirel");
        webDriver.findElement(By.id("address")).sendKeys("mahalle ismi ismi apartman adi no:");
        webDriver.findElement(By.id("address-name")).sendKeys("Ev adresi");
        webDriver.findElement(By.id("phone")).sendKeys("(530) 847-5885");
        List<WebElement> selectBox = webDriver.findElements(By.cssSelector(".caret"));
        selectBox.get(2).click();
        webDriver.findElement(By.xpath("//span[contains(@class,'text')]  [contains(text(),'ATAŞEHİR')]")).click();
    }
}
