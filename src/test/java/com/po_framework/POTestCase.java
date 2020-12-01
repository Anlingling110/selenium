package com.po_framework;

import java.util.ArrayList;

public class POTestCase extends TestCase{
    //测试步骤参数化
    public void run(){
        steps.forEach(step-> {
            String key = step.keySet().iterator().next();

            if (step.keySet().contains("init")){
                //获取的两个值存入String[],需要做类型转换
                ArrayList<String> value = (ArrayList<String>) getValue(step, "init");
                //BasePage调用单例instance,调用init()方法，动态创建的类存入PO
                BasePage.getInstance().poInit(value.get(0), value.get(1));
            }
            if(step.keySet().contains(".")){
                String[] objectMethod = key.split(".");
                String object = objectMethod[0];
                String method = objectMethod[1];
                BasePage.getInstance().getPO(object).stepRun(method);
            }
        });
    }

}
