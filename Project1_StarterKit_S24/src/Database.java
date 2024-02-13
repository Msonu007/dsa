import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * Also note that the Database class will have a clearer role in Project2,
 * where we will have two data structures. The Database class will then
 * determine
 * which command should be directed to which data structure.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    // This is an Iterator object over the SkipList to loop through it from
    // outside the class.
    // You will need to define an extra Iterator for the intersections method.
    private Iterator<KVPair<String, Rectangle>> itr1;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that
        Rectangle rect = pair.getValue();
        if (!rect.isInvalid()) {
            list.insert(pair);
            System.out.printf("Rectangle inserted: (%s, %d, %d, %d, %d)\n", pair
                .getKey().toString(), pair.getValue().getxCoordinate(), pair
                    .getValue().getyCoordinate(), pair.getValue().getWidth(),
                pair.getValue().getHeight());
        }
        else {
            System.out.printf("Rectangle rejected: (%s, %d, %d, %d, %d)\n", pair
                .getKey().toString(), pair.getValue().getxCoordinate(), pair
                    .getValue().getyCoordinate(), pair.getValue().getWidth(),
                pair.getValue().getHeight());
        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, Rectangle> ans;
        ans = list.remove(name);
        if (ans != null) {
            System.out.println("Rectangle removed: " + "(" + ans
                .getKey() + ", " + ans.getValue().getxCoordinate()
                + ", " + ans.getValue().getyCoordinate() + ", "
                + ans.getValue().getWidth() + ", " + ans
                    .getValue().getHeight() + ")");
        }
        else {
            System.out.println("Rectangle not removed: " + name);
        }

    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        KVPair<String, Rectangle> ans;
        Rectangle rec = new Rectangle(x, y, w, h);
        if (rec.isInvalid()) {
            System.out.printf("Rectangle rejected: (%d, %d, %d, %d)\n", x, y, w,h);
            return;
        }
        ans = list.removeByValue(rec);
        if (ans != null) {
            System.out.printf("Rectangle removed: (%s, %d, %d, %d, %d)\n", ans
                .getKey().toString(), ans.getValue().getxCoordinate(), ans
                    .getValue().getyCoordinate(), ans.getValue().getWidth(), ans
                        .getValue().getHeight());
        }
        else {
            System.out.printf("Rectangle not removed: (%d, %d, %d, %d)\n", x, y, w,
                h);
        }

    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        long lx = x;
        long ly = y;
        if (h>0 && w>0 && lx+w<=Integer.MAX_VALUE && ly+w<=Integer.MAX_VALUE) {
            Rectangle ques = new Rectangle(x, y, w, h);
            ArrayList<KVPair<String, Rectangle>> sol =
                new ArrayList<KVPair<String, Rectangle>>();
            sol = list.regionSearch(ques);
            System.out.printf(
                "Rectangles intersecting region (%d, %d, %d, %d):\n", x, y, w,
                h);
            if (sol.size() != 0) {
                for (int i = 0; i < sol.size(); i++) {
                    Rectangle rec1 = sol.get(i).getValue();
                    System.out.printf("(%s, %d, %d, %d, %d)\n", sol.get(i)
                        .getKey().toString(), rec1.getxCoordinate(), rec1
                            .getyCoordinate(), rec1.getWidth(), rec1
                                .getHeight());
                }
            }
        }
        else {
            System.out.printf("Rectangle rejected: (%d, %d, %d, %d)\n", x, y, w,
                h);
        }
    }


    /**
     * Prints out all the rectangles that intersect each other. Note that
     * it is better not to implement an intersections method in the SkipList
     * class
     * as the SkipList needs to be agnostic about the fact that it is storing
     * Rectangles.
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        ArrayList<String> sol = list.allIntersections();
        for (int i =0;i<sol.size();i++) {
            System.out.printf(sol.get(i));
        }
        

    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Rectangle>> k;
        k = this.list.search(name);
        if (k.size() != 0) {
            System.out.println("Rectangles found:");
            for (KVPair<String, Rectangle> ele : k) {
                System.out.printf("(%s, %d, %d, %d, %d)\n", ele.getKey()
                    .toString(), ele.getValue().getxCoordinate(), ele.getValue()
                        .getyCoordinate(), ele.getValue().getWidth(), ele
                            .getValue().getHeight());
            }
        }
        else {
            System.out.printf("Rectangle not found: (%s)\n", name);
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
