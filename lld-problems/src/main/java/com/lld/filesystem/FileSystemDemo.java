package com.lld.filesystem;

public class FileSystemDemo {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        fs.addFile("/home/alice", "notes.txt", "hello world");
        fs.addFile("/home/alice", "todo.txt", "buy milk");
        fs.addFile("/home/bob/docs", "resume.pdf", "PDF-CONTENT-BYTES");
        fs.mkdirs("/var/log");

        System.out.println("Contents of /home:");
        for (String name : fs.ls("/home")) {
            System.out.println("  " + name);
        }

        System.out.println("\nContents of /home/alice:");
        for (String name : fs.ls("/home/alice")) {
            System.out.println("  " + name);
        }

        System.out.println("\nSize of /home/alice: " + fs.sizeOf("/home/alice") + " bytes");

        System.out.println("\nSearch for '.txt':");
        for (String path : fs.find(".txt")) {
            System.out.println("  " + path);
        }
    }
}
