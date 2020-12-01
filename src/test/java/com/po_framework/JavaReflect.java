package com.po_framework;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class JavaReflect {
    @Test
    void reflect(){
        try {
            Class<?> className = Class.forName("java.lang.String");
            System.out.println(className.getMethods());
            Arrays.stream(className.getMethods()).forEach(m->System.out.println(m));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
