package com.testcase;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class LoginTest extends Base {

    @Test
    void login(){
        driver.get("https://ceshiren.com/");
        driver.findElement(By.cssSelector(".d-button-label")).click();
        driver.findElement(By.id("login-account-name")).clear();
        driver.findElement(By.cssSelector(".ember-text-field ember-view")).sendKeys("111111");
        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.cssSelector(".ember-text-field ember-view")).sendKeys("111111");
        driver.findElement(By.xpath("//8[@class='d-button-label']")).click();
    }

}
