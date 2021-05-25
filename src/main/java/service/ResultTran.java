package service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResultTran {
    public static List tran(ResultSet rs,Class clz){
        List list=new ArrayList<>();
        Field[] fields = clz.getDeclaredFields();
        try {
            Constructor constructor = clz.getDeclaredConstructor();
            while (rs.next()) {
                Object instance = constructor.newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    field.set(instance, rs.getObject(fieldName));
                }
                list.add(instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static Object tranOne(ResultSet rs, Class clz){
        Field[] fields = clz.getDeclaredFields();
        try {
            Constructor constructor = clz.getDeclaredConstructor();
            Object instance = constructor.newInstance();
            while (rs.next()) {
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    field.set(instance, rs.getObject(fieldName));
                }
            }
            return instance;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
