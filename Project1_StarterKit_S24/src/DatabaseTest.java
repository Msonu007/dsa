import org.junit.*;
import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * The {@code DatabaseTest} class contains unit tests for the {@code Database}
 * class, focusing on testing the functionality
 * of searching, inserting, and removing rectangles. It verifies the correctness
 * of these operations by capturing and
 * asserting the expected output against the actual output printed to the
 * console.
 * <p>
 * It includes tests for:
 * <ul>
 * <li>Searching for existing and non-existing rectangles by name.</li>
 * <li>Inserting and removing rectangles, both existing and non-existing, by
 * coordinates and name.</li>
 * <li>Performing region searches with valid and invalid inputs, including edge
 * cases like overflow conditions.</li>
 * </ul>
 * The output is redirected and captured from {@code System.out} to facilitate
 * assertions on the operations' results.
 * 
 * @author Mrunaldhar Bathula
 * @version 1.2
 */
public class DatabaseTest extends TestCase {

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Initializes and redirects the standard output stream to capture console
     * output for testing purposes.
     * This method is designed to be called before each test execution to set up
     * a custom output stream
     * that captures all output data usually printed to the console via
     * {@code System.out}.
     * <p>
     * This setup is essential for verifying the output of test cases that
     * inspect printed messages
     * and results to ensure the correct functionality of the tested methods.
     * <p>
     * Inputs: None.
     * Outputs: Redirects {@code System.out} to a {@code ByteArrayOutputStream}
     * to capture all console output.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    @Before
    public void setUpStreams() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outContent));
    }


    /**
     * Restores the standard output stream to its original state after testing.
     * This method is called after each test execution to revert the changes
     * made by {@code setUpStreams()},
     * ensuring that {@code System.out} is reset to its default output stream.
     * <p>
     * It is crucial for maintaining the integrity of the test environment by
     * ensuring that subsequent tests
     * are not affected by the redirection of the output stream done by previous
     * tests.
     * 
     * Inputs: None.
     * Outputs: Resets {@code System.out} to the standard output stream stored
     * in {@code originalOut}.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    @After
    public void restoreStreams() {
        // Reset System.out to its original state
        System.setOut(originalOut);
    }


    /**
     * Tests the {@code search} method of the {@code Database} class for a
     * scenario where the rectangle does not exist.
     * This test verifies that the correct message is output when searching for
     * a rectangle name that is not present in the database.
     *
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    @Test
    public void testSearchNoRectanglesFound() {
        Database db = new Database();
        String name = "nonExistentRect";

        db.search(name);

        String expectedOutput = "Rectangle not found: (nonExistentRect)\n";
        Assert.assertEquals(expectedOutput, outContent.toString());
    }


    /**
     * Tests the {@code search} method of the {@code Database} class for a
     * scenario where the rectangle exists.
     * This test ensures that the search operation successfully finds a
     * rectangle by name and correctly prints a message indicating the
     * rectangles found.
     * <p>
     * The method first inserts a rectangle with a known name into the database.
     * It then searches for this rectangle by name,
     * and verifies that the output starts with the expected message indicating
     * that one or more rectangles have been found.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testSearchRectanglesFound() {
        Database db = new Database();
        String name = "existentRect";
        Rectangle rect = new Rectangle(1, 1, 10, 10); // Valid rectangle
        KVPair<String, Rectangle> pair = new KVPair<>(name, rect);
        db.insert(pair); // Insert a rectangle to ensure search finds something

        outContent.reset(); // Reset the output stream before the actual search
        db.search(name);

        String expectedOutputStart = "Rectangles found:";
        assertTrue(outContent.toString().startsWith(expectedOutputStart));
    }


    /**
     * Tests the removal functionality of the {@code Database} class for an
     * existing rectangle, both by coordinates and by name.
     * This test first inserts a rectangle into the database and then attempts
     * to remove it twice: first by specifying its coordinates,
     * and then by using its name. It verifies that the removal is successful in
     * both cases by checking the output messages.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRemoveExistingRectangle() {
        Database db = new Database();
        Rectangle rect = new Rectangle(1, 1, 10, 10); // Rectangle to be removed
        KVPair<String, Rectangle> pair = new KVPair<>("rect1", rect);
        db.insert(pair); // Insert the rectangle first

        outContent.reset(); // Reset the output stream before actual removal
        db.remove(1, 1, 10, 10); // Remove the rectangle

        String expectedOutputStart = "Rectangle removed";
        assertTrue(outContent.toString().contains(expectedOutputStart));
        assertFalse(outContent.toString().contains("Rectangle not removed")
            || outContent.toString().contains("Rectangle rejected"));
        db.insert(pair); // Insert the rectangle first

        outContent.reset(); // Reset the output stream before actual removal
        db.remove("rect1"); // Remove the rectangle
        assertTrue(outContent.toString().contains(expectedOutputStart));
        assertFalse(outContent.toString().contains("Rectangle not removed")
            || outContent.toString().contains("Rectangle rejected"));
    }


    /**
     * Tests the {@code remove} functionality of the {@code Database} class for
     * non-existing rectangles,
     * verifying appropriate failure messages are displayed. This test performs
     * removal operations for rectangles
     * that do not exist within the database, both with valid and invalid
     * coordinates, to ensure the database
     * correctly handles these cases by rejecting the removal requests.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRemoveNonExistingRectangle() {
        Database db = new Database();
        db.remove(1, 1, 10, 10); // Attempt to remove a non-existing rectangle

        String expectedOutputStart = "Rectangle rejected";
        String expectedOutputInvalid = "Rectangle not removed";
        assertTrue(outContent.toString().startsWith(expectedOutputStart)
            || outContent.toString().startsWith(expectedOutputInvalid));
        assertFalse(outContent.toString().contains("Rectangle found"));
        outContent.reset();
        db.remove(-1, 1, 10, 10);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
    }


    /**
     * Tests the {@code regionsearch} method of the {@code Database} class with
     * valid input coordinates,
     * ensuring it correctly identifies and reports rectangles intersecting with
     * the specified region.
     * This method verifies that the database responds appropriately when a
     * region search is conducted
     * with a valid set of coordinates, expecting an output that indicates the
     * presence of intersecting rectangles.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchValidInput() {
        Database db = new Database();
        db.regionsearch(0, 0, 10, 10);
        assertTrue(outContent.toString().contains(
            "Rectangles intersecting region"));
        // Further assert the correct rectangles are printed based on setup
    }


    /**
     * Tests the {@code regionsearch} functionality of the {@code Database}
     * class with invalid input parameters.
     * This method verifies that the database correctly rejects a region search
     * request when the dimensions of
     * the search region are invalid (e.g., negative width and height). It
     * checks that the appropriate error message
     * is output, indicating the operation was rejected due to invalid input.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchInvalidInput() {
        Database db = new Database();
        db.regionsearch(0, 0, -1, -1);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
    }


    /**
     * Tests the {@code regionsearch} method of the {@code Database} class for
     * handling coordinate overflow conditions.
     * This test checks if the database correctly rejects a region search
     * request when the search area's starting coordinates
     * and dimensions could potentially cause overflow in calculations (using
     * {@code Integer.MAX_VALUE} for coordinates).
     * Note: This method does not return a value but asserts the expected output
     * behavior through unit testing.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchWithOverflow() {
        Database db = new Database();
        db.regionsearch(Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 1);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
    }


    /**
     * Tests the {@code regionsearch} method of the {@code Database} class for a
     * specified region where no rectangles intersect.
     * This test ensures that when performing a region search in an area with no
     * intersecting rectangles, the method correctly
     * identifies the lack of intersections. The absence of intersecting
     * rectangles is verified by checking the output message
     * for the expected prefix and ensuring no rectangle details are printed.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchNoIntersectingRectangles() {
        Database db = new Database();
        db.regionsearch(1000, 1000, 10, 10);
        // Assuming no rectangles at this location
        assertTrue(outContent.toString().contains(
            "Rectangles intersecting region"));
        assertFalse(outContent.toString().contains("),"));
        // The above assertion assumes that if no rectangles intersect, no
        // details are printed
    }


    /**
     * Tests the {@code regionsearch} method of the {@code Database} class for
     * rectangles touching the edges of the search region.
     * This method aims to verify the behavior of the {@code regionsearch}
     * operation when a rectangle is exactly at the boundary
     * of the specified search area. The outcome depends on the implementation's
     * criteria for considering whether touching rectangles
     * are included as intersecting the search region.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchTouchingEdges() {
        Database db = new Database();
        // Assuming there's a rectangle exactly at the edge of the search region
        db.regionsearch(10, 10, 5, 5);
        // Depending on whether touching is considered intersecting, adjust the
        // assertion
        assertTrue(outContent.toString().contains(
            "Rectangles intersecting region"));
        // Assert either presence or absence of rectangle details
    }


    /**
     * Tests the {@code regionsearch} functionality of the {@code Database}
     * class with input parameters near the maximum integer value.
     * This method assesses the ability of the {@code regionsearch} operation to
     * handle edge case input values that are close to
     * {@link Integer#MAX_VALUE}, ensuring that the method can correctly process
     * searches for regions at extreme coordinates without error.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchNearMaxInt() {
        Database db = new Database();
        db.regionsearch(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, 1, 1);
        assertTrue(outContent.toString().contains(
            "Rectangles intersecting region"));
        // Assert based on expected behavior; this assumes such a rectangle
        // could exist and be found.
    }


    /**
     * Tests the {@code regionsearch} method of the {@code Database} class for
     * handling coordinate overflow conditions.
     * This test verifies that the database correctly rejects a region search
     * request when the starting x-coordinate
     * is set to {@code Integer.MAX_VALUE}, potentially causing an overflow
     * condition. The aim is to ensure robustness
     * in handling extreme input values without causing unexpected behavior.
     * Asserts that the console output contains "Rectangle rejected", indicating
     * the search request was appropriately rejected due to the potential for
     * overflow.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchOverflowX() {
        Database db = new Database();
        db.regionsearch(Integer.MAX_VALUE, 10, 10, 10);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
        // This test checks if the method correctly rejects a search that would
        // overflow on x coordinate.
    }


    /**
     * Tests the {@code regionsearch} method of the {@code Database} class for
     * handling Y-coordinate overflow.
     * This test checks whether the method properly rejects a region search
     * request when the starting Y-coordinate
     * is set to {@code Integer.MAX_VALUE}, potentially causing an overflow
     * situation. The aim is to ensure that
     * the database handles boundary conditions gracefully without unexpected
     * behavior.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testRegionSearchOverflowY() {
        Database db = new Database();
        db.regionsearch(10, Integer.MAX_VALUE, 10, 10);
        assertTrue(outContent.toString().contains("Rectangle rejected"));
        // Similarly, this test checks for overflow on the y coordinate.
    }

}
