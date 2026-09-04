package com.lld.filesystem;

import java.util.ArrayList;
import java.util.List;

/** A facade over the composite tree that resolves absolute paths like /a/b/c. */
public class FileSystem {
    private final Directory root = new Directory("");

    public Directory getRoot() {
        return root;
    }

    /** Creates all missing directories along the path and returns the last one. */
    public Directory mkdirs(String path) {
        Directory current = root;
        for (String part : split(path)) {
            FileSystemNode next = current.get(part);
            if (next == null) {
                Directory dir = new Directory(part);
                current.add(dir);
                current = dir;
            } else if (next.isDirectory()) {
                current = (Directory) next;
            } else {
                throw new IllegalArgumentException(part + " is a file, not a directory");
            }
        }
        return current;
    }

    /** Adds a file at the given directory path. */
    public File addFile(String dirPath, String fileName, String content) {
        Directory dir = mkdirs(dirPath);
        File file = new File(fileName, content);
        dir.add(file);
        return file;
    }

    /** Lists names directly under a directory path. */
    public List<String> ls(String path) {
        Directory dir = resolveDir(path);
        List<String> names = new ArrayList<>();
        for (FileSystemNode node : dir.list()) {
            names.add(node.getName() + (node.isDirectory() ? "/" : ""));
        }
        return names;
    }

    /** Finds all paths whose node name contains the query. */
    public List<String> find(String query) {
        List<String> results = new ArrayList<>();
        root.search(query, "", results);
        return results;
    }

    public long sizeOf(String path) {
        return resolveDir(path).size();
    }

    private Directory resolveDir(String path) {
        Directory current = root;
        for (String part : split(path)) {
            FileSystemNode next = current.get(part);
            if (next == null || !next.isDirectory()) {
                throw new IllegalArgumentException("No such directory: " + path);
            }
            current = (Directory) next;
        }
        return current;
    }

    private String[] split(String path) {
        String trimmed = path.replaceAll("^/+", "").replaceAll("/+$", "");
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        return trimmed.split("/+");
    }
}
