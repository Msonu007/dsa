import static org.junit.Assert.assertTrue;
import org.junit.Assert;
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
    public void testEqualsItself() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Assert.assertTrue(rect.equals(rect));
    }


    public void testEqualsAnotherRectangleWithSameDimensions() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 20);
        Assert.assertTrue(rect1.equals(rect2));
    }


    public void testNotEqualsNull() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Assert.assertFalse(rect.equals(null));
    }


    public void testNotEqualsDifferentClass() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Object otherObject = new Object();
        Assert.assertFalse(rect.equals(otherObject));
    }


    public void testNotEqualsRectangleWithDifferentDimensions() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 15, 15); // Different size
        Assert.assertFalse(rect1.equals(rect2));
    }
    
    public void testisPointInside() {
        Rectangle c = new Rectangle(10,10,20,20);
        assertFalse(c.isPointInside(10, 8));
        assertFalse(c.isPointInside(8, 10));
        assertFalse(c.isPointInside(30, 40));
        assertFalse(c.isPointInside(40, 30));
        assertTrue(c.isPointInside(10, 15));
        assertTrue(c.isPointInside(15, 10));
        assertFalse(c.isPointInside(210, 10));
        assertFalse(c.isPointInside(10, 210));
        Rectangle c1 = new Rectangle(10,10,5,5);
        assertTrue(c1.isPointInside(12,12));
        assertFalse(c1.isPointInside(15, 16));
    }
    
    public void testIntersect() {
        Rectangle a = new Rectangle(10,10,10,10);
        Rectangle b = new Rectangle(20,20,5,5);
        Rectangle c = new Rectangle(10,10,10,10);
        assertFalse(a.intersect(b));
        assertTrue(a.intersect(c));
        b = new Rectangle(-10,-10,15,10);
        assertFalse(a.intersect(b));
        b = new Rectangle(-10,10,15,10);
        assertFalse(a.intersect(b));
        b = new Rectangle(10,-10,15,10);
        assertFalse(a.intersect(b));
        assertTrue(a.intersect(a));
        b = new Rectangle(-10,-10,15,10);
        a = new Rectangle(4,-1,5,1);
        System.out.println(b.intersect(a));
        assertFalse(b.intersect(a));
        Rectangle rect1 = new Rectangle(0, 0, 10, 10);
        Rectangle rect2 = new Rectangle(20, 20, 10, 10);
        assertFalse(rect1.intersect(rect2));
        rect1 = new Rectangle(0, 0, 15, 15);
        rect2 = new Rectangle(10, 10, 10, 10);
        assertTrue(rect1.intersect(rect2));
        rect1 = new Rectangle(0, 0, 10, 10);
        rect2 = new Rectangle(0, 0, 10, 10);
        assertTrue(rect1.intersect(rect2));
        rect1 = new Rectangle(0, 0, 10, 10);
        rect2 = new Rectangle(10, 10, 10, 10);
        assertFalse(rect1.intersect(rect2));
        rect1 = new Rectangle(0, 0, 10, 10);
        rect2 = new Rectangle(10, 0, 10, 10);
        assertFalse(rect1.intersect(rect2));
        rect1 = new Rectangle(-10, -10, 20, 20);
        rect2 = new Rectangle(-5, -5, 15, 15);
        assertFalse(rect1.intersect(rect2));
        Rectangle rect = new Rectangle(1, 1, 10, 10);
        assertTrue(rect.intersect(rect));
        b = new Rectangle(-10,-10,-15,10);
        a = new Rectangle(4,-1,5,1);
        System.out.println(b.intersect(a));
        assertFalse(b.intersect(a));
        b = new Rectangle(-10,-10,15,-10);
        a = new Rectangle(-4,-1,5,1);
        System.out.println(b.intersect(a));
        assertFalse(b.intersect(a));
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
