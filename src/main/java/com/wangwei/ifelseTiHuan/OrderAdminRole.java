package com.wangwei.ifelseTiHuan;

/**
 * Created by wangwei on 2020/1/5 0005.
 */
public class OrderAdminRole implements RoleOperation {
    @Override
    public String op() {
        return "ROLE_ORDER_ADMIN: " + "has BBB permission";
    }
}
