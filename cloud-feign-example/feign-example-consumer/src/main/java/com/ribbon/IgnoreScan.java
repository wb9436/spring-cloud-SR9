package com.ribbon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * <p>
 * 配合@ComponentScan的excludeFilters，对当前注解所标注的类进行排除，示例如下：
 * <p>
 * - @ComponentScan(excludeFilters = {
 * -        // 被 @IgnoreScan 注解所标注的类不会被注册到Spring容器
 * -        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = IgnoreScan.class)
 * - })
 *
 * @author: WB
 * @version: v1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreScan {
}
