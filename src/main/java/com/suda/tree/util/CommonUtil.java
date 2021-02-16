package com.suda.tree.util;


import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class CommonUtil {

    public static Date SQLTimeToNormalTime(Date date){
        return DateUtil.parse(String.valueOf(date));
    }

}
