package com.wangwei.bianchengsixiang.reflect;

import java.util.Random;

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.random.nextInt(1000);

    static{
        System.out.println("Initializing Initable");
    }

}
class Initable2 {
    static int staticNotFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}
class Initable3 {
    static int staticNonFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}

/**
 * Java还提供了另一种方法来生成对Class对象的引用，即使用类字面常量。比如上面的就像这样：FancyToy.class;来引用。
 这样做不仅更简单，而且更安全，因为它在编译时就会受到检查（因此不需要置于try语句块中），并且它根除了对forName方法的引用，所以也更高效。类字面常量不仅可以应用于普通的类，也可以应用于接口、数组以及基本数据类型。

 --注意：当使用“.class”来创建对Class对象的引用时，不会自动地初始化该Class对象，初始化被延迟到了对静态方法（构造器隐式的是静态的）或者非final静态域（注意final静态域不会触发初始化操作）进行首次引用时才执行。
      而使用Class.forName时会自动的初始化。

 为了使用类而做的准备工作实际包含三个步骤：
 - 加载：由类加载器执行。查找字节码，并从这些字节码中创建一个Class对象
 - 链接：验证类中的字节码，为静态域分配存储空间，并且如果必需的话，将解析这个类创建的对其他类的所有引用。
 - 初始化：如果该类具有超类，则对其初始化，执行静态初始化器和静态初始化块。
 */
public class ClassInitialization {
    public static Random random = new Random(47);

    /**
     * 输出结果：
     *   After creating Initable ref
         47
         Initializing Initable
         258
         Initializing Initable2
         147
         Initializing Initable3
         After creating Initable3 ref
         74
     */
    public static void main(String[] args) {
        /**Does not trigger initialization*/
        Class<Initable> initable = Initable.class;
        System.out.println("After creating Initable ref");
        /**Does not trigger initialization */
        System.out.println(Initable.staticFinal);
        /**Does trigger initialization( rand() is static method ) */
        System.out.println(Initable.staticFinal2);

        /**Does trigger initialization(not final)*/
        System.out.println(Initable2.staticNotFinal);

        try {
            /**Does trigger initialization*/
            Class<?> initable3 = Class.forName("com.wangwei.bianchengsixiang.reflect.Initable3");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
