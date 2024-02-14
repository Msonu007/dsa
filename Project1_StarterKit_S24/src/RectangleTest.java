import org.junit.Assert;
import student.TestCase;

/**
 * Unit test class for {@code Rectangle}. It verifies the correctness of the
 * {@code Rectangle} class's methods
 * including equality checks, validity of rectangle dimensions, containment of
 * points, intersection with other rectangles,
 * and various edge cases handling.
 * <p>
 * This class extensively tests the {@code Rectangle} class by creating
 * instances with different dimensions and positions,
 * and then performing operations such as:
 * <ul>
 * <li>Equality comparisons between rectangles, including comparisons with null
 * and objects of other classes.</li>
 * <li>Validation checks to determine if a rectangle's dimensions or coordinates
 * are invalid.</li>
 * <li>Containment checks to see if certain points lie within the bounds of a
 * rectangle.</li>
 * <li>Intersection tests to verify if two rectangles overlap or intersect.</li>
 * </ul>
 * Each test method within this class is designed to target specific scenarios,
 * ensuring comprehensive coverage
 * of the {@code Rectangle} class's functionality. The outcomes are asserted to
 * validate expected behavior against
 * the actual results produced by the {@code Rectangle} class methods.
 *
 * @author Mrunaldhar Bathula
 * @version 1.2
 */
public class RectangleTest extends TestCase {
    /**
     * Tests the equality of rectangles by comparing various rectangle instances
     * with different coordinates.
     * Verifies that a rectangle is only equal to itself and another rectangle
     * with exactly the same dimensions,
     * and not equal to rectangles with differing coordinates or dimensions.
     * <p>
     * This method systematically tests equality between rectangles by altering
     * one coordinate or dimension at a time
     * to assert the correct behavior of the {@code equals} method implemented
     * in the {@code Rectangle} class.
     * 
     * Input Variables:
     * - {@code Rectangle} instances with various combinations of coordinates
     * and dimensions.
     * 
     * Output/Return Type:
     * - This method does not return a value but asserts the expected true or
     * false conditions for rectangle equality.
     * 
     * @author Mrunaldhar Bathula
     */
    public void testEqualsRect() {
        Rectangle new1 = new Rectangle(1, 1, 2, 3);
        Rectangle new2 = new Rectangle(-1, 1, 2, 3);
        assertFalse(new1.equals(new2));
        assertTrue(new1.equals(new1));
        Rectangle new3 = new Rectangle(1, 1, 2, 3);
        Rectangle new4 = new Rectangle(1, -1, 2, 3);
        assertFalse(new3.equals(new4));
        assertTrue(new3.equals(new3));
        Rectangle new5 = new Rectangle(1, 1, 2, 3);
        Rectangle new6 = new Rectangle(1, 1, -2, 3);
        assertFalse(new5.equals(new6));
        assertTrue(new5.equals(new5));
        Rectangle new7 = new Rectangle(1, 1, 2, 3);
        Rectangle new8 = new Rectangle(1, 1, 2, -3);
        assertFalse(new7.equals(new8));
        assertTrue(new7.equals(new7));
    }


