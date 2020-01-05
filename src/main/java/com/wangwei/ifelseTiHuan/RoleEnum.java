package com.wangwei.ifelseTiHuan;

/**
 * 角色枚举类
 * Created by wangwei on 2020/1/5 0005.
 */
public enum RoleEnum implements RoleOperation{
    //系统管理员(有AAA操作权限)
    ROLE_ROOT_ADMIN {
        @Override
        public String op() {
            return "ROLE_ROOT_ADMIN: " + "has AAA permission";
        }
    },
    //订单管理员(有BBB操作权限)
    ROLE_ORDER_ADMIN {
        @Override
        public String op() {
            return "ROLE_ORDER_ADMIN: " + "has BBB permission";
        }
    },
    //普通用户(有CCC操作权限)
    ROLE_NORMAL {
        @Override
        public String op() {
            return "ROLE_NORMAL: " + "has CCC permission";
        }
    };
}
