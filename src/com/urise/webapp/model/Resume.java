package com.urise.webapp.model;

/**
 * com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume>{
    private String uuid;// Unique identifier

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "uuid='" + uuid + '\'';
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.getUuid());
    }

}
