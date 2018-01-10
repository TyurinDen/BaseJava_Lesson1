package com.urise.webapp.model;
/**
 * com.urise.webapp.model.Resume class
 */
public class Resume {
    private String uuid;// Unique identifier

    public Resume(String uuid) {
        this.uuid = uuid.intern();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid.intern();
    }

//    @Override
//    public String toString() {
//        return "Resume {" +
//                "uuid = '" + uuid + '\'' +
//                "}";
//    }

    @Override
    public String toString() {
        return "uuid='" + uuid + '\'';
    }
}
