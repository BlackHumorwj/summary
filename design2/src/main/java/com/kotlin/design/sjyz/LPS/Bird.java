package com.kotlin.design.sjyz.LPS;

import java.math.BigDecimal;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/11
 *     desc   : 鸟类的基类
 *     参考    : http://c.biancheng.net/view/1324.html
 *     version: 1.0
 * </pre>
 */

class Bird {

    double flySpeed;

    public void setSpeed(double speed) {
        this.flySpeed = speed;
    }

    public double getFlyTime(double distance) {
        return new BigDecimal(distance).divide(new BigDecimal(flySpeed)).doubleValue();
    }

}

/**
 * 燕子
 */
class Swallow extends Bird {

}

/**
 * 几维鸟
 */
class BrownKiwi extends Bird {

    @Override
    public void setSpeed(double speed) { //违背了里氏替换原则，不要重写父类非抽象的方法
        flySpeed = 0;
    }
}

class LPSTest {

    public static void main(String[] args) {
        Bird bird = new Swallow();

        Bird bird1 = new BrownKiwi();

        bird.setSpeed(200);
        bird1.setSpeed(100);

        try {
            System.out.println("燕子飞行需要时间：" + bird.getFlyTime(200));
            System.out.println("几维鸟飞行需要时间："+bird1.getFlyTime(200));
        } catch (Exception e) {
            System.out.println("发生错误了!");
        }


    }

}

