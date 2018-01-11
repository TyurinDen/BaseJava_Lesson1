package com.urise.webapp.model;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    //private Resume[] storage = new Resume[10000];
    private static Resume[] storage = new Resume[100];//TODO удалить!! Инициализация в конструкторе
    static private int storageLen = storage.length;//TODO удалить!! Инициализация в конструкторе
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
//        System.out.println(checkEntry("resume 8"));
//        System.out.println(checkEntry("resume 888"));
        delete("resume 5");
        delete("resume 8");
        delete("resume 1");
//        delete("resume 15");
        delete("resume 19");
        delete("resume 0");
        delete("resume 10");
        delete("resume 2");
        delete("resume 4");
//        delete("resume 4");
        delete("resume 12");
        delete("resume 11");
        delete("resume 17");
        System.out.println(Arrays.toString(storage));
        System.out.println(size());
//        save(new Resume("R888"));
//        save(new Resume("R000"));
//        save(new Resume("R999"));
//        System.out.println(Arrays.toString(getAll()));
//        System.out.println(size());
        normalize();
        System.out.println(size());
        System.out.println(Arrays.toString(storage));
    }

    void clear() {
        for (int i = 0; i < storageLen; i++) storage[i] = null;
    }

    static int save(Resume resume) {
        int index = checkEntry(resume.getUuid());
        if (index >= 0) return index; //если такой элемент уже есть в массиве, возвращаем его индекс
        if (freeCell < 0) return -1; //массив заполнен, нет сводобных ячеек
        storage[freeCell] = new Resume(resume.getUuid()); //freeCell всегда указывает на сводобную ячейку массива
        index = freeCell;
        freeCell = findFreeCell(); //находим новую свободную ячейку
        return index; //возвращаем индекс, по которому сохранен новый элемент
    }

    private static int findFreeCell() {
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] == null) return i; //первый элемент, который содержит null - свободен, возвращаем его индекс
        }
        return -1;
    }

    static Resume get(String uuid) {
        int index = checkEntry(uuid);
        if (index < 0) return null; //элемента с указанным uuid нет в массиве
        return storage[index];
    }

    static void delete(String uuid) { //по идее можно вернуть признак выполнения операции
        int index = checkEntry(uuid);
        if (index < 0) return; //массив не содержит удаляемый элемент, удалять нечего
        freeCell = index; //freeCell теперь указывает на освободившуюся ячейку
        storage[index] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    static Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        int index = 0;
        for (Resume r : storage) {
            if (r != null) resumes[index++] = r;
        }
        return resumes;
    }

    static int size() {
        int size = 0;
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null) size++;
        }
        return size;
    }

    private static void normalize() { //нормализует массив, то есть все элементы перемещаются в начало массива
        int index = storageLen - 1;
        for (int i = 0; i < index; i++) {
            if (storage[i] == null) {
                for (int j = index; j > i; j--) {
                    if (storage[j] != null) {
                        storage[i] = storage[j];
                        storage[j] = null;
                        index = j - 1;
                        break;
                    }
                }
            }
        }
        System.out.println(index);
        freeCell = findFreeCell(); //TODO подумать как можно использовать index
    }

    private static int checkEntry(String uuid) {
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null && storage[i].getUuid() == uuid) return i;
        }
        return -1;
    }

}
