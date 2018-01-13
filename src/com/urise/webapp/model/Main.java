package com.urise.webapp.model;

import java.util.Arrays;

public class Main {
    private final static ArrayStorage ARRAY_STORAGE = new ArrayStorage(100);

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            ARRAY_STORAGE.save(new Resume("Resume " + i));
        }
        System.out.println(Arrays.toString(ARRAY_STORAGE.getStorage()));
        System.out.println(ARRAY_STORAGE.getStorageLen());
        ARRAY_STORAGE.delete("Resume 1");
        ARRAY_STORAGE.delete("Resume 4");
        ARRAY_STORAGE.delete("Resume 5");
        ARRAY_STORAGE.delete("Resume 7");
        ARRAY_STORAGE.delete("Resume 10");
        ARRAY_STORAGE.delete("Resume 13");
        ARRAY_STORAGE.delete("Resume 14");
        ARRAY_STORAGE.delete("Resume 16");
        ARRAY_STORAGE.delete("Resume 18");
        ARRAY_STORAGE.delete("Resume 19");
        ARRAY_STORAGE.delete("Resume 11");
        ARRAY_STORAGE.delete("Resume 22");
        ARRAY_STORAGE.delete("Resume 23");
        ARRAY_STORAGE.delete("Resume 28");
        System.out.println(Arrays.toString(ARRAY_STORAGE.getStorage()));
        System.out.println(ARRAY_STORAGE.evaluateFragmentation());
    }
}
