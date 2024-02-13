/**
 * This class holds the coordinates and dimensions of a rectangle and methods to
 * check if it intersects or has the same coordinates as an other rectangle.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class Rectangle {
    // the x coordinate of the rectangle
    private int xCoordinate;
    // the y coordinate of the rectangle
    private int yCoordinate;
    // the distance from the x coordinate the rectangle spans
    private int width;
    // the distance from the y coordinate the rectangle spans
    private int height;

    /**
     * Creates an object with the values to the parameters given in the
     * xCoordinate, yCoordinate, width, height
     * 
     * @param x
     *            x-coordinate of the rectangle
     * @param y
     *            y-coordinate of the rectangle
     * @param w
     *            width of the rectangle
     * @param h
     *            height of the rectangle
     */
    public Rectangle(int x, int y, int w, int h) {
        xCoordinate = x;
        yCoordinate = y;
        width = w;
        height = h;
    }


    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }


    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }


    /**
     * Getter for the width
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Getter for the height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }


    /**
     * Checks if the invoking rectangle intersects with rec.
     * 
     * @param r2
     *            Rectangle parameter
     * @return true if the rectangle intersects with rec, false if not
     */
    public boolean isPointInside(int x,int y) {
        int p2 = this.xCoordinate+this.width;
        int p3 = this.yCoordinate+this.height;
        return (x>=this.xCoordinate && y>=this.yCoordinate && x<=p2 && y<=p3);
    }
    
    public boolean intersect(Rectangle r2) {
        if (this.equals(r2)) {
            return true;
        }
        if (this.xCoordinate + this.width <= r2.getxCoordinate() || r2.getxCoordinate() + r2.getWidth() <= this.xCoordinate) {
            return false;
        }else if (this.yCoordinate + this.height <= r2.getyCoordinate() || r2.getyCoordinate() + r2.getHeight() <= this.yCoordinate) {
            return false;
        } else {
            return true;
        }

        // If neither condition is true, rectangles intersect with an area > 0
        

    }


    /**
     * Checks, if the invoking rectangle has the same coordinates as rec.
     * 
     * @param rec
     *            the rectangle parameter
     * @return true if the rectangle has the same coordinates as rec, false if
     *         not
     */
    public boolean equals(Object rec) {

        // Check for null and ensure exact type match if strict equality is
        // needed
        if (rec == null || this.getClass() != rec.getClass()) {
            return false;
        }
        return this.xCoordinate == ((Rectangle)rec).getxCoordinate()
            && this.yCoordinate == ((Rectangle)rec).getyCoordinate()
            && this.height == ((Rectangle)rec).getHeight()
            && this.width == ((Rectangle)rec).getWidth();
    }


    /**
     * Outputs a human readable string with information about the rectangle
     * which includes the x and y coordinate and its height and width
     * 
     * @return a human readable string containing information about the
     *         rectangle
     */
    public String toString() {
        return null;
    }


    /**
     * Checks if the rectangle has invalid parameters
     * 
     * @return true if the rectangle has invalid parameters, false if not
     */
    public boolean isInvalid() {
        if (this.xCoordinate >= 1024 || this.yCoordinate >= 1024 || this.xCoordinate < 0 || this.yCoordinate <0 ) {
            return true;
        }
        boolean s = this.xCoordinate < 0 || this.yCoordinate < 0 || this.yCoordinate + this.height > 1024 || this.xCoordinate+ this.width > 1024 || this.height <= 0 || this.width <= 0;
        return s;
    }
}
