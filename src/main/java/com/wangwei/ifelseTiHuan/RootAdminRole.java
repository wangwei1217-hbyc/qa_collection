package com.wangwei.ifelseTiHuan;

/**
 * Created by wangwei on 2020/1/5 0005.
 */
public class RootAdminRole implements RoleOperation {
    @Override
    public String op() {
        return "ROLE_ROOT_ADMIN: " + "has AAA permission";
    }
}
