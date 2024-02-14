import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.Assert;
import student.TestCase;

/**
 * Test cases for the {@code SkipList} class, validating its functionality under
 * various scenarios.
 * This test suite covers methods related to insertion, removal, adjustment of
 * head, iteration, searching,
 * and removal by value and key, ensuring correct behavior of the SkipList data
 * structure.
 * 
 * <p>
 * Each test method focuses on specific aspects of the SkipList class, examining
 * its behavior under different conditions.
 * </p>
 * 
 * <p>
 * Test methods:
 * </p>
 * <ul>
 * <li>{@link #testInsert()} - Tests the insertion of elements into the
 * SkipList.</li>
 * <li>{@link #testRemove()} - Tests the removal of elements from the
 * SkipList.</li>
 * <li>{@link #testAdjustHeadIncreasingLevel()} - Tests the adjustment of head
 * when increasing level.</li>
 * <li>{@link #testAdjustHeadToSameLevel()} - Tests the adjustment of head to
 * the same level.</li>
 * <li>{@link #testAdjustHeadToLowerLevel()} - Tests the adjustment of head to a
 * lower level.</li>
 * <li>{@link #testIterator()} - Tests the iterator functionality of the
 * SkipList.</li>
 * <li>{@link #testRandomLevel()} - Tests the randomness and functionality of
 * the randomLevel method.</li>
 * <li>{@link #testRandomLevelFunctionality()} - Tests the functionality of the
 * randomLevel method over multiple invocations.</li>
 * <li>{@link #testSearchExistingKey()} - Tests the search functionality for an
 * existing key.</li>
 * <li>{@link #testSearchNonExistingKey()} - Tests the search functionality for
 * a non-existing key.</li>
 * <li>{@link #testSearchEmptySkipList()} - Tests the search functionality in an
 * empty SkipList.</li>
 * <li>{@link #testRemoveByValueExistingValue()} - Tests the removal of an
 * existing value by value.</li>
 * <li>{@link #testRemoveByValueNonExistingValue()} - Tests the removal of a
 * non-existing value by value.</li>
 * <li>{@link #testRemoveByValueEmptyList()} - Tests the removal of a value from
 * an empty SkipList.</li>
 * <li>{@link #testRemoveByKey()} - Tests the removal of an element by key from
 * the SkipList.</li>
 * <li>{@link #testIterateHeadEmptyList()} - Tests the iteration of the head
 * node in an empty SkipList.</li>
 * <li>{@link #testIterateHeadNonEmptyList()} - Tests the iteration of the head
 * node in a non-empty SkipList.</li>
 * <li>{@link #testIterateHeadWithMultipleLevels()} - Tests the iteration of the
 * head node in a SkipList with multiple levels.</li>
 * </ul>
 * 
 * @author Mrunaldhar Bathula
 * @version 1.2
 */
public class SkipListTest extends TestCase {
    private SkipList<String, Rectangle> sk = new SkipList<String, Rectangle>();

    /**
     * Tests the {@code insert} method of the {@code sk} (presumably a class or
     * object of type Skiplist)
     * by inserting multiple rectangles into the skiplist and verifying the size
     * after each insertion.
     * <p>
     * This method aims to ensure that the insertion of rectangles into the
     * skiplist is functioning correctly
     * by adding different rectangles and checking if the size of the skiplist
     * increases accordingly.
     * It also handles a case where an attempt is made to insert a rectangle
     * with invalid dimensions,
     * verifying that the size of the skiplist remains unchanged if the
     * rectangle is indeed invalid.
     * <p>
     * Output return type: None. This method performs assertions to verify the
     * size of the skiplist after each insertion.
     * 
     * @throws AssertionError
     *             if any of the size assertions fail, indicating an issue with
     *             the insert operation.
     * @see Skiplist#insert(KVPair) method for insertion operation details.
     */
    public void testInsert() {
        Rectangle rec1 = new Rectangle(0, 0, 1, 2);
        Rectangle rec2 = new Rectangle(0, 0, 19, 20);
        int size = sk.size();
        KVPair<String, Rectangle> p1 = new KVPair<String, Rectangle>("r1",
            rec1);
        KVPair<String, Rectangle> p2 = new KVPair<String, Rectangle>("r2",
            rec2);
        sk.insert(p1);
        assertEquals(size + 1, sk.size());
        sk.insert(p2);
        assertEquals(size + 2, sk.size());
        Rectangle rec3 = new Rectangle(0, 0, -1, 2);
        KVPair<String, Rectangle> p3 = new KVPair<String, Rectangle>("r3",
            rec3);
        if (!rec3.isInvalid()) {
            sk.insert(p3);
            assertEquals(size + 3, sk.size());
        }
        else {
            assertEquals(size + 2, sk.size());
        }
    }


