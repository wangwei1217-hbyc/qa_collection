package com.wangwei.util;

import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * 主键生成工具类
 */
public class UUIDUtil {
    /**
     * uuid生成字符串去掉-
     *
     * @return
     */
    public static String randomCommonUuid() {
        UUID uuid = UUID.randomUUID();
        return StringUtils.replace(uuid.toString(), "-", "");
    }

    public static void main(String[] args) {
        System.out.println(randomCommonUuid());
    }
}
