public abstract class DynArrayATD<T> {

    // конструктор
// постусловие: создан пустой массив
    public abstract DynArrayATD<T> DynArray();

    // команды
// предусловие: индекс i не выходит за пределы массива;
// постусловие: значение с индексом i изменено на itm
    public abstract void insert(T itm, int i);

// предусловие: индекс i не выходит за пределы массива;
// постусловие: после значения с индексом i добавлено значение itm
    public abstract void insert_right(T itm, int i);

// предусловие: индекс i не выходит за пределы массива;
// постусловие: перед значением с индексом i добавлено значение itm
    public abstract void insert_left(T itm, int i);


// постусловие: в хвост массива добавлено значение T itm
    public abstract void append(T itm);

// предусловие: индекс i не выходит за пределы массива;
// постусловие: значение с индексом i удалёно из массива;
    public abstract void remove(int i);

// постусловие: массив очищен ото всех значений;
    public abstract void clearAll();

    // запросы
// предусловие: индекс i не выходит за пределы массива;
    public abstract T getItem(int i);

    public abstract int size();

    // запросы статусов 
    public abstract int get_insert_status();
    public abstract int get_insert_right_status();
    public abstract int get_insert_left_status();
    public abstract int get_remove_status(); 
    public abstract int get_removeAll_status(); 
    public abstract int get_getItem_status();
}
