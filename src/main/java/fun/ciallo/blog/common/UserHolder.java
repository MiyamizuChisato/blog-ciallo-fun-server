package fun.ciallo.blog.common;


import fun.ciallo.blog.core.user.dto.UserDto;

public class UserHolder {
    private final static ThreadLocal<UserDto> userHolder = new ThreadLocal<>();

    public static void set(UserDto user) {
        userHolder.set(user);
    }

    public static UserDto get() {
        return userHolder.get();
    }

    public static void remove() {
        userHolder.remove();
    }
}
