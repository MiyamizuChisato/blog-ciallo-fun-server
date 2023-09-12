package fun.ciallo.blog.utils;


import fun.ciallo.blog.common.ServerException;
import fun.ciallo.blog.common.Status;

public final class AssertUtils {
    public static void isTrue(boolean expression, ServerException exception) {
        if (!expression) {
            throw exception;
        }
    }

    public static void notTrue(boolean expression, ServerException exception) {
        if (expression) {
            throw exception;
        }
    }

    public static void isNull(Object o, ServerException exception) {
        if (o != null) {
            throw exception;
        }
    }

    public static void notNull(Object o, Status status) {
        if (o == null) {
            throw new ServerException(status);
        }
    }

    public static void isEquals(Object sono, Object kono, Status status) {
        if (!sono.equals(kono)) {
            throw new ServerException(status);
        }
    }

    public static void notEquals(Object sono, Object kono, ServerException exception) {
        if (sono.equals(kono)) {
            throw exception;
        }
    }
}