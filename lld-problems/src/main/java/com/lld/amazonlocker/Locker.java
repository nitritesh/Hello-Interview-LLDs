package com.lld.amazonlocker;

public class Locker {
    private final String id;
    private final LockerSize size;
    private Package currentPackage;

    public Locker(String id, LockerSize size) {
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public LockerSize getSize() {
        return size;
    }

    public boolean isFree() {
        return currentPackage == null;
    }

    public boolean canHold(Package pkg) {
        return isFree() && size.canHold(pkg.getSize());
    }

    public void assign(Package pkg) {
        if (!isFree()) {
            throw new IllegalStateException("Locker " + id + " is occupied");
        }
        this.currentPackage = pkg;
    }

    public Package release() {
        Package pkg = this.currentPackage;
        this.currentPackage = null;
        return pkg;
    }
}
