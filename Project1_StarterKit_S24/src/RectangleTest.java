import static org.junit.Assert.assertTrue;
import student.TestCase;
import static org.junit.Assert.assertFalse;

public class RectangleTest extends TestCase  {
    public void testEqualsRect() {
        Rectangle new1 = new Rectangle(1,1,2,3);
        Rectangle new2 = new Rectangle(-1,1,2,3);
        assertFalse(new1.equals(new2));
        assertTrue(new1.equals(new1));
        Rectangle new3 = new Rectangle(1,1,2,3);
        Rectangle new4 = new Rectangle(1,-1,2,3);
        assertFalse(new3.equals(new4));
        assertTrue(new3.equals(new3));
        Rectangle new5 = new Rectangle(1,1,2,3);
        Rectangle new6 = new Rectangle(1,1,-2,3);
        assertFalse(new5.equals(new6));
        assertTrue(new5.equals(new5));
        Rectangle new7 = new Rectangle(1,1,2,3);
        Rectangle new8 = new Rectangle(1,1,2,-3);
        assertFalse(new7.equals(new8));
        assertTrue(new7.equals(new7));
    }
    
    
    public void testIsInvalid() {
        Rectangle new1 = new Rectangle(-1, 10, 10, 10);
        assertTrue(new1.isInvalid());
        Rectangle new2 = new Rectangle(0, 10, 10, 10);
        assertFalse(new2.isInvalid());
        Rectangle new3 = new Rectangle(10, 10, 10, 0);
        assertTrue(new3.isInvalid());
        Rectangle new4 = new Rectangle(10, 10, 10, 10);
        assertFalse(new4.isInvalid());
        Rectangle rec5 = new Rectangle(10, -1, 10, 10);
        assertTrue(rec5.isInvalid());
        Rectangle rec6 = new Rectangle(10, 0, 10, 10);
        assertFalse(rec6.isInvalid());
        Rectangle rec7 = new Rectangle(10, 1020, 10, 10);
        assertTrue(rec7.isInvalid());
        Rectangle rec8 = new Rectangle(10, 1014, 10, 10);
        assertFalse(rec8.isInvalid());
        Rectangle rec9 = new Rectangle(Integer.MAX_VALUE, 10, 10, 10);
        assertTrue(rec9.isInvalid());
        Rectangle rec10 = new Rectangle(10, Integer.MAX_VALUE, 10, 10);
        assertTrue(rec10.isInvalid());
        Rectangle rec11 = new Rectangle(10, 10, -1, 10);
        assertTrue(rec11.isInvalid());
        Rectangle rec12 = new Rectangle(10, 10, 10, -1);
        assertTrue(rec12.isInvalid());
        Rectangle rec13 = new Rectangle(10, 512, 10, 512);
        assertFalse(rec13.isInvalid());
        Rectangle rec14 = new Rectangle(512, 10, 512, 10);
        assertFalse(rec14.isInvalid());
        Rectangle rec15 = new Rectangle(10, 10, 2000, 10);
        assertTrue(rec15.isInvalid());
        Rectangle rectangle = new Rectangle(10, 10, 10, 2000);
        assertTrue(rectangle.isInvalid());
//        Rectangle new5 = new Rectangle(1,-1,-10024,3);
//        assertTrue(new5.isInvalid());
//        Rectangle new6 = new Rectangle(-1,-1,2,-10024);
//        assertTrue(new6.isInvalid());
//        Rectangle new7 = new Rectangle(1,1,1,1);
//        assertFalse(new7.isInvalid());
//        Rectangle new8 = new Rectangle(1,1,1024,1024);
//        assertTrue(new8.isInvalid());
        
    }
}
