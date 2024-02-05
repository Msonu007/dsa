/**
 * The purpose of this class is to parse a single line from the command text file
 * according to the format specified in the project specs.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * @param dataIn
     *            the database object to manipulate
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method parses keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions. 
     * These actions are performed on specified objects and include insert, remove,
     * regionsearch, search, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        String[] arr = line.trim().split("\\s+"); // Use "\\s+" to split on one or more spaces
        if (arr.length == 0) {
            System.out.println("Empty command line.");
            return;
        }

        String command = arr[0];
        try {
            switch (command) {
                case "insert":
                    // Ensure that there are enough arguments for the insert command
                    if (arr.length == 6) {
                        String rectName = arr[1];
                        int x = Integer.parseInt(arr[2]);
                        int y = Integer.parseInt(arr[3]);
                        int width = Integer.parseInt(arr[4]);
                        int height = Integer.parseInt(arr[5]);
                        Rectangle rect = new Rectangle(x, y, width, height);
                        KVPair<String, Rectangle> val = new KVPair<>(rectName, rect);
                        data.insert(val);
                    } else {
                        System.out.println("Invalid number of arguments for insert.");
                    }
                    break;
                case "remove":
                    // Remove by name or by coordinates based on the number of arguments
                    if (arr.length == 2) {
                        data.remove(arr[1]);
                    } else if (arr.length == 5) {
                        int x = Integer.parseInt(arr[1]);
                        int y = Integer.parseInt(arr[2]);
                        int width = Integer.parseInt(arr[3]);
                        int height = Integer.parseInt(arr[4]);
                        data.remove(x, y, width, height);
                    } else {
                        System.out.println("Invalid number of arguments for remove.");
                    }
                    break;
                case "regionsearch":
                    // Implement regionsearch functionality
                    break;
                case "intersections":
                    // Implement intersections functionality
                    break;
                case "search":
                    if (arr.length == 2) {
                        data.search(arr[1]);
                    } else {
                        System.out.println("Invalid number of arguments for search.");
                    }
                    break;
                case "dump":
                    data.dump();
                    break;
                default:
                    System.out.println("Unrecognized command: " + command);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numeric value: " + e.getMessage());
        }
    }

    

}