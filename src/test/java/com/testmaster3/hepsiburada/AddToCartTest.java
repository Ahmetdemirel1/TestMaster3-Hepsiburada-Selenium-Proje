package com.testmaster3.hepsiburada;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class AddToCartTest extends driverSettings {

    @Test
    public void addProduct() {

        new AddToCart(driver).addProduct("ahmetdemi@ahmetde.com","ahmet123", "Lacoste 2010893 Erkek Kol Saati "); // adress bilgisi olmayan hesap
        //new AddToCart(driver).addProduct("demirelahmet9@gmail.com","ahmet123", "Lacoste 2010893 Erkek Kol Saati ");
    }
}