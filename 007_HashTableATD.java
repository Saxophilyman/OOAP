import java.util.LinkedList;

public abstract class HashTableATD<T> {
    // конструктор
    // постусловие: создана пустая хэш-таблица
    public HashTableATD() {
    }

    //команды
    // предусловие: в таблице имеется свободное место;
    //постусловие: элемент value вставлен в хэш-таблицу
    public abstract void put(T value);

    // предусловие: в таблице имеется значение value;
    // постусловие: из таблицы удалено значение value
    public abstract void delete(T value);


    //запросы

    // предусловие: в таблице имеется значение value;
    // постусловие: в таблице найдено значение value
    public abstract boolean find(T value);

    public abstract int size();


    // запросы статусов
    public abstract int get_put_status(); // успешно; нет свободного места
    public abstract int get_delete_status(); // успешно; в таблице нет значений
    public abstract int get_find_status(); // успешно; в таблице нет значений
}

public class HashTable<T> extends HashTableATD<T>{
    private int size = 0;
    private int capacity = 8;
    private LinkedList<T>[] slots;
    private int freeSlots = capacity;
    private final int SUB_CAPACITY = 8;
    private final int BASE_CAPACITY = 8;

    private final int PUT_STATUS_NIL = 0; // команда put ещё не вызывалась
    private final int PUT_STATUS_OK = 1; // вставка элемента прошла успешно
    private final int PUT_STATUS_ERR = 2; // в таблице нет свободного места
    private final int DELETE_STATUS_NIL = 0; // команда delete ещё не вызывалась
    private final int DELETE_STATUS_OK = 1; // удаление элемента прошло успешно
    private final int DELETE_STATUS_ERR = 2; // хэш-таблица пуста
    private final int FIND_STATUS_NIL = 0; // команда find ещё не вызывалась
    private final int FIND_STATUS_OK = 1; // получение значение элемента прошло успешно
    private final int FIND_STATUS_ERR = 2; // искомый элемент отсутствует в таблице
    private int put_status;
    private int delete_status;
    private int find_status;

    public HashTable(){
        slots = initial();
        put_status = PUT_STATUS_NIL;
        delete_status = DELETE_STATUS_NIL;
        find_status = FIND_STATUS_NIL;
    }

    @Override
    public void put(T value) {
        int index = index(value);
        if (slots[index] == null) {
            slots[index] = new LinkedList<>();
            freeSlots--;
        }
        boolean contains = slots[index].contains(value);
        if (contains) {
            put_status = PUT_STATUS_OK;
            return;
        }
        if (slots[index].size() == BASE_CAPACITY)
            put_status = PUT_STATUS_ERR;
        else {
            slots[index].add(value);
            put_status = PUT_STATUS_OK;
        }
        if ((float) freeSlots / capacity <= 0.3) {
            reallocation();
        }
    }

    @Override
    public void delete(T value) {
        int index = index(value);
        if (slots[index] == null) {
            delete_status = DELETE_STATUS_ERR;
            return;
        }
        boolean result = slots[index].contains(value);
        if (result) {
            slots[index].remove(value);
            delete_status = DELETE_STATUS_OK;
        } else
            delete_status = DELETE_STATUS_ERR;
        if (slots[index].isEmpty())
            freeSlots++;
    }

    @Override
    public boolean find(T value) {
        int index = index(value);
        if (slots[index] == null) {
            find_status = FIND_STATUS_ERR;
            return false;
        }
        find_status = FIND_STATUS_OK;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get_put_status() {
        return put_status;
    }

    @Override
    public int get_delete_status() {
        return delete_status;
    }

    @Override
    public int get_find_status() {
        return find_status;
    }

    private LinkedList<T>[] initial() {
        return new LinkedList[capacity];
    }

    private int index(T value) {
        int asNumber = value.toString().chars().sum();
        return asNumber % capacity;
    }

    private void reallocation() {
        capacity *= 2;
        LinkedList<T>[] newSlots = initial();

        for (LinkedList<T> ts : slots) {
            if (ts == null)
                continue;
            for (T slot : ts) {
                int index = index(slot);
                if (newSlots[index] == null)
                    newSlots[index] = new LinkedList<>();
                newSlots[index].add(slot);
            }
        }
        slots = newSlots;
    }
}

