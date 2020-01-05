package com.wangwei.ifelseTiHuan;

import java.util.HashMap;

/**
 * 角色工厂类
 * Created by wangwei on 2020/1/5 0005.
 */
public class RoleFactory {
    private static HashMap<String,RoleOperation> roleOperationMap= new HashMap<>();
    static {
        roleOperationMap.put("ROLE_ROOT_ADMIN",new RootAdminRole());
        roleOperationMap.put("ROLE_ORDER_ADMIN",new OrderAdminRole());
        roleOperationMap.put("ROLE_NORMAL",new NormalRole());
    }
    public static RoleOperation getInstance(String roleName){
        return roleOperationMap.get(roleName);
    }
}
