package dataStructures;

/**
 * Entry Abstract Data Type
 * Includes description of general methods to be implemented by entries.
 * @author Joao Oliveira 58001 & Rafel Borralho 58349
 * @version final
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */

public interface Entry<K,V> {

	/**
	 * @return the key associated with the entry
	 */
	K getKey();


	/**
	 * @return the value associated with the entry
	 */
	V getValue();

}
