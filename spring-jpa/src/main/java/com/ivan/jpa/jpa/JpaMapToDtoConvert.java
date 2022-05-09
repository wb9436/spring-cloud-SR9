package com.ivan.jpa.jpa;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 将查询结果集map对象转换成dto对象
 *
 * @author WuBing
 * @date 2022-05-08 22:19
 */
@Slf4j
public class JpaMapToDtoConvert implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        log.info("JpaMapToDtoConvert --> getConvertibleTypes 执行了");
        return Collections.singleton(new ConvertiblePair(Map.class, JpaQueryDto.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        log.info("JpaMapToDtoConvert --> convert 执行了");
        if (source == null) {
            return null;
        }
        Class<?> tClass = targetType.getObjectType();
        Object target;
        try {
            target = tClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return target;
    }
}
