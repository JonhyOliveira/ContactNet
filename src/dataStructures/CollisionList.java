package dataStructures;

public class CollisionList<K, V> implements Dictionary<K, V> {

    private List<Entry<K, V>> entries;

    public CollisionList() {
        entries = new DoublyLinkedList<>();
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public V find(K key) {

        Iterator<Entry<K, V>> entryIterator = entries.iterator();

        while (entryIterator.hasNext()) {
            Entry<K, V> entry = entryIterator.next();
            if (entry.getKey().equals(key))
                return entry.getValue();
        }

        return null; // not found
    }

    @Override
    public V insert(K key, V value) {
        V valueFound = find(key);
        Entry<K, V> newEntry = new EntryClass<>(key, value);

        if (valueFound != null) {
            int pos = entries.find(newEntry);
            entries.remove(pos);
            entries.add(pos, newEntry);
        }
        else
            entries.addLast(newEntry);

        return valueFound;
    }

    @Override
    public V remove(K key) {
        V valueFound = find(key);

        if (valueFound != null)
            entries.remove(entries.find(new EntryClass<>(key, null)));

        return valueFound;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return entries.iterator();
    }

}
