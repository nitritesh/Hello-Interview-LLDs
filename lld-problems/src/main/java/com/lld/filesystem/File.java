package com.lld.filesystem;

public class File extends FileSystemNode {
    private String content;

    public File(String name) {
        super(name);
        this.content = "";
    }

    public File(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void write(String content) {
        this.content = content;
    }

    public void append(String extra) {
        this.content = this.content + extra;
    }

    @Override
    public long size() {
        return content.getBytes().length;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
