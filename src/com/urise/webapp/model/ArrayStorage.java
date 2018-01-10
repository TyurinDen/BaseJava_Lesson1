package com.urise.webapp.model;

import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    //private Resume[] storage = new Resume[10000];
    private static Resume[] storage = new Resume[100];
    static private int storageLen;
    static private int storageSize;
    static private int lastFreeCell;
    //static private int[] freeCellsIndices = new int[storage.length / 10];

    public ArrayStorage() {
        storageLen = storage.length;
        storageSize = 0;
        lastFreeCell = 0;
//        for (int i = 0; i < freeCellsIndices.length; i++) {
//            freeCellsIndices[i] = i;
//        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            storage[i] = new Resume("resume " + i);
        }
        System.out.println(Arrays.toString(storage));
        System.out.println(storageSize);
        System.out.println(checkEntry("resume 8"));
        System.out.println(checkEntry("resume 888"));
    }

    void clear() {
        //неоптимально, надо через size() сделать
        //можно сохрянять резервную копию массива на всякий (а надо ли???)
        Arrays.fill(storage, null);
    }

    static int save(Resume resume) {
        int index = checkEntry(resume.getUuid());
        if (index >= 0) return index;
        if (lastFreeCell > 0 | lastFreeCell < storageLen) {
            storage[lastFreeCell] = new Resume(resume.getUuid());
            index = lastFreeCell;
            lastFreeCell += 1;
            setStorageSize(storageSize + 1);
            return index;
            //или без индекса return lastFreeCell - 1;
        } else {
            //вот здесь можно запустить этот мнханизм поиска первой свободной ячейки
            //только в том случае если сайз меньше длины массива, иначе просто возвращаем ошибку.
            //и метод нормализации не понадобится, массив будет заполняться более менне равномерно
            //хотя можно и заморочиться
            //можно и сортировку сделать самому, просто для практики
            if (normalize() > 0) {
                //как то можно использовать сайз и размер массива. если сайз = длине, то массив заполнен полностью
                lastFreeCell
            }
        }
        index = lastFreeCell;
        lastFreeCell += 1;
        if (lastFreeCell < storageLen) return;
        return checkEntry(resume.getUuid());
        //или сделать метод, который ищет первый свободный элемент, и присваивает его индекс ластФриСелу
        //
        //можно хранить индекс первой свободной ячейки
        //создать массив который содержит индексы ненулевых элементов в рабочем массиве
        //по идее если делать ребейз, то в этом нет необходимости
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

    static void size() {
        int size = 0;
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null) size += 1;
        }
        storageSize = size;
    }

    private static int normalize() {
        //может возвращать количество освобожденных ячеек
        return 0;
        //помещает все элемента мыссива в начало
        //метод не так прост как кажется.
    }

    private static int checkEntry(String uuid) {
        size();
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid() == uuid) return i;
        }
        return -1;
    }

    public static int getStorageSize() {
        return storageSize;
    }

    private static void setStorageSize(int storageSize) {
        ArrayStorage.storageSize = storageSize;
    }
}
