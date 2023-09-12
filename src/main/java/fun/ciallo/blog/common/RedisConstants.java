package fun.ciallo.blog.common;

import java.time.Duration;

public final class RedisConstants {
    public static final Duration TTL_SHORT = Duration.ofMinutes(2L);
    public static final Duration TTL_LONG = Duration.ofMinutes(20L);
    public static final Duration TTL_LONGER = Duration.ofHours(2L);
    public static final Duration TTL_LONGEST = Duration.ofHours(24L);
    public static final String BLANK = "";
    public static final String USER = "user:";
    public static final String ARCHIVE = "archive:";
    public static final String ARCHIVE_PAGE = "archive:page:";
    public static final String CATEGORIES = "categories";
    public static final String CATEGORY_MAP = "category:map";
}