    /**
     * Tests the {@code isInvalid} method of the {@code Rectangle} class across
     * various scenarios to ensure it accurately identifies invalid rectangles.
     * This method creates multiple {@code Rectangle} instances with different
     * sets of dimensions (x, y, width, height) and asserts whether
     * the {@code isInvalid} method correctly identifies the rectangle as
     * invalid based on its dimensions. The criteria for invalidity include
     * negative dimensions, dimensions that exceed certain bounds, and
     * dimensions that result in the rectangle's area being effectively zero or
     * negative.
     * <p>
     * Input Variables:
     * - Multiple {@code Rectangle} instances with varying dimensions to cover a
     * broad range of valid and invalid cases.
     * <p>
     * Output Return Type:
     * - The method does not return a value but asserts the boolean result of
     * {@code Rectangle.isInvalid()} to verify correctness.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
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
        Rectangle new5 = new Rectangle(1, -1, -10024, 3);
        assertTrue(new5.isInvalid());
        Rectangle new6 = new Rectangle(-1, -1, 2, -10024);
        assertTrue(new6.isInvalid());
        Rectangle new7 = new Rectangle(1, 1, 1, 1);
        assertFalse(new7.isInvalid());
        Rectangle new8 = new Rectangle(1, 1, 1024, 1024);
        assertTrue(new8.isInvalid());

    }


    /**
     * Tests the {@code equals} method of the {@code Rectangle} class to verify
     * that a rectangle is equal to itself.
     * This test checks the reflexivity property of the {@code equals} method,
     * which states that an object must be equal to itself.
     * <p>
     * - The test returns a boolean result through {@code Assert.assertTrue},
     * which does not return a value but asserts that
     * the condition (the rectangle is equal to itself) is true.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testEqualsItself() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Assert.assertTrue(rect.equals(rect));
    }


    /**
     * Tests the {@code equals} method of the {@code Rectangle} class to verify
     * if two rectangles with identical dimensions are considered equal.
     * This test creates two {@code Rectangle} objects with the same dimensions
     * and uses the {@code equals} method to check if they are considered
     * equivalent.
     * <p>
     * - The test asserts a {@code boolean} value, expecting {@code true} to
     * confirm that {@code rect1} is equal to {@code rect2} based on their
     * dimensions.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testEqualsAnotherRectangleWithSameDimensions() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 20, 20);
        Assert.assertTrue(rect1.equals(rect2));
    }


    /**
     * Tests the equality check of the {@code Rectangle} class to ensure it
     * correctly identifies that a {@code Rectangle} object is not equal to
     * {@code null}.
     * This test verifies the {@code equals} method's compliance with the
     * equality contract, specifically that any object is not equal to
     * {@code null}.
     * <p>
     * - Asserts {@code false} for the equality check between the
     * {@code Rectangle} object and {@code null}, indicating successful
     * inequality recognition.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testNotEqualsNull() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Assert.assertFalse(rect.equals(null));
    }


    /**
     * Tests the {@code equals} method of the {@code Rectangle} class to ensure
     * it correctly identifies objects of a different class as not equal.
     * This test creates a {@code Rectangle} object and compares it to a generic
     * {@code Object} instance, expecting the comparison to return false,
     * thereby asserting the type-safety and specificity of the {@code equals}
     * method within the {@code Rectangle} class.
     * <p>
     * - The test asserts a boolean result, specifically {@code false},
     * indicating that the {@code Rectangle} and the generic {@code Object} are
     * not equal.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testNotEqualsDifferentClass() {
        Rectangle rect = new Rectangle(10, 10, 20, 20);
        Object otherObject = new Object();
        Assert.assertFalse(rect.equals(otherObject));
    }


    /**
     * Tests the equality of two {@code Rectangle} objects with different
     * dimensions to verify that the {@code equals} method
     * correctly identifies them as not equal. This test ensures that the
     * {@code Rectangle} class's {@code equals} method
     * takes into account the dimensions of the rectangles for comparison.
     * <p>
     * - Asserts that {@code rect1.equals(rect2)} returns {@code false},
     * indicating the rectangles are correctly identified as not equal.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testNotEqualsRectangleWithDifferentDimensions() {
        Rectangle rect1 = new Rectangle(10, 10, 20, 20);
        Rectangle rect2 = new Rectangle(10, 10, 15, 15); // Different size
        Assert.assertFalse(rect1.equals(rect2));
    }


    /**
     * Tests the {@code isPointInside} method of the {@code Rectangle} class to
     * verify if given points are correctly identified as being inside or
     * outside the rectangle.
     * This method creates rectangles and checks multiple points against each
     * rectangle to assert whether the method accurately determines the points'
     * positions relative to the rectangle boundaries.
     * <p>
     * - Does not return a value but asserts the expected boolean outcome (true
     * if the point is inside the rectangle, false otherwise) for each point
     * tested.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testisPointInside() {
        Rectangle c = new Rectangle(10, 10, 20, 20);
        assertFalse(c.isPointInside(10, 8));
        assertFalse(c.isPointInside(8, 10));
        assertFalse(c.isPointInside(30, 40));
        assertFalse(c.isPointInside(40, 30));
        assertTrue(c.isPointInside(10, 15));
        assertTrue(c.isPointInside(15, 10));
        assertFalse(c.isPointInside(210, 10));
        assertFalse(c.isPointInside(10, 210));
        Rectangle c1 = new Rectangle(10, 10, 5, 5);
        assertTrue(c1.isPointInside(12, 12));
        assertFalse(c1.isPointInside(15, 16));
    }


    /**
     * Tests the {@code intersect} method of the {@code Rectangle} class across
     * various scenarios to ensure accurate detection of rectangle
     * intersections.
     * This method assesses the correctness of intersection logic by creating
     * pairs of rectangles with different positions and sizes,
     * and asserting whether they should intersect based on their geometric
     * properties.
     * <p>
     * It covers cases including, but not limited to, rectangles that do not
     * overlap, rectangles that completely overlap,
     * and rectangles that only partially overlap, along with edge cases like
     * rectangles with negative dimensions.
     * - This method does not return a value but asserts the expected true or
     * false outcomes for each intersection test case.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testIntersect() {
        Rectangle a = new Rectangle(10, 10, 10, 10);
        Rectangle b = new Rectangle(20, 20, 5, 5);
        Rectangle c = new Rectangle(10, 10, 10, 10);
        assertFalse(a.intersect(b));
        assertTrue(a.intersect(c));
        b = new Rectangle(-10, -10, 15, 10);
        assertFalse(a.intersect(b));
        b = new Rectangle(-10, 10, 15, 10);
        assertFalse(a.intersect(b));
        b = new Rectangle(10, -10, 15, 10);
        assertFalse(a.intersect(b));
        assertTrue(a.intersect(a));
        b = new Rectangle(-10, -10, 15, 10);
        a = new Rectangle(4, -1, 5, 1);
        System.out.println(b.intersect(a));
        // assertFalse(b.intersect(a));
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
        rect1 = new Rectangle(0, 0, -1, -1);
        rect2 = new Rectangle(0, 0, -1, -1);
        assertTrue(rect1.intersect(rect2));
        Rectangle rect = new Rectangle(1, 1, 10, 10);
        assertTrue(rect.intersect(rect));
        b = new Rectangle(-10, -10, -15, 10);
        a = new Rectangle(4, -1, 5, 1);
        System.out.println(b.intersect(a));
        assertFalse(b.intersect(a));
        b = new Rectangle(-10, -10, 15, -10);
        a = new Rectangle(-4, -1, 5, 1);
        System.out.println(b.intersect(a));
        assertFalse(b.intersect(a));

    }

}
