package com.urise.webapp.model;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage;
    private int storageLen;

    ArrayStorage(int arraySize) {
        if (arraySize < 100) storage = new Resume[100]; //в массиве будет минимум 100 элементов
        else storage = new Resume[(arraySize / 100) * 100 + 100]; //количество элементов будет кратно 100
//        storage = new Resume[] {new Resume("RES001"), new Resume("RES002"), new Resume("RES003")};
        storageLen = storage.length;
    }

    void clear() {
        Arrays.fill(storage, 0, size() - 1, null);
    }

    int save(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, size(), resume);
        if (index >= 0) return index; //если такой элемент уже есть в массиве - возвращаем его индекс
        index = size();
        if (index == storageLen) return -1; //массив заполнен
        storage[index] = resume;
        return index; //возвращаем индекс, по которому сохранен новый элемент
    }

    Resume get(String uuid) {
        Resume r = new Resume(uuid);
        int index = Arrays.binarySearch(storage, 0, size(), r);
        if (index < 0) return null; //элемента с указанным uuid нет в массиве
        return r;
    }

    int delete(String uuid) {
        int index = Arrays.binarySearch(storage, 0, size(), new Resume(uuid));
        if (index < 0) return -1; //массив не содержит удаляемый элемент, удалять нечего
        storage[index] = null;
        normalize(); //TODO можно ли сделать как-нибудь по-другому???
        return index;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storageLen; i++) {
            if (storage[i] != null) size++;
        }
        return size;
    }

    //TODO переделать, не работает!! не нужно бегать по всему массиву!
    /**
     * Метод нормализует массив, перемещая все значимые элементы в его начало начиная с конца.
     * Метод вызывается вручную, однако оценит фрагментацию самостоятельно, и если она выше 0.5 -
     * выполнит нормализацию.
     *
     * @return - признак выполнения нормализации
     * @author - Denis Tyurin
     */
    void normalize() {
        int index = size() - 1;
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
    }

}
