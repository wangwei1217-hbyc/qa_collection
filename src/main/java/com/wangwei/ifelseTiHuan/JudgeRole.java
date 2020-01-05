package com.wangwei.ifelseTiHuan;

/**
 * Created by wangwei on 2020/1/5 0005.
 */
public class JudgeRole {
    //传统if else代码示范
    public String judge(String roleName){
        String result = "";
        if(roleName.equals("ROLE_ROOT_ADMIN")){
            //系统管理员有AAA权限
            result = "ROLE_ROOT_ADMIN: " + "has AAA permission";
        }else if(roleName.equals("ROLE_ORDER_ADMIN")){
            //订单管理员有BBB权限
            result = "ROLE_ORDER_ADMIN: " + "has BBB permission";
        }else if(roleName.equals("ROLE_NORMAL")){
            //普通用户有CCC权限
            result = "ROLE_NORMAL: " + "has CCC permission";
        }else{
            result = "XX";
        }
        return result;
    }
    //通过角色枚举类来实现
    public String judgeByEnum(String roleName){
        return RoleEnum.valueOf(roleName).op();
    }

    //通过角色工厂类来实现
    public String judgeByFactory(String roleName){
        return RoleFactory.getInstance(roleName).op();
    }

    public static void main(String[] args) {
        String roleName = RoleEnum.ROLE_ROOT_ADMIN.name();
        JudgeRole judgeRole = new JudgeRole();
        System.out.println("----judge---");
        System.out.println(judgeRole.judge(roleName));
        System.out.println("---judgeEnum----");
        System.out.println(judgeRole.judgeByEnum(roleName));
        System.out.println("---judgeFactory----");
        System.out.println(judgeRole.judgeByFactory(roleName));
    }
}
