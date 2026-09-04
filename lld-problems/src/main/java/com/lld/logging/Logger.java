package com.lld.logging;

import java.util.ArrayList;
import java.util.List;

/**
 * Thread-safe logger with a configurable minimum level and any number of
 * appenders. Messages below the threshold are dropped.
 */
public class Logger {
    private LogLevel threshold;
    private final List<LogAppender> appenders = new ArrayList<>();

    public Logger(LogLevel threshold) {
        this.threshold = threshold;
    }

    public void setThreshold(LogLevel threshold) {
        this.threshold = threshold;
    }

    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    public void log(LogLevel level, String message) {
        if (!level.isAtLeast(threshold)) {
            return;
        }
        LogMessage logMessage = new LogMessage(level, message);
        synchronized (appenders) {
            for (LogAppender appender : appenders) {
                appender.append(logMessage);
            }
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
