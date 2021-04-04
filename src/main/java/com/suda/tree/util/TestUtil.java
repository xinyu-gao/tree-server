package com.suda.tree.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
public class TestUtil {
    public static void main(String[] args) {
        log.info(String.valueOf(DateUtil.parse("2021-02-14T04:16:17.000+00:00")));
        People p = new Student();
        Student s = (Student)p;
    }
    public static class People{
        private int age;
    }
    public static class Student extends People{
        private int age;
    }
}
