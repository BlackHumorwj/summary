package com.kotlin.for2021.java.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/07
 *     desc   : 自定义注解 https://www.liaoxuefeng.com/wiki/1252599548343744/1265102026065728
 *     version: 1.0
 * </pre>
 */

@Retention(RetentionPolicy.RUNTIME)//保留时间
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface Report {

    int type() default 0;

    String level() default "hh";

    String value() default "";


}
