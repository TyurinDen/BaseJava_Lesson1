package com.urise.webapp.model;

import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    //private Resume[] storage = new Resume[10000];
    private static Resume[] storage = new Resume[100];
    static int storageLen, storageSize;

    public ArrayStorage() {
        storageLen = storage.length;
        storageSize = size();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            storage[i] = new Resume("resume " + i);
        }
        System.out.println(Arrays.toString(storage));
        System.out.println(size());
        System.out.println(checkEntry("resume 8"));
        System.out.println(checkEntry("resume 888"));
    }

    void clear() {
        //неоптимально, надо через size() сделать
        //можно сохрянять резервную копию массива на всякий (а надо ли???)
        Arrays.fill(storage, null);
    }

    static int save(Resume resume) {
        if (checkEntry(resume.getUuid()) < 0) {
            storage[size() + 1] = new Resume(resume.getUuid());
        }
        return checkEntry(resume.getUuid());
        //можно хранить индекс первой свободной ячейки
        //создать массив который содержит индексы ненулевых элементов в рабочем массиве
        //по идее если делать ребейз, то в этом нет необходимости
    }

    Resume get(String uuid) {
        //int arrayLen = size();
        for (int i = 0; i < storageSize; i++) {

        }
        return null;
    }

    void delete(String uuid) {
        //если удаляется элемент в конце, то ребейз делать не надо
        //и если удаляется элемент в начале (скажем в первой половине), то тоже ребейз не делаем.
        //или можно хранить количество окон и если их становится больше какого-то числа, то сделать ребейз
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    //можно этому методу передавать массив, но тогда нужно перегружать метод, так как работать
    //с примитивами надо одним способом, а с объектами другим (?????)
    static int size() {
        int arraySize = 0;
        //int arrayLen = storage.length;
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null) {
                arraySize += 1;
            }
        }
        return arraySize;
    }

    private void normalize() {
        //помещает все элемента мыссива в начало
        //метод не так прост как кажется.
    }

    private static int checkEntry(String uuid) {
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid() == uuid) return i;
        }
        return -1;
    }
}
