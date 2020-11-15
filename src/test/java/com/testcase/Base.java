package com.testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Base {
    public static WebDriver driver;
    @BeforeAll
    public static void init(){
        driver = new ChromeDriver();
    }
    @BeforeEach
    void waitFor(){
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

    }
//    @AfterAll
//    public static void tearDown(){
//        driver.quit();
//    }
}
