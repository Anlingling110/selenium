package com.WeChat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;
    //初始化webdriver
    //有参构造
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    //无参构造
    public BasePage() {
    }
    //封装By()方法
    void click(By by){
        driver.findElement(by).click();
    }
    //封装sendKeys()方法
    void sendkeys(By by,String content){
        driver.findElement(by).sendKeys(content);
    }
}
