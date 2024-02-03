import org.junit.*;
import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import org.junit.Assert.*;
/**
 * Unit tests for the CommandParser class.
 * This test class extends TestCase from the JUnit framework.
 * It includes test cases to validate the functionality 
 * of the CommandParser class.
 * @author Mrunaldhar Bathula
 * @version 1.2
 */
public class SkipListProjectTest extends TestCase {
    /**
     * Reads the content of a file as a string.
     *
     * @param path The path to the file to read.
     * @return The content of the file as a string.
     * @throws IOException If an I/O error occurs.
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
     * @throws Exception If an exception occurs.
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
        assertEquals(expectedOutput, expectedOutput);     
 
    }
    /*
     public void testRandomLevel() {
        SkipList skipList = new SkipList();
        HashSet<Integer> levelsGenerated = new HashSet<>();
        
        // Run the function multiple times to check the randomness and correctness
        for (int i = 0; i < 10000; i++) {
            int level = skipList.randomLevel();
            
            // Assert that the level is non-negative
            Assert.assertTrue("Level should be non-negative", level >= 0);
            
            // Add the level to the set to ensure randomness
            levelsGenerated.add(level);
        }

        // Assert that multiple different levels are generated, ensuring randomness
        // Assuming randomness, the chance of getting the same level 10000 times is negligible
        // This assertion might need to be adjusted based on the specifics of your randomLevel function
        Assert.assertTrue("Randomness check: multiple levels should be generated", levelsGenerated.size() > 1);
    }
     */
    
}