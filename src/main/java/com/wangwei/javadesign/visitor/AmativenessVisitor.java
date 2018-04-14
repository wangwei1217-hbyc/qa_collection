package com.wangwei.javadesign.visitor;

public class AmativenessVisitor implements Visitor{

    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人恋爱时，凡事不懂也要装懂！");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人恋爱时，遇事懂也装作不懂！");
    }

}

