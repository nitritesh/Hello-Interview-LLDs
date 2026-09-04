package com.lld.filesystem;

/** Composite pattern base: both File and Directory are nodes. */
public abstract class FileSystemNode {
    protected final String name;

    protected FileSystemNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Size in bytes: a file's own size, or the sum of a directory's contents. */
    public abstract long size();

    public abstract boolean isDirectory();
}
