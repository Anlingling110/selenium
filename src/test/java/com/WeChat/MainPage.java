package com.WeChat;
/**
 * MainPage封装方法：addMember()
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    File file =new File("cookies.yaml");
    void needLogin() throws Exception {
        driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx?redirect_uri=https://work.weixin.qq.com/wework_admin/frame");
        //获取cookie
        Thread.sleep(15000);//等待时间用于扫码
        Set<Cookie> cookies = driver.manage().getCookies();
        //存储cookie
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(file, cookies);
        System.exit(0);
    }
    void alreadyHaveCookie() throws Exception {
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
    public MainPage() throws Exception {
        alreadyHaveCookie();
    }
    public ContactPage mainMemberAdd(){
        click(By.linkText("添加成员"));
        //点击添加成员后调到另一个PO页面，所以要返回另一页面的PO，将driver传递过去
        //原则4：返回其他PO对象
        return new ContactPage(driver);
    }

}
