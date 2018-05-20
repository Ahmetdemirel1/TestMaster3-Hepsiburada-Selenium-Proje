package com.testmaster3.hepsiburada;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddToFavoriteTest extends driverSettings {

    @Test
    public void addFavorite() {
        new AddToFavorite(driver).addFavorite("demirelahmet9@gmail.com","ahmet123", "Apple iPhone X 256 GB (Apple Türkiye Garantili) ");
    }
}