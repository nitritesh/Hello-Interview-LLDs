package com.lld.logging;

public class LoggingDemo {
    public static void main(String[] args) {
        Logger logger = new Logger(LogLevel.INFO);
        logger.addAppender(new ConsoleAppender());
        // Writes alongside the console; file lands in the working directory.
        logger.addAppender(new FileAppender("application.log"));

        logger.debug("This debug line is filtered out (threshold is INFO).");
        logger.info("Service started on port 8080.");
        logger.warn("Disk usage at 85%.");
        logger.error("Failed to connect to database.");

        System.out.println("\nLowering threshold to DEBUG...");
        logger.setThreshold(LogLevel.DEBUG);
        logger.debug("Now debug lines show up too.");

        System.out.println("\n(Check application.log in the working directory for file output.)");
    }
}
