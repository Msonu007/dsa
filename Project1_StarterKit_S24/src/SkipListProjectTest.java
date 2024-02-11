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
     *             
     */
    public ArrayList<String> helper(String s) {
        String[] k = s.split("\n");
        ArrayList<String> sol = new ArrayList<String>();
        if (k.length > 1) {
            for (int i = 1; i < k.length; i++) {
                String[] a3 = k[i].split("\\s+");
                sol.add(a3[0].replaceAll("\\(", "").replaceAll("\\,", ""));
            }
        } else if (k.length == 1) {
            if (k[0].contains("not found")) {
                sol.add(k[0].split(":")[1].trim());
            }
        }
        
        return sol;
        
    }
    public void Searchstr(String expected,String actual) {
        ArrayList<String> solExpected = this.helper(expected);
        ArrayList<String> solActual = this.helper(actual);
        assertEquals(solExpected.size(),solActual.size());
        for (int i = 0;i<solExpected.size();i++) {
            assertEquals(solExpected.get(i),solActual.get(i));
        }
    }
    
    public String helperRemove(String s) {
        String[] s1 = s.split("\\:");
        if (s1.length > 1) {
            String sol = s1[1].split("\\s+")[0].replaceAll("\\(", "").replaceAll("\\,", "");
            return sol;
        }
        return "";
        
    }
    public void Removestr(String expected, String actual) {
        String s1 = this.helperRemove(expected);
        String s2 = this.helperRemove(actual);
        assertEquals(s1,s2);
    }
    
    public ArrayList<String> Intersectionstr(String s) {
        ArrayList<String> sol = new ArrayList<String>();
        if (s.contains("|")) {
            String[] k = s.split("\\|");
            for (int i=0;i<k.length;i++){
                System.out.println(k[i]);
            }
            sol.add(k[0].split("\\s+")[0].replaceAll("\\,","").replaceAll("\\(",""));
            sol.add(k[1].split("\\s+")[1].replaceAll("\\,","").replaceAll("\\(",""));
            System.out.println(sol.get(0));
            System.out.println(sol.get(1));
        }
        return sol;
        
    }
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
                    String[] arr = line.trim().split("\\s+");
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
        
//        File currentDirFile = new File("print.txt");
//
//
//        String path = currentDirFile.getAbsolutePath();
//
//        Files.write( Paths.get(path), printedContent.getBytes());

        String expectedOutput = readFile("output.txt");
        System.out.println(printedContent);
        String[] stdOut = printedContent.split("\n");
        String[] expOut = expectedOutput.split("\n");
        int std = 0;
        int exp = 0;
        boolean sw_f = false;
        String foundStd = "",expecStd="";
        this.restoreStreams();
        while (std < stdOut.length && exp < expOut.length ) {
            System.out.printf("The expected value is %s : The actual value is %s \n",stdOut[std],expOut[exp]);
            if (stdOut[std].contains("Rectangle") || stdOut[std].contains("Intersection")) {
                if(foundStd.length() > 0) {
                    Searchstr(foundStd,expecStd);
                    foundStd = "";
                    expecStd = "";
                }
                sw_f = false;
            }
            if(stdOut[std].contains("found") || stdOut[std].contains("not found")) {
                sw_f = true;
                foundStd.concat(stdOut[std]);
                expecStd.concat(expOut[exp]);
            }else if(sw_f) {
                foundStd.concat(stdOut[std].concat("\n"));
                expecStd.concat(expOut[exp].concat("\n")); 
            }else if(stdOut[std].contains("removed") || stdOut[std].contains("not removed")) {
                Removestr(stdOut[std],expOut[exp]); 
            }else if (stdOut[std].contains("(") && stdOut[std].contains("|")) {
                ArrayList<String> splfound = this.Intersectionstr(stdOut[std]);
                ArrayList<String> expfound = this.Intersectionstr(expOut[exp]);
                assertEquals(splfound.size(),expfound.size());
                for (int i =0;i<splfound.size();i++) {
                    if (!splfound.get(i).equals(expfound.get(i))) {
                        
                        System.out.printf("The mismatch values are %s,%s, %d, %d \n",stdOut[std],expOut[exp],std,exp);
                        System.out.printf("The keys are %s,%s\n",splfound.get(i),expfound.get(i));
                    }
                    assertEquals(splfound.get(i),expfound.get(i));
                }
            }else if(stdOut[std].contains("(") && !stdOut[std].contains("Rectangle")){
                String s11 = stdOut[std].split("\\s+")[0].replaceAll("\\,","").replaceAll("\\(","");
                String s12 = expOut[exp].split("\\s+")[0].replaceAll("\\,","").replaceAll("\\(","");
                if (!s11.equals(s12)) {
                    this.restoreStreams();
                    System.out.printf("The mismatch values are %s,%s, %d, %d \n",stdOut[std],expOut[exp],std,exp);
                }
                assertEquals(s11,s12);
                }
            else {
                assertEquals(stdOut[std],expOut[exp]);
            }
            exp++;
            std++;
        }
        

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
        SkipListProject.main(new String[]{"empty.txt"});
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


