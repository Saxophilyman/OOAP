import java.util.List;
import java.util.Stack;

//На основе примера АТД Stack (но не в форме наследования, а как автономное решение)
//определите АТД BoundedStack и выполните его реализацию.
//BoundedStack -- это ограниченный стек, конструктор которого получает целое положительное значение,
//задающее максимально допустимое количество элементов в стеке.
//Если параметр не задан, конструктор по умолчанию формирует стек максимум на 32 элемента.

public abstract class BoundedStack<T> {
    public final int POP_NIL = 0; // pop() ещё не вызывалась
    public final int POP_OK = 1; // последняя pop() отработала нормально
    public final int POP_ERR = 2; // стек пуст
    public final int PEEK_NIL = 0; // peek() ещё не вызывалась
    public final int PEEK_OK = 1; // последняя peek() отработала нормально
    public final int PEEK_ERR = 2; // стек пуст
    public final int PUSH_NIL = 0; // push() ещё не вызывалась
    public final int PUSH_OK = 1; // последняя push() отработала нормально
    public final int PUSH_ERR = 2; // превышен допустимый размер стёка командой push
    public final int CREATE_OK = 1; // создание пустого стёка завершилось успешно
    public final int CREATE_ERR = 2; // создание пустого стёка не произошло, несоответствие условия "целое положительное значение" в конструкторе

    // конструктор по умолчанию
    public abstract void Stack(); // постусловие: создан новый пустой стек

    // конструктор с аргументами
    // предусловие: целое положительное значение в аргументе;
    // постусловие: создан новый пустой стек
    public abstract void Stack(int value);


    // команды:

    // постусловие: из стека удалятся все значения
    public abstract void clear();

    // предусловие: добавление нового элемента не превышает допустимый размер стека;
    // постусловие: в стек добавлено новое значение
    public abstract void push(T value);

    // предусловие: стек не пустой;
    // постусловие: из стека удалён верхний элемент
    public abstract void pop();


    // запросы:
    // предусловие: стек не пустой
    public abstract T peek();
    public abstract int size();


    // дополнительные запросы:
    public abstract int get_pop_status(); // возвращает значение POP
    public abstract int get_peek_status(); // возвращает значение PEEK
    public abstract int get_push_status(); // возвращает значение PUSH
    public abstract int get_create_status(); // возвращает значение статуса создания конструктора
}


public class BoundedStack<T> {

    // скрытые поля
    private List<T> stack; // основное хранилище стека
    private int peek_status; // статус запроса peek()
    private int pop_status; // статус команды pop()
    private int create_status; // статус создания объекта
    private int push_status; // статус добавления значения (добавляется предусловие)
    private int maxSize; // целевое значение размера для стека
    private final int defaulf_value = 32; // значение размера для конструктора по умолчанию;

    // интерфейс класса, реализующий АТД Stack
    public final int POP_NIL = 0;
    public final int POP_OK = 1;
    public final int POP_ERR = 2;
    public final int PEEK_NIL = 0;
    public final int PEEK_OK = 1;
    public final int PEEK_ERR = 2;
    public final int PUSH_NIL = 0;
    public final int PUSH_OK = 1;
    public final int PUSH_ERR = 2;
    public final int CREATE_OK = 1;
    public final int CREATE_ERR = 2;


    // конструктор по умолчанию, использующий значение по умолчанию
    public void Stack() {
        maxSize = defaulf_value;
        clear();
    }

    // конструктор с параметром, использующий заданное целое положительное значение
    public void Stack(int value) {
        if (value > 0) {
            maxSize = value;
            clear();
        }
        create_status = CREATE_ERR;
    }
    
    public void clear() {
        stack = new Stack<>();// пустой список/стек

        // начальные статусы для предусловий peek() и pop()
        create_status = CREATE_OK;
        push_status = PUSH_NIL;
        peek_status = PEEK_NIL;
        pop_status = POP_NIL;
    }

    public void push(T value) {
        if (size() + 1 <= maxSize) {
            stack.add(value);
            push_status = PUSH_OK;
        }
        push_status = PUSH_ERR;
    }

    public void pop() {
        if (size() > 0) {
            stack.removeLast();
            pop_status = POP_OK;
        } else
            pop_status = POP_ERR;
    }

    public T peek() {
        T result;
        if (size() > 0) {
            result = stack.getLast();
            peek_status = PEEK_OK;
        } else {
            result = null;
            peek_status = PEEK_ERR;
        }
        return result;
    }

    public int size() {
        return stack.size();
    }

    // запросы статусов
    public int get_pop_status() {
        return pop_status;
    }

    public int get_peek_status() {
        return peek_status;
    }

    public int get_push_status() {
        return push_status;
    }

    public int get_create_status() {
        return create_status;
    }
}
