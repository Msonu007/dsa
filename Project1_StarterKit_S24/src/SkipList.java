import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
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
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
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
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        SkipNode l = this.head;
        HashSet<SkipNode> pointers = new HashSet<SkipNode>() ;
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
        int new_level = this.randomLevel();

        SkipNode[] TempList = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, new_level + 1);
        if (new_level > this.head.level) {
            this.adjustHead(new_level);
        }
        SkipNode temp = this.head;
        SkipNode next;
        SkipNode newNode = new SkipNode(it, new_level);
        for (int i = new_level; i >= 0; i--) {
            temp = this.head;
            while (true) {
                if (temp.forward[i] != null) {
                    if (temp.forward[i].pair.getKey().compareTo(it
                        .getKey()) > 0) {
                        TempList[i] = temp;
                        break;
                    }
                    else {
                        temp = temp.forward[i];
                    }
                }
                else {
                    TempList[i] = temp;
                    break;
                }
            }
        }

        for (int level = TempList.length - 1; level >= 0; level--) {
            temp = TempList[level];
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
        SkipNode[] temp1_list = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, newLevel + 1);
        for (int i = 0; i < head.level + 1; i++) {
            temp1_list[i] = this.head.forward[i];
        }
        this.head.forward = temp1_list;
        this.head.level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the
     * pair was valid and false if not.
     * 
     * @param pair
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode l = this.head, pos = null, next = null;
        boolean found = false;
        SkipNode[] TempList = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, this.head.level + 1);
        for (int i = this.head.level; i >= 0; i--) {
            l = this.head;
            while (true) {
                if (l.forward[i] != null && l.forward[i].pair.getKey().equals(
                    key)) {
                    if (pos == null) {
                        pos = l.forward[i];
                        TempList[i] = l;
                        break;
                    }
                    else {
                        if (l.forward[i] == pos) {
                            TempList[i] = l;
                            break;
                        }
                        else {
                            l = l.forward[i];
                        }
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
            TempList[i].forward[i] = next;
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
        SkipNode temp = this.head, next = null;
        SkipNode[] TempList = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, this.head.level + 1);
        SkipNode ele = null;
        for (int i = this.head.level; i >= 0; i--) {
            temp = this.head;
            while (true) {
                if (temp.forward[i] != null && temp.forward[i].pair.getValue()
                    .equals(val)) {
                    if (ele == null) {
                        ele = temp.forward[i];
                        TempList[i] = temp;
                        break;
                    }
                    else {
                        if (temp.forward[i].equals(ele)) {
                            TempList[i] = temp;
                            break;
                        }
                        else {
                            temp = temp.forward[i];
                        }
                    }
                }
                else if (temp.forward[i] != null) {
                    temp = temp.forward[i];
                }
                else {
                    break;
                }
            }
        }
        if (ele == null) {
            return null;
        }
        for (int i = ele.level; i >= 0; i--) {
            next = ele.forward[i];
            TempList[i].forward[i] = next;
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
        System.out.printf("Node has depth %d, Value null\n", this.head.level);

        // Iterate through the SkipList
        SkipNode temp = this.head.forward[0]; // Start with the node after the header
        while (temp != null) {
            // Print node details. Special handling for Rectangle values.
            if (temp.pair.getValue() instanceof Rectangle) {
                Rectangle tempRectangle = (Rectangle) temp.pair.getValue();
                System.out.printf("Node has depth %d, Value (%s, %d, %d, %d, %d)\n",
                                  temp.getLevel(),
                                  temp.pair.getKey().toString(),
                                  tempRectangle.getxCoordinate(),
                                  tempRectangle.getyCoordinate(),
                                  tempRectangle.getWidth(),
                                  tempRectangle.getHeight());
            } else {
                // Generic print format for non-Rectangle values
                System.out.printf("Node has depth %d, Value (%s)\n",
                                  temp.getLevel(),
                                  temp.pair.toString());
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


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return current.forward[0] != null;
        }


        @Override
        public KVPair<K, V> next() {
            // TODO Auto-generated method stub
            KVPair<K, V> elem = current.forward[0].element();
            current = current.forward[0];
            return elem;
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // TODO Auto-generated method stub
        return new SkipListIterator();
    }

}