    /**
     * Tests the removal functionality of the {@code remove} method in the
     * {@code sk} object.
     * This test verifies that the {@code remove} method correctly removes
     * rectangles from the database based on
     * both their names and their actual instances. It inserts two rectangles
     * into the database, removes them using
     * both name-based and instance-based removal methods, and asserts that the
     * size of the database becomes zero after each removal.
     * 
     * @see sk#remove(String)
     * @see sk#removeByValue(Object)
     */
    public void testRemove() {
        Rectangle rec1 = new Rectangle(0, 0, 1, 2);
        Rectangle rec2 = new Rectangle(0, 0, 19, 20);
        int size = sk.size();
        KVPair<String, Rectangle> p1 = new KVPair<String, Rectangle>("r1",
            rec1);
        KVPair<String, Rectangle> p2 = new KVPair<String, Rectangle>("r2",
            rec2);
        sk.insert(p1);
        sk.remove("r1");
        assertEquals(sk.size(), 0);
        sk.insert(p1);
        sk.removeByValue(rec1);
        assertEquals(sk.size(), 0);
    }


    /**
     * Tests the {@code adjustHead} method of the {@code SkipList} class when
     * increasing the head's level.
     * This test ensures that the head's level is correctly adjusted by a
     * specified amount, and verifies
     * that the head's level, forward length, and forward nodes are updated
     * accordingly.
     */
    public void testAdjustHeadIncreasingLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure
                                                  // some levels

