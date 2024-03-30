package exercise;

import java.lang.reflect.Field;
// BEGIN
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.annotation.Annotation;

public class Validator {
    public static List<String> validate(Object obj) {
        List<String> invalidFields = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value == null) {
                        invalidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return invalidFields;
    }
	
    public static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> invalidFields = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof NotNull) {
                    try {
                        field.setAccessible(true);
                        if (field.get(obj) == null) {
                            invalidFields.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add("can not be null");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (annotation instanceof MinLength) {
                    try {
                        field.setAccessible(true);
                        String value = (String) field.get(obj);
                        int minLength = ((MinLength) annotation).minLength();
                        if (value != null && value.length() < minLength) {
                            invalidFields.computeIfAbsent(field.getName(), k -> new ArrayList<>()).add("length less than " + minLength);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return invalidFields;
    }
}
// END
