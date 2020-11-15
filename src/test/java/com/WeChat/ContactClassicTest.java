package com.WeChat;
/**
 * 企业微信登录流程：
 * 1、启动浏览器driver
 * 2、打开浏览器
 * 3、输入网址
 * 4、用企业微信扫二维码
 * 5、等待扫码动作以便获取cookie
 * 6、获取cookie
 * 7、存储cookie
 * 8、读取cookie
 * 9、复用cookie
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactClassicTest {
    private static WebDriver driver;
    static File file =new File("cookies.yaml");
    static void needLogin() throws Exception{
        driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?redirect_uri=https://work.weixin.qq.com/wework_admin/frame");
        //获取cookie
        Thread.sleep(15000);//等待时间用于扫码
        Set<Cookie> cookies = driver.manage().getCookies();
        //存储cookie
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(file,cookies);
        System.exit(0);
    }
    //封装By()方法
    void click(By by){
        driver.findElement(by).click();
    }
    //封装sendKeys()方法
    void sendkeys(By by,String content){
        driver.findElement(by).sendKeys(content);
    }

    @BeforeAll
    static void alreadyHaveCookie() throws Exception {
        driver = new ChromeDriver();
        if(file.exists()) {
            driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?redirect_uri=https://work.weixin.qq.com/wework_admin/frame");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference = new TypeReference<List<HashMap<String, Object>>>() {
            };
            //读取cookie
            List<HashMap<String, Object>> cookies = mapper.readValue(file, typeReference);
//            System.out.println(cookies);
            cookies.forEach(cookieMap -> {
                driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(), cookieMap.get("value").toString()));
            });
            driver.navigate().refresh();
        }else {
            needLogin();
        }
    }
//    @Test
//    void contackAdd(){
//        driver.findElement(By.linkText("添加成员")).click();
//        driver.findElement(By.name("username")).sendKeys("1");
//        driver.findElement(By.name("acctid")).sendKeys("1");
//        driver.findElement(By.name(("mobile"))).sendKeys("13111111111");
//        driver.findElement(By.linkText("保存")).click();
//    }
    @Test
    void contackAdd(){
        click(By.linkText("添加成员"));
        sendkeys(By.name("username"),"1");
        sendkeys(By.name("acctid"),"1");
        sendkeys(By.name("mobile"),"13111111111");
        click(By.linkText("保存"));
    }
    @Test
    void departSearch(){
        click(By.linkText("通讯录"));
        sendkeys(By.id("memberSearchInput"),"销售部");
//        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
//        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));
//        System.out.println(content);
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        assertTrue(content.contains("无任何成员"));

    }
}
