package com.urise.webapp.model;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage;
    private int storageLen;
    private final int NUMBER_OF_GROUPS;
    private int numberElmsInGroup;
    private int freeCell;

    ArrayStorage(int arraySize) {
        if (arraySize < 100) storage = new Resume[100];
        else storage = new Resume[(arraySize / 100) * 100 + 100]; //количество элементов будет кратно 100
        storageLen = storage.length;
        NUMBER_OF_GROUPS = 10; //количество групп элементов в массиве, по которым будет оцениваться фрагментированность
        numberElmsInGroup = storageLen / NUMBER_OF_GROUPS;
        freeCell = 0;
    }

    void clear() {
        for (int i = 0; i < storageLen; i++) storage[i] = null;
    }

    int save(Resume resume) {
        int index = checkEntry(resume.getUuid());
        if (index >= 0) return index; //если такой элемент уже есть в массиве, возвращаем его индекс
        if (freeCell < 0) return -1; //массив заполнен, нет сводобных ячеек
        storage[freeCell] = new Resume(resume.getUuid()); //freeCell всегда указывает на сводобную ячейку массива
        index = freeCell;
        freeCell = findFreeCell(); //находим новую свободную ячейку от начала массива
        return index; //возвращаем индекс, по которому сохранен новый элемент
    }

    private int findFreeCell() {
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] == null) return i; //первый элемент, который содержит null - свободен, возвращаем его индекс
        }
        return -1;
    }

    Resume get(String uuid) {
        int index = checkEntry(uuid);
        if (index < 0) return null; //элемента с указанным uuid нет в массиве
        return storage[index];
    }

    void delete(String uuid) { //по идее можно вернуть признак выполнения операции
        int index = checkEntry(uuid);
        if (index < 0) return; //массив не содержит удаляемый элемент, удалять нечего
        storage[index] = null;
        freeCell = index; //freeCell теперь указывает на освободившуюся ячейку
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        int index = 0;
        for (Resume r : storage) {
            if (r != null) resumes[index++] = r;
        }
        return resumes;
    }

    Resume[] getStorage() {
        return storage;
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null) size++;
        }
        return size;
    }

    /**
     * Метод вычисляет фрагментацию массива, анализируя массив группами по numberElmsInGroup.
     *
     * @author - Denis Tyurin
     */
    float evaluateFragmentation() {
        int startIndex;
        int numberOfNonEmptyCell;
        float fragmentation;
        float totalFragmentation = 0;
        for (int i = 0; i < NUMBER_OF_GROUPS; i++) {
            startIndex = i * numberElmsInGroup;
            numberOfNonEmptyCell = 0;
            for (int j = startIndex; j < startIndex + numberElmsInGroup; j++) {
                if (storage[j] != null) numberOfNonEmptyCell++;
            }
            if (numberOfNonEmptyCell == 0) fragmentation = 0;
            else fragmentation = 1 - (float)numberOfNonEmptyCell / numberElmsInGroup;
            totalFragmentation += fragmentation;
        }
        return totalFragmentation / NUMBER_OF_GROUPS;
    }
    /**
     * Метод нормализует массив, перемещая все значимые элементы с конца в его начало.
     *
     * @author - Denis Tyurin
     */
    private void normalize() { //нормализует массив, то есть все элементы перемещаются в начало массива
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
        freeCell = findFreeCell();
    }

    private int checkEntry(String uuid) {
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null && storage[i].getUuid() == uuid) return i;
        }
        return -1;
    }

    int getStorageLen() {
        return storageLen;
    }
}