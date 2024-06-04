public abstract class ParentList <T> {

    //конструктор
// постусловие: создан новый пустой связный список
    public abstract ParentList<T> parentList();

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

    // постусловие: добавлен новый узел в хвост списка.
    public abstract void add_tail(T value);

    // постусловие: из списка удалятся все узлы с заданным значением;
    public abstract void remove_all(T value); // -- удалить в списке

    // предусловие: список не пустой;
    // постусловие: заменено значение текущего узла на заданное;
    public abstract void replace(T value);

    // установлен курсор на следующий узел с искомым значением (по отношению к текущему узлу);
    public abstract void find(T value);

    // запросы:
    // предусловие: список не пустой;
    public abstract T get();

    public abstract int size();

    public abstract int is_head();

    public abstract int is_tail();

    public abstract int is_value();

    // запросы статусов
    public abstract int get_head_status();

    public abstract int get_tail_status();

    public abstract int get_right_status();

    public abstract int get_put_right_status();

    public abstract int get_put_left_status();

    public abstract int get_remove_status();

    public abstract int get_add_replace_status();

    public abstract int get_add_find_status();

    public abstract int get_get_status();

    abstract class LinkedList<T> extends ParentList<T> {

        // конструктор
        public abstract LinkedList<T> LinkedList();
    }

    abstract class TwoWayList<T> extends ParentList<T> {
        public abstract TwoWayList<T> TwoWayList();


        // предусловие: левее курсора есть элемент;
        // постусловие: курсор сдвинут на один узел влево
        public abstract void left();

        public abstract int get_left_status(); // успешно; левее нету элемента

    }
}
