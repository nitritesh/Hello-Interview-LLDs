package com.lld.filesystem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Directory extends FileSystemNode {
    private final Map<String, FileSystemNode> children = new LinkedHashMap<>();

    public Directory(String name) {
        super(name);
    }

    public void add(FileSystemNode node) {
        children.put(node.getName(), node);
    }

    public FileSystemNode get(String name) {
        return children.get(name);
    }

    public void remove(String name) {
        children.remove(name);
    }

    public List<FileSystemNode> list() {
        return new ArrayList<>(children.values());
    }

    @Override
    public long size() {
        long total = 0;
        for (FileSystemNode child : children.values()) {
            total += child.size();
        }
        return total;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    /** Recursively collects paths of nodes whose name contains the query. */
    public void search(String query, String prefix, List<String> results) {
        for (FileSystemNode child : children.values()) {
            String path = prefix + "/" + child.getName();
            if (child.getName().contains(query)) {
                results.add(path);
            }
            if (child.isDirectory()) {
                ((Directory) child).search(query, path, results);
            }
        }
    }
}
