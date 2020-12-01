package com.po_framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class BasePage {
    WebDriver driver;
    static BasePage instance = null;//BasePage初始化单例，存所有PO
    HashMap<String, BasePage> pages = new HashMap<>();
    public BasePage(){
    }
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    //创建单例
    public static BasePage getInstance(){
        //单例为空则创建
        if(instance == null){
            instance = new BasePage();
        }
        return instance;
    }
    void click(By by){
        driver.findElement(by).click();
    }
    void sendKeys(By by,String str){
        driver.findElement(by).sendKeys(str);
    }
    //使用反射，动态创建PO
    void poInit(String name, String className){
        try {
            BasePage pageClass = (BasePage) Class.forName(className).getDeclaringClass().newInstance();
            //创建出来的实例和类，封装为PO
            pages.put(name,pageClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    BasePage getPO(String name){
        return pages.get(name);
    }
    //反射方法
    void stepRun(String method){
        Method reflectMethod = Arrays.stream(this.getClass().getMethods())
                .filter(m->m.getName().equals(m))
                .findFirst().get();
        //this.search()
        try {
            reflectMethod.invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
