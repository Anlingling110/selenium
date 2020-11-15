package com.WeChat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * test:
 * 1、打开页面
 * 2、复用session登录
 * 3、跳转页面
 * 4、部门搜索
 */

public class ContactPOTest {
    //要想使用MainPage，需先创建MainPage对象
    //多处用到，可以作为成员变量使用
    MainPage mainPage;
    @BeforeEach
    public void BeforeEach() throws Exception {
        mainPage = new MainPage();
//        mainPage.mainMemberAdd().clearAllDepart();
    }
    @Test
    void testAddMember() throws Exception {
        //对象调用要想使用MainPage页mainAddMember()进入ContactPage页，再调用进入ContactPage页addMember()方法
        //调用结果返回的是ContactPage对象
//        ContactPage contactPage = mainPage.mainMemberAdd().memberAdd("2","2","13111111111");
        ContactPage contactPage = mainPage.mainMemberAdd();
        contactPage.departSearch("销售部");
        String content = contactPage.getDepartInfo();
        assertTrue(content.contains("无任何成员"));
    }
//    等价于
//    @Test
//    void testAddMember() throws Exception {
//        assertTrue(new MainPage().mainMemberAdd().departSearch("销售部").getDepartInfo().contains("无任何成员"));
//    }

    @Test
    void testDepartAdd() throws Exception {
        String departName = "财务部";
        assertTrue(mainPage.mainMemberAdd().departAdd(departName).departSearch(departName).getDepartInfo().contains(departName));
    }
}
