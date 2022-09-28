package com.example.demo.aop.impl;

import com.example.demo.aop.Desensitized;
import com.example.demo.utils.DesensitizedUtil;
import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @className:DesensitizedAop
 * @author:hhs
 * @date:2021-07-30 10:04
 */
@Component
@Aspect
public class DesensitizedAop {


    @Around(value = "@annotation(desensitized)")
    public Object paramCheck(ProceedingJoinPoint joinPoint, Desensitized desensitized) throws Throwable {
        // 获取返回值
        Object proceed = joinPoint.proceed();
        Object execute = execute(proceed);
        System.out.println(JSONObject.fromObject(execute));
        return execute;
    }

    /**
     * 判断是否为基本类型：包括String
     *
     * @param clazz clazz
     * @return true：是; false：不是
     */
    private boolean isPrimite(Class<?> clazz) {
        return clazz.isPrimitive() || clazz == String.class;
    }

    private Object execute(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj instanceof Field ? ((Field) obj).getType() : obj.getClass();
        if (isPrimite(clazz)) {
            return obj;
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (isPrimite(declaredField.getType())) {
                Desensitized annotation = declaredField.getAnnotation(Desensitized.class);
                if (annotation != null) {
                    System.out.println(declaredField.getType());
                    declaredField.set(obj, desensitization(declaredField.get(obj), annotation));
                }
            } else {
                if (declaredField.get(obj) != null) {
                    declaredField.set(obj, execute(declaredField.get(obj)));
                }
            }
        }

        return obj;
    }

    private Object desensitization(Object obj, Desensitized annotation) {
        //利用反射，调用自定义注解中的参数方法
        MethodType methodType = MethodType.methodType(String.class, String.class);
        // 获取静态方法的句柄
        MethodHandle method = null;
        try {
            method = MethodHandles.lookup()
                    .findStatic(DesensitizedUtil.class, annotation.method(), methodType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return obj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return obj;
        }

        Object r;
        try {
            r = method.invoke((String) obj); // invoke|invoke
        } catch (Throwable e) {
            e.printStackTrace();
            return obj;
        }

        return r;
    }

}
