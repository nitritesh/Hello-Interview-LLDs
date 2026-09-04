package com.lld.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/** Appends log lines to a file on disk. */
public class FileAppender implements LogAppender {
    private final String filePath;

    public FileAppender(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void append(LogMessage message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(message.format());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }
}
