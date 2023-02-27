package com.fasid.enums;

import java.util.HashMap;
import java.util.Map;

public enum TimeUnit {

    SECONDS("Seconds", 1L),
    MINUTES("Minutes", 60L),
    HOURS("Hours", 3600L),
    DAYS("Days", 86400L);

    private String displayName;
    private long timeInSeconds;

    private static Map<String, TimeUnit> DISPLAY_NAME_TO_TIME = new HashMap<>();

    TimeUnit(final String displayName, final long timeInSeconds) {
        this.displayName = displayName;
        this.timeInSeconds = timeInSeconds;
    }

    static {
        for (TimeUnit timeUnit : TimeUnit.values()) {
            DISPLAY_NAME_TO_TIME.put(timeUnit.displayName, timeUnit);
        }
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public long getTimeInSeconds() {
        return this.timeInSeconds;
    }

    public long getTimeInMilliSeconds() {
        return (this.timeInSeconds * 1000);
    }

    public static TimeUnit getTimeUnitFromDisplayName(final String displayName) {
        return DISPLAY_NAME_TO_TIME.get(displayName);
    }
}
