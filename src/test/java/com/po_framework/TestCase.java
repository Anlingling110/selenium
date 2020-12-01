package com.po_framework;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCase {
    public List<String> data;
    public List<HashMap<String, Object>> steps;
    public int index = 0;
    public List<POTestCase> testcase_generate(){
        List<POTestCase> poTestCaseList = new ArrayList<>();
        //遍历内层data数据
        for(int i = 0; i < data.size(); i++){
            POTestCase poTestCaseNew = new POTestCase();
            poTestCaseNew.index = i;//给index赋值
            poTestCaseNew.steps = steps;//steps的值赋值给testDemoNew中steps
            poTestCaseNew.data = data;//data的值赋值给testDemoNew中data
            poTestCaseList.add(poTestCaseNew);//遍历后的testDemoNew添加到List中
        }
        return poTestCaseList;
    }

    //getValue()方法有默认值implicitly_wait
    public Object getValue(HashMap<String,Object> step, String key, Object defaultValue){
        return step.getOrDefault(key,defaultValue);
    }
    //getValue()方法无默认值
    public Object getValue(HashMap<String,Object> step, String key){
        Object value = step.get(key);//通过key，获取value
        //若value是String
        if(value instanceof String){
            //则替换{data}中的数据
            return ((String) value).replace("${data}",data.get(index));//根据index取data中的value
        }else {
            return value;
        }
    }
    public void run(){

    }
}
