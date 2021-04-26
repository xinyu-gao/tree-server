package com.suda.tree.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestUtil {
    public static void main(String[] args) {
        log.info(String.valueOf(DateUtil.parse("2021-02-14T04:16:17.000+00:00")));
        int i = 7;
        log.info(String.valueOf(i));
        log.info(String.valueOf((i--) - 3));
        People p = new People();

    }

    public static class People {
        private int age;

        People(int age) {
            this.age = age;
        }

        public People() {

        }
    }

    public static class Student extends People {
        private int age;

        Student(int age) {
            super(age);
        }
    }
}
