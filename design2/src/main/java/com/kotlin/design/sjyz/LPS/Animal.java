package com.kotlin.design.sjyz.LPS;

import java.math.BigDecimal;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/11
 *     desc   : 改进版
 *     参考    : http://c.biancheng.net/view/1324.html
 *     version: 1.0
 * </pre>
 */

class Animal {

    double runSpeed;

    public void setRunSpeed(double speed) {
        this.runSpeed = speed;
    }

    public double getRunTime(double distance) {
        return new BigDecimal(distance).divide(new BigDecimal(runSpeed)).doubleValue();
    }

}

class Bird2 extends Animal {

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
class Swallow2 extends Bird2 {

}

/**
 * 几维鸟
 */
class BrownKiwi2 extends Animal {


}

class LPSTest2 {

    public static void main(String[] args) {
        Bird2 bird = new Swallow2();

        Animal bird1 = new BrownKiwi2();


        bird.setSpeed(200);

        bird1.setRunSpeed(100);

        try {
            System.out.println("燕子飞行需要时间：" + bird.getFlyTime(200));
            System.out.println("几维鸟奔跑需要时间："+bird1.getRunTime(200));
        } catch (Exception e) {
            System.out.println("发生错误了!");
        }


    }

}

