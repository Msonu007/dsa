import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Mrunaldhar Bathula
 * 
 * @version 2021-08-23
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List

    /**
     * Initializes the fields head, size and level
     */
    /**
     * Constructs a new, empty SkipList. Initializes the head of the skip list
     * with a sentinel node
     * having a {@code null} value and level 0. Sets the initial size of the
     * skip list to 0. This setup
     * is essential for the correct functioning of the skip list, providing a
     * starting point for insertion,
     * search, and deletion operations within the list.
     * <p>
     * Input Variables: None (constructor does not take any parameters).
     * <p>
     * Output/Return Type: Creates a new instance of a SkipList. The constructor
     * does not have a return type.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Generates a random level based on a probabilistic approach, where the
     * chance of increasing the level is 50%.
     * This method uses a loop to determine the level by generating a random
     * number and checking if it's even.
     * The loop continues (incrementing the level) until an odd number is
     * generated, simulating a "coin flip" approach
     * for level determination in probabilistic data structures like skip lists.
     * <p>
     * No explicit input variables are required as this method generates its own
     * random numbers internally.
     * 
     * @return An {@code int} representing the generated random level. The level
     *         starts at 0 and increments
     *         by 1 each time a generated random number is even, stopping when
     *         an odd number is encountered.
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for key-value pairs with the specified key in the skip list.
     * This method traverses the skip list vertically and horizontally to locate
     * all occurrences of the given key.
     * It then returns an ArrayList containing all key-value pairs associated
     * with the specified key found in the skip list.
     * 
     * @param key
     *            The key to search for in the skip list.
     * @return An ArrayList of key-value pairs (KVPair) associated with the
     *         specified key found in the skip list.
     *         If no key-value pair with the specified key is found, an empty
     *         ArrayList is returned.
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        SkipNode l = this.head;
        HashSet<SkipNode> pointers = new HashSet<SkipNode>();
        ArrayList<KVPair<K, V>> sol = new ArrayList<KVPair<K, V>>();
        for (int i = l.level; i >= 0; i--) {
            l = this.head.forward[i];
            while (l != null) {
                if (l.pair.getKey().equals(key)) {
                    if (!pointers.contains(l)) {
                        sol.add(l.pair);
                        pointers.add(l);
                    }
                    l = l.forward[i];
                }
                else {
                    l = l.forward[i];
                }

            }
        }
        return sol;
    }


    /**
     * Performs a region search to find all key-value pairs in the database
     * where the value (assumed to be a {@code Rectangle}) intersects with the
     * given rectangle.
     * <p>
     * This method iterates through all entries in the database, checking each
     * one to see if its value (a {@code Rectangle}) intersects
     * with the {@code Rectangle} provided as the input parameter. All
     * intersecting rectangles are then collected into an {@code ArrayList}.
     * <p>
     * Note: This method assumes that the generic type {@code V} is or can be
     * cast to {@code Rectangle}. It is the caller's responsibility to ensure
     * that this assumption holds true in the context where the method is used.
     * 
     * @param rec
     *            The {@code Rectangle} to use for the region search. It is the
     *            area against which all database rectangles are checked for
     *            intersection.
     * @return An {@code ArrayList<KVPair<K, V>>} containing all key-value pairs
     *         where the value intersects with the provided {@code Rectangle}.
     * 
     * @throws ClassCastException
     *             if the generic type {@code V} cannot be cast to
     *             {@code Rectangle}.
     * @author Mrunaldhar Bathula
     */
    public ArrayList<KVPair<K, V>> regionSearch(V rec) {
        ArrayList<KVPair<K, V>> sol = new ArrayList<KVPair<K, V>>();
        KVPair<K, V> s;
        Iterator<KVPair<K, V>> it = this.iterator();
        while (it.hasNext()) {
            s = it.next();
            if (((Rectangle)s.getValue()).intersect((Rectangle)rec)) {
                sol.add(s);
            }
        }
        return sol;
    }


    /**
     * Generates a list of all intersecting rectangle pairs within a skip list
     * structure. Each rectangle is represented by
     * a {@code KVPair} object, and intersections are identified based on the
     * geometric properties of the rectangles.
     * For each pair of intersecting rectangles, two strings are generated and
     * added to the list: one for the intersection
     * as it is found, and another for the inverse of that intersection
     * (swapping the rectangles' positions in the output format).
     * <p>
     * This method traverses the skip list, comparing every possible pair of
     * rectangles to determine if they intersect,
     * using their coordinate and size properties. Intersections are formatted
     * as strings indicating the key and dimensions
     * of each intersecting rectangle pair.
     * <p>
     * No explicit input parameters are required as the method operates on the
     * internal state of the skip list.
     * 
     * @return ArrayList<String> containing formatted strings for each
     *         intersecting pair of rectangles, including both
     *         direct and inverse intersection representations.
     * @author Mrunaldhar Bathula
     */
    public ArrayList<String> allIntersections() {
        ArrayList<String> intersectionList = new ArrayList<String>();
        SkipNode temp1;
        SkipNode temp2;
        String intersectionStr;
        String inverseIntersectionStr;
        temp1 = this.head.forward[0];
        while (temp1 != null) {
            temp2 = temp1;
            while (temp2 != null) {
                if (temp1 != temp2 && ((Rectangle)temp2.pair.getValue())
                    .intersect((Rectangle)temp1.pair.getValue())) {
                    KVPair<K, V> k = temp1.pair;
                    V val = temp1.pair.getValue();
                    KVPair<K, V> l = temp2.pair;
                    V val1 = temp2.pair.getValue();
                    intersectionStr = String.format(
                        "(%s, %d, %d, %d, %d) | (%s, %d, %d, %d, %d)\n", k
                            .getKey().toString(), ((Rectangle)val)
                                .getxCoordinate(), ((Rectangle)val)
                                    .getyCoordinate(), ((Rectangle)val)
                                        .getWidth(), ((Rectangle)val)
                                            .getHeight(), l.getKey().toString(),
                        ((Rectangle)val1).getxCoordinate(), ((Rectangle)val1)
                            .getyCoordinate(), ((Rectangle)val1).getWidth(),
                        ((Rectangle)val1).getHeight());
                    inverseIntersectionStr = String.format(
                        "(%s, %d, %d, %d, %d) | (%s, %d, %d, %d, %d)\n", l
                            .getKey().toString(), ((Rectangle)val1)
                                .getxCoordinate(), ((Rectangle)val1)
                                    .getyCoordinate(), ((Rectangle)val1)
                                        .getWidth(), ((Rectangle)val1)
                                            .getHeight(), k.getKey().toString(),
                        ((Rectangle)val).getxCoordinate(), ((Rectangle)val)
                            .getyCoordinate(), ((Rectangle)val).getWidth(),
                        ((Rectangle)val).getHeight());
                    intersectionList.add(intersectionStr);
                    intersectionList.add(inverseIntersectionStr);
                }
                temp2 = temp2.forward[0];
            }
            temp1 = temp1.forward[0];
        }
        return intersectionList;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by
     * its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    // remove the below function while submitting

    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int newLevel = this.randomLevel();

        SkipNode[] tempList = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, newLevel + 1);
        if (newLevel > this.head.level) {
            this.adjustHead(newLevel);
        }
        SkipNode temp = this.head;
        SkipNode next;
        SkipNode newNode = new SkipNode(it, newLevel);
        for (int i = newLevel; i >= 0; i--) {
            temp = this.head;
            while (true) {
                if (temp.forward[i] != null) {
                    if (temp.forward[i].pair.getKey().compareTo(it
                        .getKey()) > 0) {
                        tempList[i] = temp;
                        break;
                    }
                    else {
                        temp = temp.forward[i];
                    }
                }
                else {
                    tempList[i] = temp;
                    break;
                }
            }
        }

        for (int level = tempList.length - 1; level >= 0; level--) {
            temp = tempList[level];
            next = temp.forward[level];
            temp.forward[level] = newNode;
            newNode.forward[level] = next;
        }
        this.size += 1;
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices
     * than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel) {
        SkipNode[] tempList = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, newLevel + 1);
        for (int i = 0; i < this.head.level + 1; i++) {
            if (i < tempList.length) {
                tempList[i] = this.head.forward[i];
            }
        }
        this.head.forward = tempList;
        this.head.level = newLevel;
    }


    /**
     * Retrieves the level of the head node in the data structure.
     * This method returns the level of the head node, which is a property of
     * the data structure.
     * 
     * @return The level of the head node in the skiplist.
     */
    public int getHeadLevel() {
        return this.head.level;
    }

