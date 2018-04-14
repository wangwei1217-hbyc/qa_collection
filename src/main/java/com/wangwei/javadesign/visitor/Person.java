package com.wangwei.javadesign.visitor;

public interface Person {
    void accept(Visitor visitor);//接收访问者对象(成功、失败等),执行特定的行为
}
