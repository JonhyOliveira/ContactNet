package dataStructures;

/**
 * Simple HashTable implementation using separate chaining with linked lists
 * @author Joao Oliveira 58001 & Rafel Borralho 58349
 * @version final
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	//The array of dictionaries.
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public ChainedHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime(capacity); // if capacity is a prime returns itself
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new CollisionList<>();
        maxSize = arraySize;
        currentSize = 0;
    }                                      


    public ChainedHashTable( )
    {
        this(HashTable.DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[hash(key)].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            this.rehash();

        V returnedValue = table[hash(key)].insert(key, value);
        currentSize++;

        return returnedValue;
    }

    @Override
    public V remove( K key )
    {
        V returnedValue = table[hash(key)].remove(key);
        currentSize --;
        return returnedValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void rehash() {

        // create table with double the size
        Dictionary<K, V>[] newTable = new Dictionary[HashTable.nextPrime(table.length*2)];
        for ( int i = 0; i < newTable.length; i++ )
            newTable[i] = new CollisionList<>();

        // copy entries to new table
        for (int i = 0; i < maxSize; i++) {
            Iterator<Entry<K, V>> rowIt = table[i].iterator();
            
            while (rowIt.hasNext()) {
                Entry<K, V> entry = rowIt.next();
                newTable[Math.abs(entry.getKey().hashCode()) % newTable.length].insert(entry.getKey(), entry.getValue());
            }
        }

        // assign new table
        maxSize *= newTable.length;
        table = newTable;
    }



    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        List<Entry<K, V>> entries = new DoublyLinkedList<>();
        int current = 0;
        for (int i = 0; i < table.length; i++) {
            Iterator<Entry<K, V>> entryIterator = table[i].iterator();

            while (entryIterator.hasNext() && current < currentSize) {
                entries.addLast(entryIterator.next());
            }
        }
        return entries.iterator();
    }

}