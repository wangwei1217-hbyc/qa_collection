package com.wangwei.javadesign.visitor;

public class VisitorTest {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new Man());
        objectStructure.add(new Woman());

        //成功时的反应
        SuccessVisitor successVisitor = new SuccessVisitor();
        objectStructure.display(successVisitor);

        //失败时的反应
        FailVisitor failVisitor = new FailVisitor();
        objectStructure.display(failVisitor);

        //恋爱时的反应
        AmativenessVisitor amativenessVisitor = new AmativenessVisitor();
        objectStructure.display(amativenessVisitor);

    }
}
