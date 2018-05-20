package com.testmaster3.hepsiburada;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Login {
    WebDriver webDriver;
    public void openLoginMenu() {
        WebElement mainLoginButton = webDriver.findElement(By.id("myAccount"));
        mainLoginButton.click();
        Actions actions = new Actions(webDriver);
        actions.moveToElement(mainLoginButton).build().perform();
    }
}
