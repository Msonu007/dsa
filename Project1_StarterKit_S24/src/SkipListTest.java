import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.Assert;
import student.TestCase;

public class SkipListTest extends TestCase {
    private SkipList<String, Rectangle> sk = new SkipList<String, Rectangle>();

    public void testInsert() {
        Rectangle rec1 = new Rectangle(0, 0, 1, 2);
        Rectangle rec2 = new Rectangle(0, 0, 19, 20);
        int size_ = sk.size();
        KVPair<String, Rectangle> p1 = new KVPair<String, Rectangle>("r1",
            rec1);
        KVPair<String, Rectangle> p2 = new KVPair<String, Rectangle>("r2",
            rec2);
        sk.insert(p1);
        assertEquals(size_ + 1, sk.size());
        sk.insert(p2);
        assertEquals(size_ + 2, sk.size());
        Rectangle rec3 = new Rectangle(0, 0, -1, 2);
        KVPair<String, Rectangle> p3 = new KVPair<String, Rectangle>("r3",
            rec3);
        if (!rec3.isInvalid()) {
            sk.insert(p3);
            assertEquals(size_ + 3, sk.size());
        }
        else {
            assertEquals(size_ + 2, sk.size());
        }
    }
    
    public void testRemove() {
        Rectangle rec1 = new Rectangle(0, 0, 1, 2);
        Rectangle rec2 = new Rectangle(0, 0, 19, 20);
        int size_ = sk.size();
        KVPair<String, Rectangle> p1 = new KVPair<String, Rectangle>("r1",
            rec1);
        KVPair<String, Rectangle> p2 = new KVPair<String, Rectangle>("r2",
            rec2);
        sk.insert(p1);
        sk.remove("r1");
        assertEquals(sk.size(),0);
        sk.insert(p1);
        sk.removeByValue(rec1);
        assertEquals(sk.size(),0);
    }
//    public void testAdjustHead() {
//        Rectangle rec1 = new Rectangle(0, 0, 1, 2);
//        Rectangle rec2 = new Rectangle(0, 0, 19, 20);
//        KVPair<String, Rectangle> p1 = new KVPair<String, Rectangle>("r1",
//            rec1);
//        KVPair<String, Rectangle> p2 = new KVPair<String, Rectangle>("r2",
//            rec2);
//        Rectangle rec3 = new Rectangle(0, 0, 1, 2);
//        Rectangle rec4 = new Rectangle(0, 0, 19, 20);
//        int size_ = sk.size();
//        KVPair<String, Rectangle> p3 = new KVPair<String, Rectangle>("r1",
//            rec3);
//        KVPair<String, Rectangle> p4 = new KVPair<String, Rectangle>("r2",
//            rec4);
//        sk.insert(p1);
//        assertEquals(size_ + 1, sk.size());
//        sk.insert(p2);
//        assertEquals(size_ + 2, sk.size());
//        sk.insert(p3);
//        sk.insert(p4);
//        sk.adjustHead(10);
//        ArrayList<String> s = sk.iterateHead();
//        String temp = s.get(0);
//        for (int i = 1;i<s.size();i++) {
//            if(s.get(i).compareTo("a")>0) {
//                assert(s.get(i).compareTo(temp)>=0);
//                temp = s.get(i);   
//            }
//        }
//      
//        
//        
//    }
    public void testAdjustHeadIncreasingLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure some levels
        
