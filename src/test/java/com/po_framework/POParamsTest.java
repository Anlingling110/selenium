package com.po_framework;
/**
 * 数据驱动演变封装
 * 1、ValueSource(strings = {"value1","value2",...})
 * 2、MethodSource()，使用Stream返回的数据，Stream方法需同测试方法名一致
 * 3、MethodSource()，使用外部文件，例如.yaml文件
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import junit.framework.TestCase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;

public class POParamsTest extends TestCase {
    @ParameterizedTest
    @MethodSource()//使用page_search.yaml数据
    void MethodSourceSearch(POTestCase poTestCase){
        System.out.println(poTestCase);
        poTestCase.run();
    }
    static List<POTestCase> MethodSourceSearch() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        POTestCase poTestCase = mapper.readValue(POParamsTest.class.getResourceAsStream("/framework/search_po_test" +
                        ".yaml"),
                POTestCase.class);
        return poTestCase.testcase_generate();//调用方法，读取List
        }
}
