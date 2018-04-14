package com.wangwei.javadesign.simplefactory;

import java.math.BigDecimal;
import java.util.Scanner;
/**
 * 简单工厂模式
 * @author Administrator
 *
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字1：");
        String numA = scanner.nextLine();
        System.out.println("请输入操作符(+、-、*、/):");
        String operate = scanner.nextLine();
        System.out.println("请输入数字2：");
        String numB = scanner.nextLine();

        Operation operation = OperationFactory.createOperation(operate);
        operation.setNumA(new BigDecimal(numA));
        operation.setNumB(new BigDecimal(numB));

        String operateResult = operation.getResult();
        System.out.println("结果： "+numA +" "+operate + " "+numB +" = "+operateResult);

    }
}
