package com.wangwei.listener;

import com.wangwei.util.SpringBeanHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by ray on 2017/5/3.
 */
public class RootConfig implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.destroySpring(sce.getServletContext());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.initSpring(sce.getServletContext());
    }

    /**
     * 加载spring的context，通过将spring的context存储在静态变量中来实现与ServletContext的解耦
     *
     * @param context
     */
    private void initSpring(ServletContext context) {
        SpringBeanHolder.setWebApplicationContext(WebApplicationContextUtils
                .getRequiredWebApplicationContext(context));
    }

    private void destroySpring(ServletContext context) {
        SpringBeanHolder.setWebApplicationContext(null);
    }

}
