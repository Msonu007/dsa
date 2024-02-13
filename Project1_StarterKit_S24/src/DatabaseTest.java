import org.junit.*;
import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DatabaseTest extends TestCase {

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outContent));
    }


    @After
    public void restoreStreams() {
        // Reset System.out to its original state
        System.setOut(originalOut);
    }


    @Test
    public void testSearchNoRectanglesFound() {
        Database db = new Database();
        String name = "nonExistentRect";

        db.search(name);

        String expectedOutput = "Rectangle not found: (nonExistentRect)\n";
        Assert.assertEquals(expectedOutput, outContent.toString());
    }


    public void testSearchRectanglesFound() {
        Database db = new Database();
        String name = "existentRect";
        Rectangle rect = new Rectangle(1, 1, 10, 10); // Valid rectangle
        KVPair<String, Rectangle> pair = new KVPair<>(name, rect);
        db.insert(pair); // Insert a rectangle to ensure search finds something

        outContent.reset(); // Reset the output stream before the actual search
        db.search(name);

        String expectedOutputStart = "Rectangles found:";
        assertTrue(outContent.toString().startsWith(
            expectedOutputStart));
        // Assert.assertTrue(outContent.toString().contains("(1,1,10,10)"));
    }


    public void testRemoveExistingRectangle() {
        Database db = new Database();
        Rectangle rect = new Rectangle(1, 1, 10, 10); // Rectangle to be removed
        KVPair<String, Rectangle> pair = new KVPair<>("rect1", rect);
        db.insert(pair); // Insert the rectangle first

        outContent.reset(); // Reset the output stream before actual removal
        db.remove(1, 1, 10, 10); // Remove the rectangle

        String expectedOutputStart = "Rectangle removed";
        assertTrue(outContent.toString().contains(
            expectedOutputStart));
        assertFalse(outContent.toString().contains("Rectangle not removed") || outContent.toString().contains("Rectangle rejected"));
        db.insert(pair); // Insert the rectangle first

        outContent.reset(); // Reset the output stream before actual removal
        db.remove("rect1"); // Remove the rectangle
        assertTrue(outContent.toString().contains(
            expectedOutputStart));
        assertFalse(outContent.toString().contains("Rectangle not removed") || outContent.toString().contains("Rectangle rejected"));
    }


    public void testRemoveNonExistingRectangle() {
        Database db = new Database();
        db.remove(1, 1, 10, 10); // Attempt to remove a non-existing rectangle

        String expectedOutputStart = "Rectangle rejected";
        String expectedOutputInvalid = "Rectangle not removed";
        assertTrue(outContent.toString().startsWith(
            expectedOutputStart) || outContent.toString().startsWith(expectedOutputInvalid));
        assertFalse(outContent.toString().contains("Rectangle found"));
        outContent.reset();
        db.remove(-1, 1, 10, 10);
        assertTrue(outContent.toString().contains("Rectangle rejected")); 
    }
    public void testRegionSearchValidInput() {
        Database db = new Database();
        db.regionsearch(0, 0, 10, 10);
        assertTrue(outContent.toString().contains("Rectangles intersecting region"));
        // Further assert the correct rectangles are printed based on setup
    }

    public void testRegionSearchInvalidInput() {
        Database db = new Database();
        db.regionsearch(0, 0, -1, -1);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
    }

    public void testRegionSearchWithOverflow() {
        Database db = new Database();
        db.regionsearch(Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 1);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
    }

    public void testRegionSearchNoIntersectingRectangles() {
        Database db = new Database();
        db.regionsearch(1000, 1000, 10, 10);
        // Assuming no rectangles at this location
        assertTrue(outContent.toString().contains("Rectangles intersecting region"));
        assertFalse(outContent.toString().contains("),"));
        // The above assertion assumes that if no rectangles intersect, no details are printed
    }

    public void testRegionSearchTouchingEdges() {
        Database db = new Database();
        // Assuming there's a rectangle exactly at the edge of the search region
        db.regionsearch(10, 10, 5, 5);
        // Depending on whether touching is considered intersecting, adjust the assertion
        assertTrue(outContent.toString().contains("Rectangles intersecting region"));
        // Assert either presence or absence of rectangle details
    }
    
    public void testRegionSearchNearMaxInt() {
        Database db = new Database();
        db.regionsearch(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, 1, 1);
        assertTrue(outContent.toString().contains("Rectangles intersecting region"));
        // Assert based on expected behavior; this assumes such a rectangle could exist and be found.
    }

    public void testRegionSearchOverflowX() {
        Database db = new Database();
        db.regionsearch(Integer.MAX_VALUE, 10, 10, 10);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
        // This test checks if the method correctly rejects a search that would overflow on x coordinate.
    }

    public void testRegionSearchOverflowY() {
        Database db = new Database();
        db.regionsearch(10, Integer.MAX_VALUE, 10, 10);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
        // Similarly, this test checks for overflow on the y coordinate.
    }
    
}
