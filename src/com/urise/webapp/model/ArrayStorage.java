package com.urise.webapp.model;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    //private Resume[] storage = new Resume[10000];
    private static Resume[] storage;
    static private int storageLen;
    //static private int storageSize;
    static private int freeCell;
    //static private int[] freeCellsIndices = new int[storage.length / 10];

    public ArrayStorage() {
        storage = new Resume[100];
        storageLen = storage.length;
        freeCell = 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            storage[i] = new Resume("resume " + i);
        }
        System.out.println(Arrays.toString(storage));
        System.out.println(checkEntry("resume 8"));
        System.out.println(checkEntry("resume 888"));
    }

    void clear() {
        //возможно стоит таки реализовать метод нормолизации массива, тогда нам понадобится переменная=признак
        //того, что массив отнормирован
        //можно сохрянять резервную копию массива на всякий (а надо ли???)
        for (int i = 0; i < storageLen; i++) storage[i] = null;
        //Arrays.fill(storage, null);
    }

    static int save(Resume resume) {
        int index = checkEntry(resume.getUuid());
        if (index >= 0) return index;
        if (freeCell < 0) return -1;
        storage[freeCell] = new Resume(resume.getUuid());
        index = freeCell;
        freeCell = findFreeCell();
        return index;
        //вот здесь можно запустить этот мнханизм поиска первой свободной ячейки
        //только в том случае если сайз меньше длины массива, иначе просто возвращаем ошибку.
        //и метод нормализации не понадобится, массив будет заполняться более менне равномерно
        //хотя можно и заморочиться
        //можно и сортировку сделать самому, просто для практики
        //как то можно использовать сайз и размер массива. если сайз = длине, то массив заполнен полностью
        //создать массив который содержит индексы ненулевых элементов в рабочем массиве
        //по идее если делать ребейз, то в этом нет необходимости
    }

    private static int findFreeCell() {
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] == null) return i;
        }
        return -1;
    }

    Resume get(String uuid) {
        if (checkEntry(uuid) < 0) return null;
        return storage[checkEntry(uuid)];
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

    static int size() {
        int size = 0;
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null) size += 1;
        }
        return size;
    }

    private static int normalize() {
        //может возвращать количество освобожденных ячеек
        return 0;
        //помещает все элемента мыссива в начало
        //метод не так прост как кажется.
    }

    private static int checkEntry(String uuid) {
        for (int i = 0; i < storageLen; i++) {
            if (storage[i].getUuid() == uuid) return i;
        }
        return -1;
    }

    public static int getStorageSize() {
        return size();
    }

}
