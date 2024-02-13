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
        Assert.assertTrue(outContent.toString().startsWith(
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
        Assert.assertTrue(outContent.toString().startsWith(
            expectedOutputStart));
    }


    public void testRemoveNonExistingRectangle() {
        Database db = new Database();
        db.remove(1, 1, 10, 10); // Attempt to remove a non-existing rectangle

        String expectedOutputStart = "Rectangle rejected";
        String expectedOutputInvalid = "Rectangle not removed";
        Assert.assertTrue(outContent.toString().startsWith(
            expectedOutputStart) || outContent.toString().startsWith(expectedOutputInvalid));
    }

    // Additional test cases for other methods...
}
