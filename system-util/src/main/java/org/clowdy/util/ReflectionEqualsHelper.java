package org.clowdy.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class that provides helper methods for equals and hashCode methods for abstract classes using reflection.
 *
 * @author Dominic Cogan-Tucker
 */
public class ReflectionEqualsHelper {
    /**
     * Returns true if the two given objects are equal. Two objects are equal if they are of the same class and
     * the value of each respective field variable is equal.
     *
     * @param objectOne The first Object.
     * @param objectTwo The second Object.
     * @return true if the objects are equal, false if not.
     */
    public static boolean areEquals(Object objectOne, Object objectTwo) {
        if (objectOne.getClass().equals(objectTwo.getClass())) {
            Field[] fieldVariables = getFields(objectOne);
            Object objectOneFieldValue;
            Object objectTwoFieldValue;
            for (Field field : fieldVariables) {
                objectOneFieldValue = getFieldValue(objectOne, field);
                objectTwoFieldValue = getFieldValue(objectTwo, field);
                if (objectOneFieldValue instanceof Float
                        || objectOneFieldValue instanceof Double) {
                    objectOneFieldValue = (float) objectOneFieldValue + 0.0f;
                    objectTwoFieldValue = (float) objectTwoFieldValue + 0.0f;
                }
                if (!Objects.equals(objectOneFieldValue, objectTwoFieldValue)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a hashcode for this object. The hashcode is calculated in accordance to the equals method, such
     * that if two objects are equal they will have the same hashcode.
     *
     * @param object The Object to generate the hashcode for.
     * @return The hashcode for the given object.
     */
    public static int hashcode(Object object) {
        Field[] fieldVariables = getFields(object);

        final int prime = 31;
        int result = 17;

        Object objectFieldValue;
        for (Field field : fieldVariables) {
            objectFieldValue = getFieldValue(object, field);
            if (objectFieldValue instanceof Float
                    || objectFieldValue instanceof Double) {
                objectFieldValue = (float) objectFieldValue + 0.0f;
            }
            result = result * prime +
                    (objectFieldValue == null ? 0 : objectFieldValue.hashCode());
        }

        return result;
    }

    /**
     * Returns the Fields of the given object.
     *
     * @param object The Object to get the Fields of.
     * @return The Fields of the given object.
     */
    private static Field[] getFields(Object object) {
        return object.getClass().getDeclaredFields();
    }

    /**
     * Returns the value of the given field variable for the given Object.
     *
     * @param object The Object which contains the field.
     * @param field  The field to find the value of.
     * @return The value of the given field variable for the given Object.
     */
    private static Object getFieldValue(Object object, Field field) {
        field.setAccessible(true);
        try {
            Object value = field.get(object);
            field.setAccessible(false);
            return value;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
