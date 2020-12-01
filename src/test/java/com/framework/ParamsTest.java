package com.framework;
/**
 * 数据驱动演变封装
 * 1、ValueSource(strings = {"value1","value2",...})
 * 2、MethodSource()，使用Stream返回的数据，Stream方法需同测试方法名一致
 * 3、MethodSource()，使用外部文件，例如.yaml文件
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ParamsTest {
    ChromeDriver driver;
    //基本case
    @Test
    void BaseSearch() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).click();
        driver.findElement(By.id("search-term")).sendKeys("keyword");
        driver.quit();
    }
//  参数化方式一
    @ParameterizedTest
    @ValueSource(strings = {"demo1","demo2"})
    void ValueSourceSearch(String keyword) {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("https://ceshiren.com/");
    driver.findElement(By.id("search-button")).click();
    driver.findElement(By.id("search-term")).click();
    driver.findElement(By.id("search-term")).sendKeys(keyword);
    }

    //参数化方式二2.1
    @ParameterizedTest
    @MethodSource()//使用同名方法中Stream返回的数据
    void MethodSourceSearch1(String keyword) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).click();
        driver.findElement(By.id("search-term")).sendKeys(keyword);
    }
    static Stream<String> MethodSourceSearch1(){
        return Stream.of("appium","selenium");
    }
    //参数化方式二2.2
    @ParameterizedTest
    @MethodSource
    void MethodSourceSearch2(String keyword){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys(keyword);
    }
    static List<String> MethodSourceSearch2() throws IOException {
        ObjectMapper mapper =new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<String>>() {};
        List<String> keywords = mapper.readValue(ParamsTest.class.getResourceAsStream("/framework/test.yaml"),
                typeReference);
        return keywords;
    }
    //参数化方式二2.3
    @ParameterizedTest
    @MethodSource()//使用外部文件数据
    void MethodSourceSearch3(TestDemo testDemo){
        System.out.println(testDemo);
        testDemo.run();
    }
    static List<TestDemo> MethodSourceSearch3() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestDemo testDemo = mapper.readValue(ParamsTest.class.getResourceAsStream("/framework/search.yaml"),
                TestDemo.class);
        return testDemo.testdemo_generate();//调用方法，读取List
        }
}
