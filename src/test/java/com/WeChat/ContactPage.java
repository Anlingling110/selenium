package com.WeChat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 封装方法：addMember()，与MainPage是两个方法
 */
public class ContactPage extends BasePage{
    //private 原则2：不对外暴露页面内部细节
    private By departInfo = By.cssSelector(".js_party_info");;

    //保存driver到自己实例中，调用super()即可实现
    public ContactPage(WebDriver driver) {
        super(driver);
    }
    //原则6：相同的方法不同的结果，需要封装为不同的方法
    //添加成功
    public ContactPage MemberAdd(String name, String accid, String mobile){//添加成员时需要输入三个参数
        //添加成员之后还是留在此页面，所以返回自身
        return this;
    }
    //添加失败
//    public ContactPage MemberAddFail(String name, String accid, String mobile){//添加成员时需要输入三个参数
//        //添加成员之后还是留在此页面，所以返回自身
//        return this;
//    }
    //原则1：用公共方法代表页面提供的功能
    //原则3：PO内不添加断言
    //原则5：按需写方法，无需全部写出来
    public ContactPage departSearch(String departName){//搜索部门时需要输入一个参数
        sendkeys(By.id("memberSearchInput"),"销售部");
//        String content = driver.findElement(departInfo).getText();
//        System.out.println(content);
        click(By.cssSelector(".ww_icon_AddMember"));//点击操作以确定页面加载完成
        return this;
    }
    public String getDepartInfo(){
        String content = driver.findElement(departInfo).getText();
//        System.out.println(content);
        return content;
    }
    public ContactPage departAdd(String departName){
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
//        sendkeys(By.cssSelector(".qui_inputText ww_inputText"),"财务部");
        sendkeys(By.name("name"),departName);
        click(By.linkText("选择所属部门"));
        click(By.linkText("销售部"));//找不到？
        click(By.linkText("确定"));
        return this;
    }
//    public void clearAllDepart() {
//        click(By.xpath("//*[@class='member_colRight_memberTable_th_Checkbox']"));
//        click(By.linkText("删除"));
//        click(By.linkText("确定"));
//    }

}
