package com.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestDemo {
    //定义yaml中的两个字段data/steps
    public List<String> data;
    public List<HashMap<String,Object>> steps;
    private ChromeDriver driver;
    private WebElement currentElement;
    public int index = 0;//定义下标，通过下标获取data中的value
    //测试数据参数化
    //封装外层List数据(用例数据)
    public List<TestDemo> testdemo_generate(){
        List<TestDemo> testDemoList = new ArrayList<>();
        //遍历内层data数据
        for(int i = 0; i < data.size(); i++){
            TestDemo testDemoNew = new TestDemo();
            testDemoNew.index = i;//给index赋值
            testDemoNew.steps = steps;//steps的值赋值给testDemoNew中steps
            testDemoNew.data = data;//data的值赋值给testDemoNew中data
            testDemoList.add(testDemoNew);//遍历后的testDemoNew添加到List中
        }
        return testDemoList;
    }

    //getValue()方法有默认值implicitly_wait
    private Object getValue(HashMap<String,Object> step, String key, Object defaultValue){
        return step.getOrDefault(key,defaultValue);
    }
    //getValue()方法无默认值
    private Object getValue(HashMap<String,Object> step, String key){
        Object value = step.get(key);//通过key，获取value
        //若value是String
        if(value instanceof String){
            //则替换{data}中的数据
            return ((String) value).replace("${data}",data.get(index));//根据index取data中的value
        }else {
            return value;
        }
    }
    //测试步骤参数化
    public void run(){
        steps.forEach(step-> {
            if(step.keySet().contains("chrome")){
                driver = new ChromeDriver();
            }
            if(step.keySet().contains("sleep")){
                try {
                    Thread.sleep(Long.valueOf(getValue(step,"sleep").toString()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (step.keySet().contains("implicitly_wait")){
                driver.manage().timeouts().implicitlyWait((int)getValue(step,"implicitly_wait",5),
                        TimeUnit.SECONDS);
            }
            if(step.keySet().contains("get")){
                driver.get(step.get("get").toString());
            }
            if(step.keySet().contains("find")){
                ArrayList<By> bys = new ArrayList<>();
                ((HashMap<String,String>)getValue(step,"find")).entrySet().forEach(stringStringEntry -> {
                    if (stringStringEntry.getKey().contains("id")){
                        bys.add(By.id(stringStringEntry.getValue()));
                    }
                    if (stringStringEntry.getKey().contains("xpath")){
                        bys.add(By.xpath(stringStringEntry.getValue()));
                    }
                });
                currentElement = driver.findElement(bys.get(0));
            }
            if(step.keySet().contains("click")){
                currentElement.click();
            }
            if(step.keySet().contains("send_keys")){
                currentElement.sendKeys(getValue(step,"send_keys").toString());
            }
            if(step.keySet().contains("quit")){
                driver.quit();
            }
        });
    }

}
