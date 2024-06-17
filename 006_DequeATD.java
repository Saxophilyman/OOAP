import java.util.ArrayList;
import java.util.List;

//стандартная реализация АТД без связки с Queue
public abstract class DequeATD<T> {
    // конструктор
    // постусловие: создана пустая очередь
    public DequeATD() {
    }

    //команды

    //постусловие: элемент item вставлен в голову очереди
    public abstract void addFront(T item);

    //постусловие: элемент item вставлен в хвост очереди
    public abstract void addTail(T item);

    //предусловие: очередь не пуста;
    //постусловие: удалён элемент из головы
    public abstract void removeFront();

    //предусловие: очередь не пуста;
    //постусловие: удалён элемент из хвоста
    public abstract void removeTail();

    //запросы
    //предусловие: очередь не пуста
    public abstract T getFront(); //получить элемент из головы очереди

    public abstract T getTail(); //получить элемент из хвоста очереди

    public abstract int size();

    // запросы статусов
    abstract public int get_removeFront_status(); // успешно; очередь пуста

    abstract public int get_removeTail_status(); // успешно; очередь пуста

    abstract public int get_getFront_status(); // успешно; очередь пуста

    abstract public int get_getTail_status(); // успешно; очередь пуста
}


class DequeClass<T> extends DequeATD<T> {
    private List<T> deque;
    private int size;
    private final int REMOVE_FRONT_STATUS_NIL = 0; // удаление элемента ещё не вызывалось
    private final int REMOVE_FRONT_STATUS_OK = 1; // удаление прошло успешно
    private final int REMOVE_FRONT_STATUS_ERR = 2; // очередь пустая
    private final int REMOVE_TAIL_STATUS_NIL = 0; // удаление элемента ещё не вызывалось
    private final int REMOVE_TAIL_STATUS_OK = 1; // удаление прошло успешно
    private final int REMOVE_TAIL_STATUS_ERR = 2; // очередь пустая
    private final int GET_FRONT_STATUS_NIL = 0; // удаление элемента ещё не вызывалось
    private final int GET_FRONT_STATUS_OK = 1; // удаление прошло успешно
    private final int GET_FRONT_STATUS_ERR = 2; // очередь пустая
    private final int GET_TAIL_STATUS_NIL = 0; // удаление элемента ещё не вызывалось
    private final int GET_TAIL_STATUS_OK = 1; // удаление прошло успешно
    private final int GET_TAIL_STATUS_ERR = 2; // очередь пустая

    private int removeFront_status;
    private int removeTail_status;
    private int getFront_status;
    private int getTail_status;

    public DequeClass() {
        super();
        deque = new ArrayList<>();
        size = 0;
        removeFront_status = REMOVE_FRONT_STATUS_NIL;
        removeTail_status = REMOVE_TAIL_STATUS_NIL;
        getFront_status = GET_FRONT_STATUS_NIL;
        getTail_status = GET_TAIL_STATUS_NIL;
    }

    @Override
    public void addFront(T item) {
        deque.addFirst(item);
        size++;
    }

    @Override
    public void addTail(T item) {
        deque.addLast(item);
        size++;
    }

    @Override
    public void removeFront() {
        if (deque.isEmpty()) {
            removeFront_status = REMOVE_FRONT_STATUS_ERR;
            return;
        }
        removeFront_status = REMOVE_FRONT_STATUS_OK;
        deque.removeFirst();
        size--;
    }

    @Override
    public void removeTail() {
        if (deque.isEmpty()) {
            removeTail_status = REMOVE_TAIL_STATUS_ERR;
            return;
        }
        removeTail_status = REMOVE_TAIL_STATUS_OK;
        deque.removeLast();
        size--;
    }

    @Override
    public T getFront() {
        if (deque.isEmpty()) {
            getFront_status = GET_FRONT_STATUS_ERR;
            return null;
        }
        getFront_status = GET_FRONT_STATUS_OK;
        return deque.getFirst();
    }

    @Override
    public T getTail() {
        if (deque.isEmpty()) {
            getTail_status = GET_TAIL_STATUS_ERR;
            return null;
        }
        getTail_status = GET_TAIL_STATUS_OK;
        return deque.getLast();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get_removeFront_status() {
        return removeFront_status;
    }

    @Override
    public int get_removeTail_status() {
        return removeTail_status;
    }

    @Override
    public int get_getFront_status() {
        return getFront_status;
    }

    @Override
    public int get_getTail_status() {
        return getTail_status;
    }
}


//Совмещение АТД и реализации Queue и Deque в одну иерархию

public abstract class ParentQueATD<T> {

    // конструктор
    // постусловие: создана пустая очередь
    public ParentQueATD() {
    }

    //команды

    //постусловие: элемент item вставлен в хвост очереди
    public abstract void addTail(T item);

    //предусловие: очередь не пуста;
    //постусловие: удалён элемент из головы
    public abstract void removeFront();


    //запросы
    //предусловие: очередь не пуста
    public abstract T getFront(); //получить элемент из головы очереди

    public abstract int size();


    // запросы статусов
    abstract public int get_removeFront_status(); // успешно; очередь пуста

    abstract public int get_getFront_status(); // успешно; очередь пуста
}

public abstract class QueueATD<T> extends ParentQueATD {
    //конструктор
    public QueueATD() {
    }
}

public abstract class Deque<T> extends ParentQueATD {
    //конструктор
    public Deque() {
    }

    //команды

    //постусловие: элемент item вставлен в голову очереди
    public abstract void addFront(T item);

    //предусловие: очередь не пуста;
    //постусловие: удалён элемент из хвоста
    public abstract void removeTail();


    //запросы
    //предусловие: очередь не пуста
    public abstract T getTail(); //получить элемент из хвоста очереди

    //запросы статусов
    abstract public int get_removeTail_status(); // успешно; очередь пуста

    abstract public int get_getTail_status(); // успешно; очередь пуста
}
