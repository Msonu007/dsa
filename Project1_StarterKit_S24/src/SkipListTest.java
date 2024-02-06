import java.util.ArrayList;
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
    public void testAdjustHead() {
        Rectangle rec1 = new Rectangle(0, 0, 1, 2);
        Rectangle rec2 = new Rectangle(0, 0, 19, 20);
        KVPair<String, Rectangle> p1 = new KVPair<String, Rectangle>("r1",
            rec1);
        KVPair<String, Rectangle> p2 = new KVPair<String, Rectangle>("r2",
            rec2);
        Rectangle rec3 = new Rectangle(0, 0, 1, 2);
        Rectangle rec4 = new Rectangle(0, 0, 19, 20);
        int size_ = sk.size();
        KVPair<String, Rectangle> p3 = new KVPair<String, Rectangle>("r1",
            rec1);
        KVPair<String, Rectangle> p4 = new KVPair<String, Rectangle>("r2",
            rec2);
        sk.insert(p1);
        assertEquals(size_ + 1, sk.size());
        sk.insert(p2);
        assertEquals(size_ + 2, sk.size());
        sk.insert(p3);
        sk.insert(p4);
        sk.adjustHead(10);
        ArrayList<String> s = sk.iterateHead();
        String temp = s.get(0);
        for (int i = 1;i<s.size();i++) {
            if(s.get(i).compareTo("a")>0) {
                assert(s.get(i).compareTo(temp)>=0);
                temp = s.get(i);   
            }
        }
      
        
        
    }
    public void testAdjustHeadIncreasingLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure some levels
        
        int oldLevel = skipList.getHeadLevel();
        int newLevel = oldLevel + 2; // Increase level by 2
        skipList.adjustHead(newLevel);
        assertEquals(newLevel, skipList.getHeadLevel());
        assertTrue(skipList.isForwardValid());
        assertEquals(newLevel + 1, skipList.getForwardLength());
    }

    public void testAdjustHeadToSameLevel() {
        SkipList<String, Integer> skipList = new SkipList<>();
        skipList.insert(new KVPair<>("Key1", 1)); // Insert elements to ensure some levels
        
        int oldLevel = skipList.getHeadLevel();
        skipList.adjustHead(oldLevel); // Adjust to the same level

        assertEquals(oldLevel, skipList.getHeadLevel());
        assertTrue(skipList.isForwardValid());
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
        assertTrue(skipList.isForwardValid());
        assertEquals(newLevel + 1, skipList.getForwardLength());
    }
}
