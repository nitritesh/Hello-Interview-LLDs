package com.lld.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogMessage {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LogLevel level;
    private final String message;
    private final LocalDateTime timestamp;

    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public LogLevel getLevel() {
        return level;
    }

    public String format() {
        return "[" + timestamp.format(FMT) + "] " + level + " - " + message;
    }
}
