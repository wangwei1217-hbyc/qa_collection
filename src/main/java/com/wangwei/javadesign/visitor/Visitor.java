package com.wangwei.javadesign.visitor;
/**
 * 访问者对象接口
 * 		相当于本例中的状态:如成功、失败、恋爱等，而这些状态是变化的，但人的性别只有男、女两种，是相对稳定的
 * @author Administrator
 *
 */
public interface Visitor {
    //得到男人结论或反应
    void getManConclusion(Man man);
    //得到女人结论或反应
    void getWomanConclusion(Woman woman);
}
