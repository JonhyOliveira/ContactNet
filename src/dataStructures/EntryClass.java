package dataStructures;

/**
 * Implementation os the Entry data type with a setter for the value
 * @author Joao Oliveira 58001 & Rafel Borralho 58349
 * @version final
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
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

    /**
     * Sets the value of the entry
     */
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
