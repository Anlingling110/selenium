package com.testcase;
/**
 * 多窗口处理流程：
 * 1、获取当前旧窗口句柄 driver.getWindowHandle()
 * 2、获取新窗口所有句柄 driver.getWindowHandles()
 * 3、遍历新窗口所有句柄，若所有句柄没有等于原窗口句柄的，则切换到新窗口句柄再做操作；若想在旧窗口做操作，需先切回到旧窗口句柄
 * for(String win:driver.getWindowHandles()){
 *     if(!win.equals(当前旧窗口句柄)){
 *          driver.switchTo.Window(新窗口句柄)
 *     }
 * }
 */

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Set;

public class SwitchToWindowTest extends Base {
    @Test
    void switchToWindow() throws Exception {
        driver.get("https://www.baidu.com");
        //最大化窗口，否则有可能找不到元素
        driver.manage().window().maximize();
        driver.findElement(By.linkText("登录")).click();
        //1、获取百度首页窗口句柄
        String indexHandle = driver.getWindowHandle();
        driver.findElement(By.linkText("立即注册")).click();
        //点击立即注册后会打开一个新的窗口
        //2、点击登录之后获取新窗口所有句柄
        Set<String> allHandles = driver.getWindowHandles();
        //3、遍历新窗口句柄并判断
        for(String win: allHandles){
            //新窗口句柄中如果没有旧窗口的句柄
            if(!win.equals(indexHandle)){
                System.out.println("这是一个新窗口，若想对新窗口操作，请先切换到新窗口");
                //则先切换到新窗口，再做操作
                driver.switchTo().window(win);
                driver.findElement(By.id("TANGRAM__PSP_4__userName")).sendKeys("111111");
                driver.findElement(By.id("TANGRAM__PSP_4__phone")).sendKeys("13111111111");
                driver.findElement(By.id("TANGRAM__PSP_4__password")).sendKeys("111111");
                //切回到原窗口再做操作
                driver.switchTo().window(indexHandle);
                driver.findElement(By.id("TANGRAM__PSP_11__footerULoginBtn")).click();
                driver.findElement(By.id("TANGRAM__PSP_11__userName")).sendKeys("13111111111");
                driver.findElement(By.id("TANGRAM__PSP_11__password")).sendKeys("111111");
            }
        }
        Thread.sleep(3000);
    }
}
