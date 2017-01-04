package me.flyness.toolbox.annotation;

import java.lang.annotation.*;

/**
 * Created by bjlizhitao on 2016/8/17.
 * 标注暂未使用的类、方法
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unused {
    /**
     * 未使用说明
     *
     * @return
     */
    String desc() default "";
}
