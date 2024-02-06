import static org.junit.Assert.assertEquals;

public class SkipListTest {
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
}
