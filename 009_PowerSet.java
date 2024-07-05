public abstract class PowerSetATD<T> extends HashTableATD<T>{
    // ** конструктор **
    // постусловие: создано пустое множество с максимальным количеством элементов size
    protected PowerSetATD(int size){}

    // ** запросы **
    // возвращает пересечение текущего множества и set2
    public abstract PowerSetATD<T> intersection(PowerSetATD<T> set2);

    // возвращает объединение текущего множества и set2
    public abstract PowerSetATD<T> union(PowerSetATD<T> set2);

    // возвращает разницу текущего множества и set2
    public abstract PowerSetATD<T> difference(PowerSetATD<T> set2);

    // проверка, что set2 есть подмножество текущего множества
    public abstract boolean isSubset(PowerSetATD<T> set2);
}

class PowerSet<T> extends HashTable<T> {
    PowerSet(int size){
        super(size);
    }

    public PowerSet<T> intersection(PowerSet<T> other) {
        PowerSet<T> newSet = new PowerSet<>(this.capacity);
        for (T slot : this.slots) {
            if (slot != null && other.contains(slot))
                newSet.put(slot);
        }
        return newSet;
    }

    public PowerSet<T> union(PowerSet<T> other) {
        PowerSet<T> newSet = new PowerSet<>(this.capacity + other.capacity);
        for (T slot : this.slots) {
            if (slot != null && !other.contains(slot))
                newSet.put(slot);
        }
        for (T slot : other.slots) {
            if (slot != null && !this.contains(slot))
                newSet.put(slot);
        }
        return newSet;
    }

    public PowerSet<T> difference(PowerSet<T> other){
        PowerSet<T> newSet = new PowerSet<>(this.capacity);
        for (T slot : this.slots) {
            if (slot != null && !other.contains(slot))
                newSet.put(slot);
        }
        return newSet;
    }

    public boolean isSubset(PowerSet<T> other) {
        for (T slot : other.slots)
            if (slot != null && !this.contains(slot))
                return false;
        return true;
    }
}
