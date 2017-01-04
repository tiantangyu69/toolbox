package me.flyness.toolbox.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjlizhitao on 2016/8/5.
 * 反射工具类
 */
public class ReflectionUtil {
    /**
     * 原生类型
     */
    private static final Map<String, Class<?>> PRIMITIVE_CLASSES = new HashMap<String, Class<?>>(8);

    static {
        PRIMITIVE_CLASSES.put("byte", Byte.TYPE);
        PRIMITIVE_CLASSES.put("short", Short.TYPE);
        PRIMITIVE_CLASSES.put("int", Integer.TYPE);
        PRIMITIVE_CLASSES.put("long", Long.TYPE);
        PRIMITIVE_CLASSES.put("float", Float.TYPE);
        PRIMITIVE_CLASSES.put("double", Double.TYPE);
        PRIMITIVE_CLASSES.put("boolean", Boolean.TYPE);
        PRIMITIVE_CLASSES.put("char", Character.TYPE);
    }

    /**
     * 将 String[] 参数转换为 Class[] 对象
     *
     * @param parameterTypes
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?>[] getParameterTypes(String[] parameterTypes) throws ClassNotFoundException {
        if (null != parameterTypes && parameterTypes.length > 0) {
            Class<?>[] paraTypes = new Class[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                if (isPrimitiveClass(parameterTypes[i])) // 是java原生类型
                    paraTypes[i] = getPrimitiveClass(parameterTypes[i]);
                else
                    paraTypes[i] = Class.forName(parameterTypes[i]);
            }
            return paraTypes;
        }
        return null;
    }

    /**
     * 是否为javabean规范的setter、getter方法
     *
     * @param method
     * @param beanClass
     * @return
     */
    public static boolean isProperty(Method method, Class<?> beanClass) {
        String methodName = method.getName();
        // 是否为javabean规范的setter方法
        boolean flag = methodName.length() > 3 && methodName.startsWith("set") && Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1;
        Method getter = null;
        if (flag) {
            Class<?> type = method.getParameterTypes()[0];
            try {
                getter = beanClass.getMethod("get" + methodName.substring(3), new Class[0]);
            } catch (NoSuchMethodException e) {
                try {
                    getter = beanClass.getMethod("is" + methodName.substring(3), new Class[0]);
                } catch (NoSuchMethodException e1) {
                }
            }

            flag = getter != null && Modifier.isPublic(getter.getModifiers()) && type.equals(getter.getReturnType());
        }

        return flag;
    }

    /**
     * 是否为原生对象类型
     *
     * @param classname
     * @return
     */
    public static boolean isPrimitiveClass(String classname) {
        if (PRIMITIVE_CLASSES.containsKey(classname)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取原生对象类型
     *
     * @param type
     * @return
     */
    public static Class<?> getPrimitiveClass(String type) {
        return PRIMITIVE_CLASSES.get(type);
    }
}