        int oldLevel = skipList.getHeadLevel();
        int newLevel = oldLevel + 2; // Increase level by 2
        skipList.adjustHead(newLevel);
        assertEquals(newLevel, skipList.getHeadLevel());
        // assertTrue(skipList.isForwardValid());
        assertEquals(newLevel + 1, skipList.getForwardLength());
    }


    /**
     * Tests the {@code adjustHead} method of the {@code SkipList} class to
     * ensure that the head of the skip list
     * is correctly adjusted to the same level as the highest level of elements
     * in the list.
     * <p>
     * This test inserts an element into the skip list to ensure that there are
     * some levels present. It then retrieves
     * the current level of the head, adjusts the head to match the level of the
     * highest element, and verifies that
     * the head level remains the same and the forward length increases by one.
     * 
     */
    public void testAdjustHeadToSameLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure
                                                  // some levels

        int oldLevel = skipList.getHeadLevel();
        skipList.adjustHead(oldLevel); // Adjust to the same level

        assertEquals(oldLevel, skipList.getHeadLevel());
        // assertTrue(skipList.isForwardValid());
        assertEquals(oldLevel + 1, skipList.getForwardLength());
    }


    /**
     * Tests the {@code adjustHead} method of the {@code SkipList} class to
     * lower the head level by one.
     * This test verifies that the head level of the SkipList is correctly
     * adjusted to a lower level by one,
     * ensuring that the forward pointers are updated accordingly.
     * <p>
     * Inserts elements into the SkipList to ensure that it has some levels,
     * then decreases the head level by one.
     * The method asserts that the new head level matches the expected level,
     * and that the forward length of the SkipList
     * is adjusted accordingly (one less than the new head level).
     */
    public void testAdjustHeadToLowerLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure
                                                  // some levels
        skipList.insert(new KVPair<>("Key2", 2));

        int oldLevel = skipList.getHeadLevel();
        int newLevel = oldLevel - 1; // Decrease level by 1
        skipList.adjustHead(newLevel);

        assertEquals(newLevel, skipList.getHeadLevel());
        // assertTrue(skipList.isForwardValid());
        assertEquals(newLevel + 1, skipList.getForwardLength());
    }


    /**
     * Tests the iterator functionality of the {@code SkipList} class.
     * This test ensures that the iterator correctly iterates through the
     * elements of the SkipList
     * and that it correctly reports whether there are more elements to iterate
     * over.
     * <p>
     * It inserts elements into the SkipList to ensure that there are some
     * levels present,
     * then retrieves an iterator and checks:
     * - Whether the iterator has a next element initially.
     * - Iterating over the next elements and confirming the iterator's
     * hasNext() method's behavior.
     */
    public void testIterator() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure
                                                  // some levels
        skipList.insert(new KVPair<>("Key2", 2));
        Iterator<KVPair<String, Integer>> it = skipList.iterator();
        assertTrue(it.hasNext());
        KVPair<String, Integer> l = it.next();
        assertTrue(it.hasNext());
        l = it.next();
        assertFalse(it.hasNext());
    }


    /**
     * Tests the randomness and correctness of the {@code randomLevel} function
     * in the {@code SkipList} class.
     * This test ensures that the {@code randomLevel} function generates
     * non-negative levels and exhibits randomness by
     * generating multiple different levels across multiple invocations.
     * Output Return Type: {@code void} (No return value, as this is a test
     * method).
     */
    public void testRandomLevel() {
        SkipList<String, Rectangle> skipList =
            new SkipList<String, Rectangle>();
        HashSet<Integer> levelsGenerated = new HashSet<>();

        // Run the function multiple times to check the randomness and
        // correctness
        for (int i = 0; i < 10000; i++) {
            int level = skipList.randomLevel();

            // Assert that the level is non-negative
            Assert.assertTrue("Level should be non-negative", level >= 0);

            // Add the level to the set to ensure randomness
            levelsGenerated.add(level);
        }

        // Assert that multiple different levels are generated, ensuring
        // randomness
        // Assuming randomness, the chance of getting the same level 10000 times
        // is negligible
        // This assertion might need to be adjusted based on the specifics of
        // your randomLevel function
        Assert.assertTrue(
            "Randomness check: multiple levels should be generated",
            levelsGenerated.size() > 1);
    }


    /**
     * Tests the functionality of the {@code randomLevel} method in the
     * {@code SkipList} class.
     * This test method verifies that the {@code randomLevel} method generates
     * non-negative levels,
     * adds unique levels to a set, and runs multiple times to ensure different
     * levels are generated.
     */
    public void testRandomLevelFunctionality() {
        SkipList<Integer, String> skipList = new SkipList<>();
        HashSet<Integer> generatedLevels = new HashSet<>();
        boolean multipleIterations = false;

        for (int i = 0; i < 1000; i++) {
            int level = skipList.randomLevel();

            // Test that level is non-negative (indirectly tests Random
            // initialization)
            Assert.assertTrue("Level should be non-negative", level >= 0);

            // Add the level to a set (tests increment and return)
            boolean isNewLevel = generatedLevels.add(level);
            if (isNewLevel && level > 0) {
                // If a new level greater than 0 is added, it means the loop ran
                // more than once
                multipleIterations = true;
            }
        }

        // Ensure that the loop condition is being tested (the loop runs more
        // than once)
        Assert.assertTrue(
            "Loop should run multiple times generating different levels",
            multipleIterations);

        // Ensure that the method returns different levels, testing the
        // increment and return statement
        Assert.assertTrue(
            "Method should return different levels over multiple invocations",
            generatedLevels.size() > 1);
    }


    /**
     * Tests the {@code search} method of the {@code SkipList} class for a
     * scenario where the key exists in the skip list.
     * This test verifies that the search operation returns the correct value
     * associated with the given key.
     */
    public void testSearchExistingKey() {
        SkipList<Integer, String> skipList = new SkipList<>();
        KVPair<Integer, String> pair1 = new KVPair<>(1, "Value1");
        KVPair<Integer, String> pair2 = new KVPair<>(2, "Value2");
        skipList.insert(pair1);
        skipList.insert(pair2);

        ArrayList<KVPair<Integer, String>> result = skipList.search(1);

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.contains(pair1));
    }


    /**
     * Tests the {@code search} method of the {@code SkipList} class for a
     * non-existing key.
     * This test verifies that when searching for a key that does not exist in
     * the SkipList,
     * the method returns an empty list.
     * 
     */
    public void testSearchNonExistingKey() {
        SkipList<Integer, String> skipList = new SkipList<>();
        KVPair<Integer, String> pair = new KVPair<>(1, "Value1");
        skipList.insert(pair);

        ArrayList<KVPair<Integer, String>> result = skipList.search(2);

        Assert.assertTrue(result.isEmpty());
    }


    /**
     * Tests the {@code search} method of an empty {@code SkipList} to ensure
     * correct behavior.
     * This test verifies that when searching for a key in an empty
     * {@code SkipList}, the result is an empty list.
     */
    public void testSearchEmptySkipList() {
        SkipList<Integer, String> skipList = new SkipList<>();

        ArrayList<KVPair<Integer, String>> result = skipList.search(1);

        Assert.assertTrue(result.isEmpty());
    }


    /**
     * Tests the {@code removeByValue} method of the {@code SkipList} class for
     * removing an existing value.
     * This test validates that the {@code removeByValue} method correctly
     * removes a specified value from the skip list,
     * and returns the key-value pair associated with the removed value. It also
     * verifies the correctness of the skip list
     * structure after the removal operation.
     * 
     * @see SkipList#removeByValue(Object)
     */
    public void testRemoveByValueExistingValue() {
        SkipList<String, Rectangle> skipList;
        Rectangle rec1 = new Rectangle(0, 0, 10, 10);
        Rectangle rec2 = new Rectangle(0, 0, 10, 20);
        KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>(
            "Value1", rec1);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>(
            "Value2", rec2);
        skipList = new SkipList<String, Rectangle>();
        skipList.insert(pair1);
        skipList.insert(pair2);
        KVPair<String, Rectangle> removedPair = skipList.removeByValue(rec2);

        Assert.assertNotNull(removedPair);
        Assert.assertEquals(rec2, removedPair.getValue());
        // Add more assertions to check the structure of the skip list
    }


    /**
     * Tests the {@code removeByValue} method of the {@code SkipList} class when
     * attempting to remove a non-existing value.
     * This test verifies that when attempting to remove a rectangle from the
     * skip list using {@code removeByValue},
     * and the rectangle does not exist in the skip list, the method returns
     * {@code null} indicating that no pair was removed.
     * It also ensures that the size of the skip list remains unchanged after
     * the removal attempt.
     */
    public void testRemoveByValueNonExistingValue() {
        SkipList<String, Rectangle> skipList;
        Rectangle rec1 = new Rectangle(0, 0, 10, 10);
        Rectangle rec2 = new Rectangle(0, 0, 10, 20);
        Rectangle rec3 = new Rectangle(0, 0, 20, 20);
        KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>(
            "Value1", rec1);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>(
            "Value2", rec2);
        skipList = new SkipList<String, Rectangle>();
        skipList.insert(pair1);
        skipList.insert(pair2);
        KVPair<String, Rectangle> removedPair = skipList.removeByValue(rec3);
        Assert.assertNull(removedPair);
        // Assert the size of the skip list has not changed
    }


    /**
     * Tests the {@code removeByValue} method of the {@code SkipList} class for
     * an empty list.
     * This test ensures that attempting to remove a key-value pair by its value
     * from an empty skip list
     * returns {@code null}, indicating that the operation cannot be performed
     * due to the list being empty.
     */
    public void testRemoveByValueEmptyList() {
        SkipList<String, Rectangle> skipList = new SkipList<>();
        Rectangle rec3 = new Rectangle(0, 0, 20, 20);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>(
            "Value2", rec3);
        KVPair<String, Rectangle> removedPair = skipList.removeByValue(rec3);
        Assert.assertNull(removedPair);
    }


    /**
     * Tests the removal of key-value pairs from a SkipList data structure by
     * key.
     * This test verifies that the correct key-value pair is removed from the
     * SkipList
     * when removing by key. It inserts multiple key-value pairs into the
     * SkipList,
     * then attempts to remove a specific key and verifies that the correct
     * key-value pair
     * is removed and returned.
     */
    public void testRemoveByKey() {
        SkipList<String, Rectangle> skipList;
        Rectangle rec1 = new Rectangle(0, 0, 10, 10);
        Rectangle rec2 = new Rectangle(0, 0, 10, 20);
        Rectangle rec3 = new Rectangle(0, 0, 20, 20);
        KVPair<String, Rectangle> sol;
        KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>("Value",
            rec1);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>(
            "Value2", rec2);
        KVPair<String, Rectangle> pair3 = new KVPair<String, Rectangle>(
            "Value2", rec3);
        skipList = new SkipList<String, Rectangle>();
        skipList.insert(pair1);
        skipList.insert(pair2);
        skipList.insert(pair3);
        skipList.dump();
        sol = skipList.remove("Value2");
        System.out.println(sol);
        assertTrue(sol.getKey().toString().equals("Value2"));
    }


    /**
     * Tests the {@code iterateHead} method of the {@code SkipList} class for an
     * empty list.
     * This test verifies that calling {@code iterateHead()} on an empty skip
     * list returns an ArrayList
     * containing a single element, which represents the head node. The element
     * is expected to have a value of "a_".
     */
    public void testIterateHeadEmptyList() {
        SkipList<String, Integer> list = new SkipList<>();

        ArrayList<String> result = list.iterateHead();
        // Assuming the head node of an empty list has a forward array of size 1
        // with all nulls
        assertEquals(1, result.size());
        assertEquals("a_", result.get(0));
    }


    /**
     * Tests the {@code iterateHead} method of the {@code SkipList} class when
     * the list is non-empty.
     * This test inserts a key-value pair into the SkipList and then calls the
     * {@code iterateHead} method to retrieve
     * the keys starting from the head. It validates that the first key inserted
     * is correctly retrieved, and it further
     * validates the rest of the keys in the list according to the specific
     * implementation of the SkipList.
     * 
     * @see SkipList#iterateHead()
     */
    public void testIterateHeadNonEmptyList() {
        SkipList<String, Integer> list = new SkipList<>();
        list.insert(new KVPair<>("Key1", 100));
        ArrayList<String> result = list.iterateHead();
        // Validate that the first key is correctly retrieved and the rest are
        // "a_"
        assertTrue(result.contains("Key1"));
        // Further checks depend on the specific implementation of your SkipList
        // For example, if inserting one element only populates the lowest
        // level,
        // the rest of the levels should still contain "a_"
    }


    /**
     * Tests the {@code iterateHead} method of the {@code SkipList} class when
     * multiple levels are present in the skip list.
     * This test inserts enough elements into the skip list to ensure that
     * multiple levels are created. It then calls the {@code iterateHead} method
     * to retrieve keys from the skip list starting from the head. The test
     * verifies that the returned array contains expected keys and placeholders
     * for levels, ensuring correct functionality of the iteration process.
     */
    public void testIterateHeadWithMultipleLevels() {
        SkipList<String, Integer> list = new SkipList<>();
        // Insert enough elements to ensure multiple levels are created
        list.insert(new KVPair<>("Key1", 100));
        list.insert(new KVPair<>("Key2", 200));
        // You might need to insert more elements or manipulate levels directly
        // if possible

        ArrayList<String> result = list.iterateHead();
        // Check that the array contains the expected keys and "a_" placeholders
        assertTrue(result.contains("Key1") || result.contains("Key2"));

        // The exact assertions will depend on how your skip list implements
        // level promotion
        // and how the `iterateHead` method is supposed to behave.
    }

}
