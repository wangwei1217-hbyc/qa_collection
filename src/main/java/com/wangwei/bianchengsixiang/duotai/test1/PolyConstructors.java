package com.wangwei.bianchengsixiang.duotai.test1;

/**
 * -构造函数并不具有多态性，它们实际上是static方法，只不过该static声明是隐式的。因此，构造函数不能够被override。

   -在父类构造函数内部调用具有多态行为的函数将导致无法预测的结果，因为此时子类对象还没初始化，此时调用子类方法不会得到我们想要的结果。

 -只有非private方法才可以被覆盖，但是还需要密切注意覆盖private方法的现象，这时虽然编译器不会报错，但是也不会按照我们所期望的来执行，
  即覆盖private方法对子类来说是一个新的方法而非重载方法。因此，在子类中，新方法名最好不要与基类的private方法采取同一名字（虽然没关系，但容易误解，以为能够覆盖基类的private方法）。
 */
class Glyph {
    void draw() {
        System.out.println("Glyph.draw()");
    }
    Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph(). radius = " + radius);
    }

    void draw() {
        System.out.println("RoundGlyph.draw(). radius = " + radius);
    }
}

public class PolyConstructors {

    /**
     * 输出结果：
     *      Glyph() before draw()
            RoundGlyph.draw(). radius = 0
            Glyph() after draw()
            RoundGlyph.RoundGlyph(). radius = 5

     分析：
     （1）在其他任何事物发生之前，将分配给对象的存储空间初始化成二进制0；
     （2）调用基类构造函数。从根开始递归下去，因为多态性此时调用子类覆盖后的draw()方法（要在调用RoundGlyph构造函数之前调用），由于步骤1的缘故，我们此时会发现radius的值为0；
     （3）按声明顺序调用成员的初始化方法；
     （4）最后调用子类的构造函数。
     */
    public static void main(String[] args) {
        new RoundGlyph(5);

    }

}