// @SuppressWarnings("unchecked")
// public ArrayList<K> getForward() {
// ArrayList<K> sol = new ArrayList<K>();
// for (int i=0;i<this.head.forward.length;i++) {
// if (this.head.forward[i] == null) {
// sol.add((K) "null");
// }else {
// sol.add(this.head.forward[i].pair.getKey());
// }
// }
// return sol;
// }


    /**
     * Retrieves the length of the 'forward' array of the node pointed to by
     * 'head'.
     * This method is used to determine the number of forward pointers (links)
     * in the head node of a data structure,
     * which can be indicative of the node's level or depth within that
     * structure.
     * <p>
     * Inputs: None.
     * <p>
     * Output: Returns an {@code int} value representing the length of the
     * 'forward' array.
     * 
     * @return Integer length of the forward list of the head of the skiplist.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public int getForwardLength() {
        return this.head.forward.length;
    }


    /**
     * Iterates over the head nodes of a skip list, returning a list of keys.
     * This method traverses the forward links from the head of the skip list,
     * collecting the keys of encountered nodes.
     * If a node in the traversal is null, a placeholder string "a_" is added to
     * the result list to indicate a missing node.
     * This approach allows for the identification of the structure and
     * distribution of nodes at the list's head level.
     * <p>
     * Note: This method suppresses unchecked warnings due to casting
     * operations.
     * 
     * @return An {@code ArrayList<K>} containing the keys of the nodes starting
     *         from the head of the skip list.
     *         If a forward link is null at any level, "a_" is added as a
     *         placeholder in its position.
     * 
     * @implNote The method uses unchecked casting when adding placeholder
     *           strings to the list of keys,
     *           which is why {@code @SuppressWarnings("unchecked")} is used.
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    @SuppressWarnings("unchecked")
    public ArrayList<K> iterateHead() {
        ArrayList<K> sol = new ArrayList<K>();
        SkipNode[] k = this.head.forward;
        for (int i = 0; i < k.length; i++) {
            if (k[i] == null) {
                sol.add((K)"a_");
            }
            else {
                sol.add(k[i].pair.getKey());
            }

        }
        return sol;

    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the
     * pair was valid and false if not.
     * 
     * @param key
     *            K the key to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode l = this.head;
        SkipNode pos = null;
        SkipNode next = null;
        boolean found = false;
        SkipNode[] tempList = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, this.head.level + 1);
        for (int i = this.head.level; i >= 0; i--) {
            l = this.head;
            while (true) {
                if (l.forward[i] != null && l.forward[i].pair.getKey().equals(
                    key)) {

                    if (pos == null) {
                        pos = l.forward[i];
                        tempList[i] = l;
                        break;
                    }
                    else if (l.forward[i] == pos) {
                        tempList[i] = l;
                        break;
                    }
                    else {
                        l = l.forward[i];
                    }
                }
                else if (l.forward[i] != null && key.compareTo(l.forward[i].pair
                    .getKey()) > 0) {
                    l = l.forward[i];
                }
                else {
                    break;
                }

            }
        }
        if (pos == null) {
            return null;
        }
        for (int i = pos.level; i >= 0; i--) {
            next = pos.forward[i];
            tempList[i].forward[i] = next;
            pos.forward[i] = null;
        }
        this.size = this.size - 1;
        return pos.pair;
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> removeByValue(V val) {
        SkipNode temp = this.head;
        SkipNode next = null;
        SkipNode[] tempList = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, this.head.level + 1);
        SkipNode ele = null;

        temp = temp.forward[0];
        while (temp != null) {
            if (temp.pair.getValue().equals(val)) {
                ele = temp;
                break;
            }
            temp = temp.forward[0];
        }
        if (ele == null) {
            return null;
        }

        for (int i = this.head.level; i >= 0; i--) {
            temp = this.head;
            while (true) {
                if (temp.forward[i] != null && temp.forward[i].equals(ele)) {
                    tempList[i] = temp;
                    break;
                }
                else if (temp.forward[i] != null) {
                    temp = temp.forward[i];
                }
                else {
                    break;
                }
            }
        }
        for (int i = ele.level; i >= 0; i--) {
            next = ele.forward[i];
            tempList[i].forward[i] = next;
            ele.forward[i] = null;
        }
        this.size = this.size - 1;
        return ele.pair;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        System.out.println("SkipList dump:");

        // Print the header node's level

        System.out.printf("Node has depth %d, value null\n", this.head.level
            + 1);

        // Iterate through the SkipList
        SkipNode temp = this.head.forward[0]; // Start with the node after the
                                              // header
        while (temp != null) {
            // Print node details. Special handling for Rectangle values.
            if (temp.pair.getValue() instanceof Rectangle) {
                Rectangle tempRectangle = (Rectangle)temp.pair.getValue();
                System.out.printf(
                    "Node has depth %d, value (%s, %d, %d, %d, %d)\n", temp
                        .getLevel() + 1, temp.pair.getKey().toString(),
                    tempRectangle.getxCoordinate(), tempRectangle
                        .getyCoordinate(), tempRectangle.getWidth(),
                    tempRectangle.getHeight());
            }
            else {
                // Generic print format for non-Rectangle values
                System.out.printf("Node has depth %d, value (%s)\n", temp
                    .getLevel(), temp.pair.toString());
            }
            temp = temp.forward[0]; // Move to the next node
        }

        // Print the size of the SkipList
        System.out.printf("SkipList size is: %d\n", this.size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from
         * the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }


        public int getLevel() {
            return this.level;
        }

    }


    /**
     * Initializes a new {@code SkipListIterator} that iterates over the
     * elements in a skip list.
     * The iterator starts from the beginning of the skip list, preparing to
     * iterate through all its elements.
     * 
     * No input variables.
     * No output return type as this is a constructor.
     * 
     * @author Mrunaldhar Bathula
     */
    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        /**
         * Constructs a new {@code SkipListIterator} initializing the iterator's
         * current position to the head of the skip list.
         * This constructor sets up the iterator to start iterating from the
         * beginning of the skip list.
         * <p>
         * Input Variables: None. The constructor does not require any input
         * parameters.
         * <p>
         * Output/Return Type: This is a constructor method; thus, it does not
         * return a value but initializes a new instance of
         * {@code SkipListIterator}.
         * 
         * @author Mrunaldhar Bathula
         * @version 1.2
         */
        public SkipListIterator() {
            current = head;
        }


        /**
         * Determines if there are more elements in the iteration beyond the
         * current element.
         * This method checks if the next element in the forward array at the
         * lowest level is not null,
         * indicating that there is a subsequent element available for
         * iteration.
         * <p>
         * Input Variables: None. The method operates on the internal state of
         * the iterator, specifically the {@code current} node's forward
         * reference.
         * <p>
         * Output Return Type: {@code boolean} - Returns {@code true} if there
         * is a next element available; {@code false} otherwise.
         * 
         * @return boolean indicating the presence of a next element in the
         *         iteration.
         */
        @Override
        public boolean hasNext() {
            return current.forward[0] != null;
        }


        /**
         * Retrieves the next element in the iteration and advances the iterator
         * by one position.
         * This method accesses the next {@code KVPair} object in the sequence
         * by following the forward reference
         * from the current position, updates the current position to this next
         * element, and then returns the element.
         * <p>
         * It assumes that the iterator is in a valid state with a next element
         * available. The method should be used
         * in conjunction with a check for {@code hasNext()} to avoid errors due
         * to reaching the end of the sequence.
         * 
         * @return KVPair<K, V> The next key-value pair in the iteration.
         */
        @Override
        public KVPair<K, V> next() {
            KVPair<K, V> elem = current.forward[0].element();
            current = current.forward[0];
            return elem;
        }

    }

    /**
     * Provides an iterator over the elements in this collection.
     * This method overrides the {@code iterator} method to return a
     * {@code SkipListIterator},
     * which allows for traversing the elements stored in a skip list data
     * structure.
     * <p>
     * The {@code SkipListIterator} specifically iterates over
     * {@code KVPair<K, V>} objects,
     * enabling sequential access to the key-value pairs contained within the
     * skip list.
     * <p>
     * Inputs: None.
     * <p>
     * Output/Return Type: {@code Iterator<KVPair<K, V>>} - An iterator that
     * provides sequential access to the
     * key-value pairs in the collection, encapsulated within
     * {@code KVPair<K, V>} objects.
     * 
     * @return An instance of {@code SkipListIterator} for iterating over the
     *         skip list's elements.
     */
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
