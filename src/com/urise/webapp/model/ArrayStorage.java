package com.urise.webapp.model;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage;
    private int storageLen;

    ArrayStorage(int arraySize) {
        if (arraySize < 100) storage = new Resume[100]; //в массиве будет минимум 100 элементов
        else storage = new Resume[(arraySize / 100) * 100 + 100]; //количество элементов будет кратно 100
        storageLen = storage.length;
    }

    void clear() {
        Arrays.fill(storage, 0, size(), null);
    }

    int save(Resume resume) {
        int index = Arrays.binarySearch(storage, 0, size(), resume);
        if (index >= 0) return index; //если такой элемент уже есть в массиве - возвращаем его индекс
        index = size(); //добавляем элемент в конец массива
        if (index == storageLen) return -1; //массив заполнен
        storage[index] = resume;
        Arrays.sort(storage, 0, size());
        return index; //возвращаем индекс, по которому сохранен новый элемент
    }

    Resume get(String uuid) {
        Resume r = new Resume(uuid);
        int index = Arrays.binarySearch(storage, 0, size(), r);
        if (index < 0) return null; //элемента с указанным uuid нет в массиве
        return storage[index];
    }

    int delete(String uuid) {
        Resume r = get(uuid);
        int i, size;
        if (r == null) return -1; //массив не содержит удаляемый элемент
        int index = Arrays.binarySearch(storage, 0, size(), r);
        storage[index] = null;
        size = size();
        for (i = index; i < size; i++) storage[i] = storage[i + 1];
        storage[i] = null;
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

}
