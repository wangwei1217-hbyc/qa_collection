package com.wangwei.ifelseTiHuan;

/**
 * Created by wangwei on 2020/1/5 0005.
 */
public class NormalRole implements RoleOperation {
    @Override
    public String op() {
        return "ROLE_NORMAL: " + "has CCC permission";
    }
}
