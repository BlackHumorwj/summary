package com.kotlin.design.sjyz.openClose.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class OrgDemo {

    /**
     * 计算折扣金额
     *
     * @param vip
     * @param amount
     * @return
     */
    public double getDiscountAmount(int vip, double amount) {

        switch (vip) {
            case 0:
                return amount;
            case 1:
                return amount * 0.7;

            case 2:
                return amount * 0.5;

        }
        return amount;
    }
}


enum VipLevel {

    NORMAL, STANDARD

}

/**
 * <pre>
 *     author : jinBao
 *     time   : 2022/5/10  11:05
 *     desc   : 策略基类
 * </pre>
 */

interface CalDiscountStrategy {

    double getDiscountAmount(double amount);

    VipLevel getVipLevel();


}


class NormalCustomerStrategy implements CalDiscountStrategy {

    @Override
    public double getDiscountAmount(double amount) {
        return amount;
    }

    @Override
    public VipLevel getVipLevel() {
        return VipLevel.NORMAL;
    }
}

/**
 * <pre>
 *     author : jinBao
 *     time   : 2022/5/10  15:51
 *     desc   : 策略的实现
 * </pre>
 */

class StandardCustomerStrategy implements CalDiscountStrategy {

    @Override
    public double getDiscountAmount(double amount) {
        return new BigDecimal(amount).multiply(new BigDecimal("0.7")).doubleValue();
    }

    @Override
    public VipLevel getVipLevel() {
        return VipLevel.STANDARD;
    }
}

/**
 * <pre>
 *     author : jinBao
 *     time   : 2022/5/10  15:51
 *     desc   : 策略创建
 * </pre>
 */

class Context {

    private static final HashMap<VipLevel, CalDiscountStrategy> map = new HashMap<>();

    static {
        map.put(VipLevel.NORMAL, new NormalCustomerStrategy());
        map.put(VipLevel.STANDARD, new StandardCustomerStrategy());
    }


    public static CalDiscountStrategy getStrategy(VipLevel level) {
        //可以使用简单工厂进行改造
        return map.get(level);
    }



}


class StrategyFactory {

  private static List<CalDiscountStrategy> mList = new ArrayList<CalDiscountStrategy>() {
        {
            this.add(new NormalCustomerStrategy());
            this.add(new StandardCustomerStrategy());
        }
    };

  //工厂模式，创建对象
    public static CalDiscountStrategy getStrategy(VipLevel level) {
        for (CalDiscountStrategy strategy : mList) {
            if (strategy.getVipLevel() == level) {
                return strategy;
            }
        }
        return null;
    }
}


class  StrategyClass{


    public static void main(String[] args) {

        //策略模式，实现开闭原则
        final CalDiscountStrategy strategy = Context.getStrategy(VipLevel.STANDARD);
        System.out.println(strategy.getDiscountAmount(45));


        final CalDiscountStrategy strategy1 = StrategyFactory.getStrategy(VipLevel.NORMAL);
        assert strategy1 != null;
        final double discountAmount = strategy1.getDiscountAmount(56.4);


    }

}







