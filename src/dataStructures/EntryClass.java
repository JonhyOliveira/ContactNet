package dataStructures;

public class EntryClass<K, V> implements Entry<K, V> {

    private K key;
    private V value;

    public EntryClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entry) {
            Entry<?, ?> other = (Entry<?, ?>) obj;
            return other.getKey().equals(this.getKey());
        }

        return super.equals(obj);
    }
}
