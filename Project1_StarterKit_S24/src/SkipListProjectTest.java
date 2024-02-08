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
        assertEquals(expectedOutput,printedContent);

    }
    
    public void testMainWithValidFile() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        bw.write("insert a 1 0 2 4\n");
        bw.write("remove a\n");
        bw.close();

        SkipListProject.main(new String[]{tempFile.getPath()});

        // Verify that the output is as expected
        // Assert.assertEquals(expectedOutput, outContent.toString().trim());

        tempFile.delete();
    }

    public void testMainWithInvalidFile() {
        SkipListProject.main(new String[]{"non_existing_file.txt"});
        Assert.assertTrue(outContent.toString().contains("Invalid file"));
    }

    public void testMainWithEmptyFile() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        SkipListProject.main(new String[]{tempFile.getPath()});

        // Verify that the output is as expected for an empty file
        // Assert.assertEquals(expectedOutputForEmptyFile, outContent.toString().trim());
    }
}


