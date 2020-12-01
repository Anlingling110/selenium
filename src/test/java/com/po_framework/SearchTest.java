package com.po_framework;

import org.junit.jupiter.api.Test;

public class SearchTest {
    @Test
    void search(){
        MainPage mainPage = new MainPage();
        SearchPage searchPage = mainPage.search();
        searchPage.search("selenium");
//        mainPage.search().search("selenium");
    }
}
