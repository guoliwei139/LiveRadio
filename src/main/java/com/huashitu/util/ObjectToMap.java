package com.huashitu.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * Created by levy on 2016/11/14.
 */
public class ObjectToMap {

    private static ObjectToMap objectToMap;

    public static ObjectToMap getinstance() {
        if (objectToMap == null) {
            objectToMap = new ObjectToMap();
        }
        return objectToMap;
    }


    public void objectToHashmap(Object object, Map hashMap) {
        //使用反射找出非空的值
        Class oClass = object.getClass();
        Field[] fields = oClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            f.setAccessible(true);
            try {
                if (f.get(object) != null) {
                    if (f.getName().contains("time") || f.getName().contains("Time")) {
                        hashMap.put(f.getName(),DateUtils.fotmatDate3((Date) f.get(object)));
                        continue;
                    }
                    hashMap.put(f.getName(), f.get(object));
                }
//                System.out.println("==============="+hashMap);
            } catch (Exception e) {
                System.out.println("=====【UserController】userToHashMap=====" + e.getMessage());
            }

        }

    }
}
