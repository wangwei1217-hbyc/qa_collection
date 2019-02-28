package com.wangwei.bianchengsixiang.generic;

/**
 * 泛型方法：
 *
 */
public class GenericTest9 {
    public <K,E,T> void f(K k,E e,T t){
        if(!k.getClass().getName().equals(e.getClass().getName())
                && !k.getClass().getName().equals(t.getClass().getName())
                && !e.getClass().getName().equals(t.getClass().getName())){
            System.out.println("k:"+k.getClass().getName());
            System.out.println("e:"+e.getClass().getName());
            System.out.println("t:"+t.getClass().getName());
        }else{
            System.out.println("参数类型不合法：有相同类型参数");
        }
    }

    public <E,T> void f(String k,E e,T t){
        if(!k.getClass().getName().equals(e.getClass().getName())
                && !k.getClass().getName().equals(t.getClass().getName())
                && !e.getClass().getName().equals(t.getClass().getName())){
            System.out.println("k1:"+k.getClass().getName());
            System.out.println("e1:"+e.getClass().getName());
            System.out.println("t1:"+t.getClass().getName());
        }else{
            System.out.println("参数类型不合法-1：有相同类型参数");
        }
    }

    public static void main(String[] args) {
        GenericTest9 genericTest9 = new GenericTest9();
        genericTest9.f("a","",0.1f);
    }
}
