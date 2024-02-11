import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;

public class CommandProcessorTest {

    private Database mockDatabase;
    private CommandProcessor commandProcessor;

    public void setUp() {
        commandProcessor = new CommandProcessor();
    }


    public void testSearchWithInvalidNumberOfArguments() {
        String line = "search"; // No name provided
        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Perform the operation
        commandProcessor.processor(line);

        // Check the output
        String expectedOutput = "Unrecognized command: ";
        Assert.assertTrue(outContent.toString().contains(expectedOutput));

        // Reset the System.out to its original state
        System.setOut(System.out);
    } 
}
