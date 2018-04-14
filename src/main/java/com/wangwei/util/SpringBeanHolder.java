package com.wangwei.util;

import org.springframework.web.context.WebApplicationContext;

/**
 * Created by ray on 2017/5/3.
 */
public class SpringBeanHolder {
    private static WebApplicationContext wac;

    public SpringBeanHolder() {
    }

    public static void setWebApplicationContext(WebApplicationContext context) {
        wac = context;
    }

    public static Object getBean(String beanName) {
        return beanName != null && !"".equals(beanName)?wac.getBean(beanName):null;
    }
}
