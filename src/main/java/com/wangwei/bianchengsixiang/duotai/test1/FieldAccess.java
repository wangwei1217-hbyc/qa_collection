package com.wangwei.bianchengsixiang.duotai.test1;

class Super {
    public int field = 0;
    public int getField() {
        return field;
    }
}

class Sub extends Super {
    public int field = 1;
    public int getField() {
        return field;
    }
    public int getSuperField() {
        return super.field;
    }
}

/**
 * Java类中属性域的访问操作都由编译器解析，因此不是多态的。父类和子类的同名属性都会分配不同的存储空间
 */
public class FieldAccess {

    /**
     * 输出结果：
     * sup.filed = 0, sup.getField() = 1
     sub.filed = 1, sub.getField() = 1, sub.getSuperField() = 0
     * 分析：
     * Sub子类实际上包含了两个称为field的域，然而在引用Sub中的field时所产生的默认域并非Super版本的field域，
     * 因此为了得到Super.field，必须显式地指明super.field
     */
    public static void main(String[] args) {
        Super sup = new Sub();
        System.out.println("sup.filed = " + sup.field +
                ", sup.getField() = " + sup.getField());
        Sub sub = new Sub();
        System.out.println("sub.filed = " + sub.field +
                ", sub.getField() = " + sub.getField() +
                ", sub.getSuperField() = " + sub.getSuperField());
    }

}
