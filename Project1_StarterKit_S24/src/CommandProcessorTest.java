import org.junit.*;
import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Unit tests for the CommandParser class.
 * This test class extends TestCase from the JUnit framework.
 * It includes test cases to validate the functionality 
 * of the CommandParser class.
 * @author Mrunaldhar Bathula
 * @version 1.2
 */
public class CommandProcessorTest extends TestCase {
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
     * Testcase
     *
     * @throws Exception If an exception occurs.
     */
    public void testNikhil1() throws Exception {
        
        PrintStream newOut = new PrintStream(outContent);
        System.setOut(newOut);
     // takes the first command line argument and opens that file
        File file = new File("input.txt");
        
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
   
       
 
        
        String printedContent = outContent.toString().trim();
        
        String expectedOutput = readFile("output.txt");
        assertEquals(expectedOutput, printedContent);     
 
    }
}