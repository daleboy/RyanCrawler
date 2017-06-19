package cn.ryan.xpath.util;

import com.google.common.base.Preconditions;

import cn.ryan.xpath.exception.ObjectCreateException;


/**
 * Created by virjar on 17/6/13.
 */
public class ObjectFactory {

    public static <T> T newInstance(String className) {
        Preconditions.checkNotNull(className);
        try {
            Class<?> aClass = Class.forName(className);
            return (T) aClass.newInstance();
        } catch (Exception e) {
            throw new ObjectCreateException("can not create instance for class " + className, e);
        }
    }

    public static <T> T newInstance(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw new ObjectCreateException("can not create instance for class " + tClass, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> classForName(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (Exception e) {
            throw new ObjectCreateException("can not create instance for class " + className, e);
        }
    }
}