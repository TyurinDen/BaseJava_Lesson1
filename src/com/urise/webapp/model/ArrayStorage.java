package com.urise.webapp.model;

import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(storage));
//    }

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume resume) {
        //просто сохранить или проверить есть ли такой элеммент в массиве
        //создать массив который содержит индексы ненулевых элементов в рабочем массиве
        //вместит ли отведенная память JVM массив из 10000 строк???
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
