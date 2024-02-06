import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * Unit tests for the CommandParser class.
 * This test class extends TestCase from the JUnit framework.
 * It includes test cases to validate the functionality
 * of the CommandParser class.
 * 
 * @author Mrunaldhar Bathula
 * @version 1.2
 */
public class SkipListProjectTest extends TestCase {
    /**
     * Reads the content of a file as a string.
     *
     * @param path
     *            The path to the file to read.
     * @return The content of the file as a string.
     * @throws IOException
     *             If an I/O error occurs.
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    /**
     * set Ups Steams
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }


    /**
     * restore Steams
     */
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }


    /**
     * Test case for Mrunal
     *
     * @throws Exception
     *             If an exception occurs.
     */
    public void testMrunal() throws Exception {

        PrintStream newOut = new PrintStream(outContent);
        System.setOut(newOut);

        // the file containing the commands
        File file = null;

        // Attempts to open the file and scan through it
        try {

            // takes the first command line argument and opens that file
            file = new File("input.txt");

            // creates a scanner object
            Scanner scanner = new Scanner(file);

            // creates a command processor object
            CommandProcessor cmdProc = new CommandProcessor();

            // reads the entire file and processes the commands
            // line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // determines if the file has more lines to read
                if (!line.trim().isEmpty()) {
                    cmdProc.processor(line.trim());
                }
            }
            // closes the scanner
            scanner.close();
        }
        // catches the exception if the file cannot be found
        // and outputs the correct information to the console
        catch (FileNotFoundException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }

        String printedContent = outContent.toString().trim();

        String expectedOutput = readFile("output.txt");
        assertEquals(printedContent, expectedOutput);

    }


    public void testRandomLevel() {
        SkipList skipList = new SkipList();
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

}
