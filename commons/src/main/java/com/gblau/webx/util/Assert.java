package com.gblau.webx.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author gblau
 * @date 2017-05-26
 */
public abstract class Assert {
    public Assert() {
    }

    public static void state(boolean expression, String message) {
        if(!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if(isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void noNullElements(Object[] array, String message) {
        if(array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if(element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }

    }

    public static void notEmpty(Collection<?> collection, String message) {
        if(isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if(isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ")?"":": ") + typeName;
    }

    private static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    private static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