        int oldLevel = skipList.getHeadLevel();
        int newLevel = oldLevel + 2; // Increase level by 2
        skipList.adjustHead(newLevel);
        assertEquals(newLevel, skipList.getHeadLevel());
        //assertTrue(skipList.isForwardValid());
        assertEquals(newLevel + 1, skipList.getForwardLength());
    }

    public void testAdjustHeadToSameLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure some levels
        
        int oldLevel = skipList.getHeadLevel();
        skipList.adjustHead(oldLevel); // Adjust to the same level

        assertEquals(oldLevel, skipList.getHeadLevel());
        //assertTrue(skipList.isForwardValid());
        assertEquals(oldLevel + 1, skipList.getForwardLength());
    }

    public void testAdjustHeadToLowerLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure some levels
        skipList.insert(new KVPair<>("Key2", 2));
        
        int oldLevel = skipList.getHeadLevel();
        int newLevel = oldLevel - 1; // Decrease level by 1
        skipList.adjustHead(newLevel);

        assertEquals(newLevel, skipList.getHeadLevel());
        //assertTrue(skipList.isForwardValid());
        assertEquals(newLevel + 1, skipList.getForwardLength());
    }
    
    public void testIterator() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure some levels
        skipList.insert(new KVPair<>("Key2", 2));
        Iterator<KVPair<String, Integer>> it = skipList.iterator();
        assertTrue(it.hasNext());
        KVPair<String,Integer> l = it.next();
        assertTrue(it.hasNext());
        l = it.next();
        assertFalse(it.hasNext());
    }
    public void testRandomLevel() {
        SkipList<String, Rectangle> skipList = new SkipList<String,Rectangle>();
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


    public void testSearchNonExistingKey() {
        SkipList<Integer, String> skipList = new SkipList<>();
        KVPair<Integer, String> pair = new KVPair<>(1, "Value1");
        skipList.insert(pair);

        ArrayList<KVPair<Integer, String>> result = skipList.search(2);

        Assert.assertTrue(result.isEmpty());
    }


    public void testSearchEmptySkipList() {
        SkipList<Integer, String> skipList = new SkipList<>();

        ArrayList<KVPair<Integer, String>> result = skipList.search(1);

        Assert.assertTrue(result.isEmpty());
    }


    public void testRemoveByValueExistingValue() {
        SkipList<String, Rectangle> skipList;// initialize and add nodes
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


    public void testRemoveByValueNonExistingValue() {
        SkipList<String, Rectangle> skipList;// initialize and add nodes
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


    public void testRemoveByValueEmptyList() {
        SkipList<String, Rectangle> skipList = new SkipList<>();
        Rectangle rec3 = new Rectangle(0, 0, 20, 20);
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>(
            "Value2", rec3);
        KVPair<String, Rectangle> removedPair = skipList.removeByValue(rec3);
        Assert.assertNull(removedPair);
    }
    
    public void testRemoveByKey() {
        SkipList<String, Rectangle> skipList;// initialize and add nodes
        Rectangle rec1 = new Rectangle(0, 0, 10, 10);
        Rectangle rec2 = new Rectangle(0, 0, 10, 20);
        Rectangle rec3 = new Rectangle(0, 0, 20, 20);
        KVPair<String,Rectangle> sol;
        KVPair<String, Rectangle> pair1 = new KVPair<String, Rectangle>(
            "Value", rec1);
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
    
    public void testIterateHeadEmptyList() {
        SkipList<String, Integer> list = new SkipList<>();

        ArrayList<String> result = list.iterateHead();
        // Assuming the head node of an empty list has a forward array of size 1 with all nulls
        assertEquals(1, result.size());
        assertEquals("a_", result.get(0));
    }

    public void testIterateHeadNonEmptyList() {
        SkipList<String, Integer> list = new SkipList<>();
        list.insert(new KVPair<>("Key1", 100));
        ArrayList<String> result = list.iterateHead();
        // Validate that the first key is correctly retrieved and the rest are "a_"
        assertTrue(result.contains("Key1"));
        // Further checks depend on the specific implementation of your SkipList
        // For example, if inserting one element only populates the lowest level,
        // the rest of the levels should still contain "a_"
    }

    public void testIterateHeadWithMultipleLevels() {
        SkipList<String, Integer> list = new SkipList<>();
        // Insert enough elements to ensure multiple levels are created
        list.insert(new KVPair<>("Key1", 100));
        list.insert(new KVPair<>("Key2", 200));
        // You might need to insert more elements or manipulate levels directly if possible

        ArrayList<String> result = list.iterateHead();
        // Check that the array contains the expected keys and "a_" placeholders
        assertTrue(result.contains("Key1") || result.contains("Key2"));
        
        // The exact assertions will depend on how your skip list implements level promotion
        // and how the `iterateHead` method is supposed to behave.
    }

    
    


}
