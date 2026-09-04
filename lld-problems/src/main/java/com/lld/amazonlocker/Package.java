package com.lld.amazonlocker;

public class Package {
    private final String id;
    private final LockerSize size;

    public Package(String id, LockerSize size) {
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public LockerSize getSize() {
        return size;
    }
}
