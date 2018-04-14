package com.wangwei.javadesign.visitor;


import java.util.ArrayList;
import java.util.List;

/**
 * 对象结构类：由于总是需要男人和女人在不同状态下的对比，
 * 所以我们需要一个对象结构类针对不同的状态遍历男人和女人，得到不同的行为。
 * @author Administrator
 *
 */
public class ObjectStructure {
    private List<Person> persons = new ArrayList<Person>();
    //增加
    public void add(Person person){
        persons.add(person);
    }

    public void remove(Person person){
        persons.remove(person);
    }

    //查看显示
    public void display(Visitor visitor){
        for(Person p : persons){
            p.accept(visitor);
        }
    }
}
