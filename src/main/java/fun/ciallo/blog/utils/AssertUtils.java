package fun.ciallo.blog.utils;


import fun.ciallo.blog.common.ServerException;
import fun.ciallo.blog.common.Status;

public class AssertUtils {
    private static void exception(Status status) {
        throw new ServerException(status);
    }

    public static void isTrue(boolean expression, Status status) {
        if (!expression) {
            exception(status);
        }
    }

    public static void notTrue(boolean expression, Status status) {
        if (expression) {
            exception(status);
        }
    }

    public static void isNull(Object o, Status status) {
        if (o != null) {
            exception(status);
        }
    }

    public static void notNull(Object o, Status status) {
        if (o == null) {
            exception(status);
        }
    }

    public static void isEquals(Object sono, Object kono, Status status) {
        if (!sono.equals(kono)) {
            exception(status);
        }
    }

    public static void notEquals(Object sono, Object kono, Status status) {
        if (sono.equals(kono)) {
            exception(status);
        }
    }
}