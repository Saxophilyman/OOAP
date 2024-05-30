public abstract class LinkedListATD<T> {

    public final int CURS_OFF = 0; // курсор ещё не установлен
    public final int CURS_ON = 1; // курсор установлен
    public final int CURS_HEAD_OFF = 0; //  курсор не находится в начале списка
    public final int CURS_HEAD_ON = 1; //  курсор находится в начале списка
    public final int CURS_TAIL_OFF = 0; //  курсор не находится в конце списка
    public final int CURS_TAIL_ON = 1; // курсор находится в конце списка

    public final int HEAD_OK = 1; // последняя head() отработала нормально
    public final int HEAD_ERR = 2; // список пуст
    public final int TAIL_OK = 1; // последняя tail() отработала нормально
    public final int TAIL_ERR = 2; // список пуст
    public final int RIGHT_OK = 1; // последняя right() отработала нормально
    public final int RIGHT_ERR = 2; // список пуст
    public final int PUT_RIGHT_CURS_ERR = 0; // курсор не установлен
    public final int PUT_RIGHT_OK = 1; // последняя put_right() отработала нормально
    public final int PUT_RIGHT_ERR = 2; // список пуст
    public final int PUT_LEFT_CURS_ERR = 0; // курсор не установлен
    public final int PUT_LEFT_OK = 1; // последняя put_left() отработала нормально
    public final int PUT_LEFT_ERR = 2; // список пуст
    public final int REMOVE_CURS_ERR = 0; // курсор не установлен
    public final int REMOVE_OK = 1; // последняя remove() отработала нормально
    public final int REMOVE_ERR = 2; // список пуст
    public final int ADD_TO_EMPTY_OK = 1; // последняя add_to_empty отработала нормально
    public final int ADD_TO_EMPTY_ERR = 2; // список не пуст
    public final int ADD_TAIL_OK = 1; // последняя add_tail отработала нормально
    public final int ADD_TAIL_ERR = 2; // список пуст
    public final int REPLACE_CURS_ERR = 0; // курсор не установлен
    public final int REPLACE_OK = 1; // последняя replace() отработала нормально
    public final int REPLACE_ERR = 2; // список пуст
    public final int FIND_CURS_ERR = 0; // курсор не установлен
    public final int FIND_OK = 1; // последняя find() отработала нормально
    public final int FIND_ERR = 2; // список пуст
    public final int GET_CURS_ERR = 0; // курсор не установлен
    public final int GET_OK = 1; // последняя get() отработала нормально
    public final int GET_ERR = 2; // список пуст




    //конструктор
// постусловие: создан новый пустой связный список
    public abstract LinkedListATD<T> linkedListATD();


    // команды:

    // предусловие: список не пустой;
    // постусловие: курсор установлен на первый узел в списке;
    public abstract void head();

    // предусловие: список не пустой;
    // постусловие: курсор установлен на последний узел в списке;
    public abstract void tail();

    // предусловие: список не пустой;
    // постусловие: курсор сдвинут на один узел вправо;
    public abstract void right();

    // предусловие: список не пустой; установлен курсор;
    // постусловие: вставлен следом за текущим узлом новый узел с заданным значением;
    public abstract void put_right(T value);

    // предусловие: список не пустой; установлен курсор;
    // постусловие: вставлен перед текущим узлом новый узел с заданным значением;
    public abstract void put_left(T value);

    // предусловие: установлен курсор;
    // постусловие: удалён текущий узел
    public abstract void remove();
            /*(курсор смещается к правому соседу, если он есть,
             в противном случае курсор смещается к левому соседу,
             если он есть);*/

    // постусловие: из списка удаляются все значения
    public abstract void clear();

    // предусловие: список пустой;
    // постусловие: добавлен новый узел в пустой список.
    public abstract void add_to_empty(T value);

    // предусловие: список не пустой;
    // постусловие: добавлен новый узел в хвост списка.
    public abstract void add_tail(T value);

    // предусловие: список не пустой; установлен курсор;
    // постусловие: заменено значение текущего узла на заданное;
    public abstract void replace(T value);

    // предусловие: список не пустой; установлен курсор;
    // установлен курсор на следующий узел с искомым значением (по отношению к текущему узлу);
    public abstract void find(T value);

    // постусловие: из списка удалятся все узлы с заданным значением;
    public abstract void remove_all(T value); // -- удалить в списке


    // запросы:
    // предусловие: стек не пустой;
    public abstract T get(); // -- получить значение текущего узла;

    public abstract int size();

    public abstract int is_head();

    public abstract int is_tail();

    public abstract int is_value();


    // дополнительные запросы:
    public abstract int get_curs_status();
    public abstract int get_curs_head_status();
    public abstract int get_curs_tail_status();
    public abstract int get_head_status();
    public abstract int get_tail_status();
    public abstract int get_right_status();
    public abstract int get_put_right_status();
    public abstract int get_put_left_status();
    public abstract int get_remove_status();
    public abstract int get_add_to_empty_status();
    public abstract int get_add_tail_status();
    public abstract int get_add_replace_status();
    public abstract int get_add_find_status();
    public abstract int get_get_status();
}
// 2.2. Почему операция tail не сводима к другим операциям (если исходить из эффективной реализации)?
// Возможно из-за того, что никакие другие комбинации операций не дают результата операции tail.
// Очевидный приём мог бы исходить из использования операций head и right и size, но их в той структуре АТД,
// которая имеется их недостаточно (нужна, возможно, отслеживающая переменная текущего размере списка)
// Другими словами исходя из вышеописанной АТД всегда оставался бы вопрос - дошёл ли курсор до конца списка или нет.

//2.3. Операция поиска всех узлов с заданным значением, выдающая список таких узлов, уже не нужна. Почему?
// Возможно потому что теперь можно удалить все узлы с заданным значением и без этой операции.
// Насколько понимаю такая возможность появляется с момента введения курсора в модель.
