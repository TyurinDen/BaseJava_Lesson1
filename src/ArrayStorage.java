import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static Resume[] storage = new Resume[10000];
    private static Resume[] nullEl = {new Resume()};

    public static void main(String[] args) {
        //nullEl[1] = new Resume();
        System.out.println(Arrays.toString(nullEl));
        System.out.println(Arrays.toString(storage));
    }

    void clear() {
    }

    void save(Resume r) {
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
