package com.aries.api.demo.util;

import java.util.UUID;

/**
 * @Author: AriesHoo on 2019/2/12 14:13
 * @E-Mail: AriesHoo@126.com
 * @Function: 应用常用工具
 * @Description:
 */
public class AppUtil {

    /**
     * 获取id
     *
     * @return
     */
    public static String getId() {
        String id = UUID.randomUUID().toString().replace("-", "");
        return id.length() > 32 ? id.substring(0, 32) : id;
    }
}
