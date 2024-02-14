import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;

/**
 * The {@code CommandProcessorTest} class contains unit tests for the
 * {@code CommandProcessor} class.
 * It focuses on testing various scenarios and edge cases of the command
 * processing functionality
 * provided by the {@code CommandProcessor} class.
 * <p>
 * Each test method within this class verifies a specific behavior of the
 * {@code CommandProcessor},
 * including correct handling of different commands, invalid input scenarios,
 * and error messages.
 * <p>
 * The tests are designed to ensure that the {@code CommandProcessor} class
 * functions as expected
 * in different scenarios and maintains the desired behavior in response to
 * various inputs and commands.
 * <p>
 * 
 * @author Mrunaldhar Bathula:
 * @version 1.2
 */
public class CommandProcessorTest {

    private CommandProcessor commandProcessor;

    /**
     * Initializes the {@code commandProcessor} instance for test setup.
     * This method creates a new instance of the {@code CommandProcessor} class,
     * which is used for processing commands
     * in the test environment. It is typically called before each test method
     * to ensure that the {@code commandProcessor}
     * object is properly initialized for testing.
     * 
     * @see CommandProcessor
     */
    public void setUp() {
        commandProcessor = new CommandProcessor();
    }


    /**
     * Tests the behavior of the {@code processor} method in the
     * {@code CommandProcessor} class
     * when provided with an invalid number of arguments for the "search"
     * command.
     * This test verifies that the processor correctly handles the case where
     * the "search" command
     * is invoked without providing the required name argument, and outputs an
     * appropriate error message.
     * <p>
     * This method does not return any value but asserts the
     * correctness of the output message.
     * 
     * Preconditions:
     * - The {@code CommandProcessor} instance is properly initialized before
     * running the test.
     * 
     * Postconditions:
     * - The output stream is captured, the {@code processor} method is invoked
     * with the provided command,
     * and the captured output is checked to ensure that it contains the
     * expected error message indicating an unrecognized command.
     * Finally, the output stream is reset to its original state.
     * 
     * @author Mrunaldhar Bathula:
     * @version 1.2
     */
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
