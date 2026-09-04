package com.lld.logging;

/** Ordered by severity; a logger emits messages at or above its threshold. */
public enum LogLevel {
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4);

    private final int severity;

    LogLevel(int severity) {
        this.severity = severity;
    }

    public boolean isAtLeast(LogLevel threshold) {
        return this.severity >= threshold.severity;
    }
}
