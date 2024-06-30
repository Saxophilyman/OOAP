import java.lang.reflect.Array;
import java.util.Optional;

public abstract class MapATD<T> {

    // конструктор
    // постусловие: создана пустая карта
public  MapATD(){};

    //команды
    // постусловие: гарантированно записываем значение value по ключу key
    // (создаётся новое ключ-значение или перезаписывается старое по ключу)
    public abstract void put(String key, T value);

    // предусловие: ключ key присутствует в массиве
    // постусловие: ключ удаляется вместе со значением
    public abstract void remove(String key);


    //запросы

    // предусловие: в таблице имеется значение key;
    // постусловие: возвращает true если ключ имеется иначе false
    public abstract boolean isKey(String key);


    // предусловие: в таблице имеется значение value;
    // постусловие: возвращает value для key, вариант обхода null - Optional
    public abstract Optional<T> get(String key);

    public  abstract int size();

    // запросы статусов
    public abstract int get_put_status(); // успешно; ключ перезаписан
    public abstract int get_remove_status();// успешно; ключ отсутствует
    public abstract int get_get_status();// успешно; ключ отсутствует
}

class NativeDictionary<T> extends MapATD<T>{
    private int size = 0;
    private int lenght;
    private String[] keys;
    private T[] values;
    private Class<T> clazz;

    private final int PUT_STATUS_NIL = 0; // команда put ещё не вызывалась
    private final int PUT_STATUS_OK = 1; // вставка элемента прошла успешно
    private final int PUT_STATUS_ERR = 2; // ключ перезаписан
    private final int REMOVE_STATUS_NIL = 0; // команда remove ещё не вызывалась
    private final int REMOVE_STATUS_OK = 1; // удаление прошло успешно
    private final int REMOVE_STATUS_ERR = 2; // ключ отсутствует
    private final int GET_STATUS_NIL = 0; // команда get ещё не вызывалась
    private final int GET_STATUS_OK = 1; // получение значение элемента прошло успешно
    private final int GET_STATUS_ERR = 2; // ключ отсутствует

    private int put_status;
    private int remove_status;
    private int get_status;

    public NativeDictionary(int lenght){
        this.lenght = lenght;
        keys = new String[lenght];
        values = (T[]) Array.newInstance(clazz, lenght);
        put_status = PUT_STATUS_NIL;
        remove_status = REMOVE_STATUS_NIL;
        get_status = GET_STATUS_NIL;
    }
    @Override
    public void put(String key, T value) {
        int index = index(key);
        if (!keys[index].equals(key)) {
            put_status = PUT_STATUS_ERR;
        } else {
            put_status = PUT_STATUS_OK;
            values[index] = value;
            size++;
        }
    }

    @Override
    public void remove(String key) {
        int index = index(key);
        if (!keys[index].equals(key)) {
            remove_status = REMOVE_STATUS_ERR;
        } else {
            remove_status = REMOVE_STATUS_OK;
            keys[index] = null;
            values[index] = null;
            size--;
        }
    }

    @Override
    public boolean isKey(String key) {
        int index = index(key);
        return keys[index].equals(key);
    }

    @Override
    public Optional<T> get(String key) {
        int index = index(key);
        if (!keys[index].equals(key)) {
            get_status = GET_STATUS_ERR;
            return Optional.empty();
        }
        get_status = GET_STATUS_OK;
        return Optional.of(values[index]);
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
    public int get_remove_status() {
        return remove_status;
    }

    @Override
    public int get_get_status() {
        return get_status;
    }

    private int index(String key) {
        int asNumber = key.chars().sum();
        return asNumber % lenght;
    }
}
