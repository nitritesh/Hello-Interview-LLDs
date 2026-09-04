package com.lld.logging;

/** Strategy for where a log line goes (console, file, network, ...). */
public interface LogAppender {
    void append(LogMessage message);
}
