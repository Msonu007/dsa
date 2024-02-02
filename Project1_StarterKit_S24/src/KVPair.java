/**
 * This class holds a generic Key-value pair implementation. The purpose of this
 * class is to hold generic KVPair object which will be stored in the SkipList.
 * There is also a toString method for easily translating the objects contained
 * in the KVPair into a human readable string.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 * @param <K>
 *            Key to be used
 * @param <V>
 *            Value to be associated with the key
 */

//public class KVPair<K, V> implements Comparable<KVPair<K, V>> {
	//Another Implementation choice is to require K to implement Comparable not KVPair
	public class KVPair<K extends Comparable<? super K>, V> {

    // the object to be a key
    private K key;
    // the object to be the value at the key
    private V value;

    /**
     * The constructor assigns value to the key and value fields from user
     * specified objects.
     * 
     * @param strKey
     *            the key for the KVPair
     * @param val
     *            the value for the KVPair
     */
    public KVPair(K strKey, V val) {
        this.key = strKey;
        this.value = val;
    }


    /**
     * Returns the key of this KVPair
     *
     * @return the key of the KVPair
     */
    public K getKey() {
        return key;
    }


    /**
     * Returns the value of this KVPair
     *
     * @return the value that the KVPair holds
     */
    public V getValue() {
        return value;
    }

    /**
     * Returns the KVPair in a human readable format.
     *
     * @return A human readable string representing the KVPair object
     */
    public String toString() {
        return "(" + key + ", " + value
            + ")";
    }
}
