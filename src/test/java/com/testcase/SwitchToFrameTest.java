package com.testcase;
/**
 * 查看要操作的元素是否在frame中，若在，需要先切换到元素所在的frame
 * 若元素在子节点frame中，需先切换至parentFrame再切换至frame
 * driver.switchTo().frame("frameName");
 * driver.switchTo().parentFrame("parentFrameName");
 */

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SwitchToFrameTest extends Base {
    @Test
    void switchToFrame(){
            driver.get("https://www.runoob.com/try/try-cdnjs.php?filename=jqueryui-api-droppable");
            driver.switchTo().frame("iframeResult");
            System.out.println(driver.findElement(By.id("droppable")).getText());
//            driver.switchTo().parentFrame();
//            System.out.println(driver.findElement(By.id("submitBTN")).getText());
    }
}
