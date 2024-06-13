import java.util.ArrayList;
import java.util.List;

public abstract class QueueATD<T> {

    // конструктор
    // постусловие: создана пустая очередь
    public QueueATD() {
    }

    //команды
    //постусловие: элемент item вставлен в хвост очереди
    public abstract void enqueue(T item);

    //постусловие: удалён элемент из головы
    public abstract void dequeue() ;

    //запросы
    //предусловие: очередь не пуста
    public abstract T get() ;
    public abstract int size();

    // запросы статусов
    abstract public int get_dequeue_status(); // успешно; очередь пуста
    abstract public int get_get_status(); // успешно; очередь пуста
}

class QueueClass<T> extends QueueATD<T>{
    private List<T> queue;
    private int size;
    private final int DEQUEUE_STATUS_NIL = 0; // удаление элемента ещё не вызывалось
    private final int DEQUEUE_STATUS_OK = 1; // удаление прошло успешно
    private final int DEQUEUE_STATUS_ERR = 2; // очередь пустая
    private final int GET_STATUS_NIL = 0; // запрос элемента ещё не вызывался
    private final int GET_STATUS_OK = 1; //  запрос прошёл успешно
    private final int GET_STATUS_ERR = 2; // очередь пустая

    private int dequeue_status;
    private int get_status;

    public QueueClass(){
        super();
        queue = new ArrayList<>();
        size = 0;
        dequeue_status = DEQUEUE_STATUS_NIL;
        get_status = GET_STATUS_NIL;
    }


    @Override
    public void enqueue(T item) {
        queue.add(item);
        size++;
    }

    @Override
    public void dequeue() {
        if (queue.isEmpty()) {
            dequeue_status = DEQUEUE_STATUS_ERR;
            return;
        }
        dequeue_status = DEQUEUE_STATUS_OK;
        queue.removeFirst();
        size--;
    }

    @Override
    public T get() {
        if (queue.isEmpty()) {
            get_status = GET_STATUS_ERR;
            return null;
        }
        get_status = GET_STATUS_OK;
        return queue.getFirst();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get_dequeue_status() {
        return dequeue_status;
    }

    @Override
    public int get_get_status() {
        return get_status;
    }
}
