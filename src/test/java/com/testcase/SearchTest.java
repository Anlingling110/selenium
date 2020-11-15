package com.testcase;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SearchTest extends Base {
    @Test
    void search() throws InterruptedException {
        driver.get("https://ceshiren.com/");
        //cssSelector定位(id-#,class-.)
//        driver.findElement(By.cssSelector("#search-button")).click();
        //cssSelector定位(class-.父孙节点)
        driver.findElement(By.cssSelector(".search-dropdown .d-icon-search")).click();
        //xpath定位(//*[@节点名=value值])
//        driver.findElement(By.xpath("//*[@title='搜索主题、帖子、用户或分类']"));
        //id定位
        driver.findElement(By.id("search-term")).sendKeys("selenium");
        Thread.sleep(3000);
    }
}
