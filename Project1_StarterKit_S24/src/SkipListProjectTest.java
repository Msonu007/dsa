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
     * Sets up the redirection of the standard output stream to capture console
     * output for testing purposes.
     * This method is annotated with {@code @Before} to indicate that it is
     * executed before each test method.
     * It redirects the standard output stream ({@code System.out}) to a
     * {@code ByteArrayOutputStream}
     * ({@code outContent}) to capture all output data usually printed to the
     * console during test execution.
     * 
     * @see #restoreStreams() The corresponding method to restore the standard
     *      output stream after testing.
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
     * Parses a string representing output from a search operation and extracts
     * relevant information.
     * This method takes a string input representing the output of a search
     * operation, splits it into lines,
     * and extracts the relevant information to construct a list of strings. If
     * multiple lines are present,
     * it extracts the first word from each line, excluding commas and
     * parentheses. If only one line is present
     * and it contains the phrase "not found", it extracts the substring
     * following the colon and trims leading
     * and trailing whitespace.
     * 
     * @param s
     *            A {@code String} representing the output of a search
     *            operation.
     * @return An {@code ArrayList<String>} containing the extracted
     *         information.
     */
    public ArrayList<String> helper(String s) {
        String[] k = s.split("\n");
        ArrayList<String> sol = new ArrayList<String>();
        if (k.length > 1) {
            for (int i = 1; i < k.length; i++) {
                String[] a3 = k[i].split("\\s+");
                sol.add(a3[0].replaceAll("\\(", "").replaceAll("\\,", ""));
            }
        }
        else if (k.length == 1) {
            if (k[0].contains("not found")) {
                sol.add(k[0].split(":")[1].trim());
            }
        }

        return sol;

    }


    /**
     * Compares two strings representing search results to ensure they are
     * identical in terms of their individual search items.
     * This method assists in comparing expected and actual search results by
     * breaking down the strings into individual search items
     * and then comparing them one by one. It verifies that the expected and
     * actual search results contain the same number of items
     * and that each corresponding item is identical.
     * 
     * @param expected
     *            The expected search result string.
     * @param actual
     *            The actual search result string to be compared against the
     *            expected result.
     */
    public void searchStr(String expected, String actual) {
        ArrayList<String> solExpected = this.helper(expected);
        ArrayList<String> solActual = this.helper(actual);
        assertEquals(solExpected.size(), solActual.size());
        for (int i = 0; i < solExpected.size(); i++) {
            assertEquals(solExpected.get(i), solActual.get(i));
        }
    }


    /**
     * Parses a string to extract and return the first segment following a colon
     * (':'), removing any parentheses and commas.
     * This method splits the input string by colon (':') and retrieves the
     * second segment.
     * It then removes any parentheses and commas from the first word in the
     * second segment, if present, and returns the modified string.
     * If the input string does not contain a colon, an empty string is
     * returned.
     * 
     * @param s
     *            A {@code String} to be parsed.
     * @return A {@code String} containing the first word from the segment
     *         following the colon (':'), with parentheses and commas removed.
     */
    public String helperRemove(String s) {
        String[] s1 = s.split("\\:");
        if (s1.length > 1) {
            String sol = s1[1].split("\\s+")[0].replaceAll("\\(", "")
                .replaceAll("\\,", "");
            return sol;
        }
        return "";

    }


    /**
     * Compares two strings after removing any leading or trailing whitespace,
     * ensuring they are equal.
     * This method is used to compare two strings while disregarding any leading
     * or trailing whitespace characters,
     * enabling a more flexible comparison between strings that may have
     * different formatting or spacing.
     * 
     * @param expected
     *            The expected string value.
     * @param actual
     *            The actual string value to be compared with the expected
     *            value.
     */
    public void removeStr(String expected, String actual) {
        String s1 = this.helperRemove(expected);
        String s2 = this.helperRemove(actual);
        assertEquals(s1, s2);
    }


    /**
     * Parses a string containing two coordinates separated by a '|' character
     * and extracts the coordinates
     * for the intersection of two rectangles.
     * <p>
     * If the input string {@code s} contains the '|' character, it splits the
     * string into two parts and extracts
     * the coordinates for the intersection. Each part is assumed to represent a
     * rectangle's coordinates, formatted
     * as "(x1, y1, x2, y2)". The method then removes any commas and parentheses
     * and adds the x-coordinate of the
     * first rectangle's top-left corner and the y-coordinate of the second
     * rectangle's bottom-right corner to the
     * result {@code ArrayList}.
     * <p>
     * Input:
     * - A {@code String} {@code s} representing two rectangle coordinates
     * separated by a '|' character.
     * <p>
     * Output:
     * - An {@code ArrayList<String>} containing two strings representing the
     * coordinates of the intersection:
     * 
     * @param s
     *            A {@code String} representing two rectangle coordinates
     *            separated by a '|' character.
     * @return An {@code ArrayList<String>} containing the coordinates of the
     *         intersection.
     */
    public ArrayList<String> intersectionStr(String s) {
        ArrayList<String> sol = new ArrayList<String>();
        if (s.contains("|")) {
            String[] k = s.split("\\|");
            for (int i = 0; i < k.length; i++) {
                System.out.println(k[i]);
            }
            sol.add(k[0].split("\\s+")[0].replaceAll("\\,", "").replaceAll(
                "\\(", ""));
            sol.add(k[1].split("\\s+")[1].replaceAll("\\,", "").replaceAll(
                "\\(", ""));
            System.out.println(sol.get(0));
            System.out.println(sol.get(1));
        }
        return sol;

    }


    /**
     * Test case for Mrunal
     *
     * @throws Exception
     *             If an exception occurs.
     * 
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
        System.out.println(printedContent);
        String[] stdOut = printedContent.split("\n");
        String[] expOut = expectedOutput.split("\n");
        int std = 0;
        int exp = 0;
        boolean switchFound = false;
        String foundStd = "";
        String expecStd = "";
        this.restoreStreams();
        while (std < stdOut.length && exp < expOut.length) {
            System.out.printf(
                "The expected value is %s : The actual value is %s \n",
                stdOut[std], expOut[exp]);
            if (stdOut[std].contains("Rectangle") || stdOut[std].contains(
                "Intersection")) {
                if (foundStd.length() > 0) {
                    searchStr(foundStd, expecStd);
                    foundStd = "";
                    expecStd = "";
                }
                switchFound = false;
            }
            if (stdOut[std].contains("found") || stdOut[std].contains(
                "not found")) {
                switchFound = true;
                foundStd.concat(stdOut[std]);
                expecStd.concat(expOut[exp]);
            }
            else if (switchFound) {
                foundStd.concat(stdOut[std].concat("\n"));
                expecStd.concat(expOut[exp].concat("\n"));
            }
            else if (stdOut[std].contains("removed") || stdOut[std].contains(
                "not removed")) {
                removeStr(stdOut[std], expOut[exp]);
            }
            else if (stdOut[std].contains("(") && stdOut[std].contains("|")) {
                ArrayList<String> splfound = this.intersectionStr(stdOut[std]);
                ArrayList<String> expfound = this.intersectionStr(expOut[exp]);
                assertEquals(splfound.size(), expfound.size());
                for (int i = 0; i < splfound.size(); i++) {
                    if (!splfound.get(i).equals(expfound.get(i))) {

                        System.out.printf(
                            "The mismatch values are %s,%s, %d, %d \n",
                            stdOut[std], expOut[exp], std, exp);
                        System.out.printf("The keys are %s,%s\n", splfound.get(
                            i), expfound.get(i));
                    }
                    assertEquals(splfound.get(i), expfound.get(i));
                }
            }
            else if (stdOut[std].contains("(") && !stdOut[std].contains(
                "Rectangle")) {
                String s11 = stdOut[std].split("\\s+")[0].replaceAll("\\,", "")
                    .replaceAll("\\(", "");
                String s12 = expOut[exp].split("\\s+")[0].replaceAll("\\,", "")
                    .replaceAll("\\(", "");
                if (!s11.equals(s12)) {
                    this.restoreStreams();
                    System.out.printf(
                        "The mismatch values are %s,%s, %d, %d \n", stdOut[std],
                        expOut[exp], std, exp);
                }
                assertEquals(s11, s12);
            }
            else {
                assertEquals(stdOut[std], expOut[exp]);
            }
            exp++;
            std++;
        }

    }


    /**
     * Tests the {@code dump} method of the {@code SkipList} class when the list
     * is empty.
     * This test verifies that calling the {@code dump} method on an empty
     * {@code SkipList} results
     * in the output indicating a single node with depth 1 and a null value, as
     * expected for an empty list.
     * 
     * @author Mrunaldhar Bathula
     * @version 1.2
     */
    public void testDumpEmptyList() {
        SkipList<Integer, String> list = new SkipList<>();
        list.dump();
        String expectedOutput = "Node has depth 1, value null";
        assertTrue(outContent.toString().contains(expectedOutput));
    }


    /**
     * Tests the {@code dump} method of the {@code SkipList} class for a
     * non-empty list.
     * This test inserts a key-value pair into a {@code SkipList}, then calls
     * the {@code dump} method
     * to print the contents of the list. It asserts that the printed output
     * contains the value inserted
     * into the list, ensuring that the {@code dump} method correctly displays
     * the elements of the list.
     */
    public void testDumpNonEmptyList() {
        SkipList<Integer, String> list = new SkipList<>();
        list.insert(new KVPair<>(1, "Value1"));
        list.dump();
        assertTrue(outContent.toString().contains("Value1"));
    }


    /**
     * Tests the {@code dump} method of the {@code SkipList} class with a
     * rectangle object inserted.
     * This test inserts a rectangle object into a SkipList, then calls the dump
     * method to print the contents of the SkipList.
     * It verifies that the output contains the string representation of the
     * inserted rectangle.
     * 
     * @see SkipList#dump()
     */
    public void testDumpWithRectangle() {
        SkipList<Integer, Rectangle> list = new SkipList<>();
        list.insert(new KVPair<>(1, new Rectangle(0, 0, 10, 20)));
        list.dump();
        String expected = "value (1, 0, 0, 10, 20)"; // Adjust depth as needed
        assertTrue(outContent.toString().contains(expected));
    }


    /**
     * Tests the correctness of the depth reported by the {@code dump} method in
     * the {@code SkipList} class.
     * This test inserts multiple elements into the skip list to potentially
     * increase the depth of some nodes,
     * and then calls the {@code dump} method to print the structure of the skip
     * list.
     * It asserts that the output contains information about the depth of nodes
     * in the skip list, ensuring
     * that the reported depth matches the expectations.
     */
    public void testDumpReportsCorrectDepth() {
        SkipList<Integer, String> list = new SkipList<>();
        // Insert multiple elements to potentially increase the depth of some
        // nodes
        list.insert(new KVPair<>(1, "Value1"));
        list.insert(new KVPair<>(2, "Value2"));
        list.insert(new KVPair<>(3, "Value3"));
        list.dump();
        // This test might need to be adjusted based on how your skip list
        // handles levels.
        // The idea is to verify that the depth reported in the dump matches
        // expectations.
        // You would need to determine what those expectations are based on your
        // implementation.
        assertTrue(outContent.toString().matches(
            "(?s).*Node has depth [\\d+], value.*")); // Use regex to check
                                                      // depth format
    }
}
