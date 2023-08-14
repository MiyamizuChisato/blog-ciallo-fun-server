package fun.ciallo.blog.utils;


import fun.ciallo.blog.common.response.BlogServerException;

public final class AssertUtils {
    public static void isTrue(boolean expression, BlogServerException exception) {
        if (!expression) {
            throw exception;
        }
    }

    public static void notTrue(boolean expression, BlogServerException exception) {
        if (expression) {
            throw exception;
        }
    }

    public static void isNull(Object o, BlogServerException exception) {
        if (o != null) {
            throw exception;
        }
    }

    public static void notNull(Object o, BlogServerException exception) {
        if (o == null) {
            throw exception;
        }
    }

    public static void isEquals(Object sono, Object kono, BlogServerException exception) {
        if (!sono.equals(kono)) {
            throw exception;
        }
    }

    public static void notEquals(Object sono, Object kono, BlogServerException exception) {
        if (sono.equals(kono)) {
            throw exception;
        }
    }
}