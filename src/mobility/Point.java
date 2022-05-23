package mobility;

/**
 * Defines a position on a two-dimensional axis.
 * the class will contain the X, Y fields.
 * @version 30/04/2022
 * @author  Shaked Asido, Tomer handali.
 * @see     Ilocatable
 */

public class Point {
    private int x;
    private int y;
    public static final int min_x=0;
    public static final int max_x=800;
    public static final int min_y=0;
    public static final int max_y=600;

    /**
     * Constructor for X, Y fields. It creates a point.
     * @param x
     *        represent the X field
     * @param y
     *        represent the Y field
     */
    public Point(int x, int y) //throws IllegalArgumentException
    {
        this.x=x;
        this.y=y;
    }
    /**
     * Constructor for X, Y fields. It creates a point.
     * @param other
     *        represent the another Point
     */
    public Point(Point other)
    {
        this.x=other.x;
        this.y=other.y;
    }

    //setters
    /**
     * Setter.
     * sets the attribute x.
     */
    public boolean setX(int x)
    {
        if(x<min_x || x>max_x)
            return false;

        this.x=x;
        return true;
    }

    /**
     * Setter.
     * sets the attribute y.
     */
    public boolean setY(int y)
    {
        if(y<min_y||y>max_y)
            return false;

        this.y=y;
        return true;
    }

    public boolean setPoint(Point other)
    {
        if(!checkBoundaries(other))
            return false;

        this.x=other.x;
        this.y=other.y;

        return true;
    }

    //getters
    /**
     * Getter.
     * gets the attribute x.
     * @return the value of the attribute x.
     */
    public int GetX() {return this.x;}

    /**
     * Getter.
     * gets the attribute y.
     * @return the value of the attribute y.
     */
    public int GetY() {return this.y;}

    //methods
    /**
     * Check if the animal is in the correct boundaries, so she will be able to eat her food.
     * @param p
     *        represent a point from class Point.
     *
     * @return Boolean result. True- if the point is in the range, False- if not.
     */
    public static boolean checkBoundaries(Point p) { return p.x >= min_x && p.x <= max_x && p.y >= min_y && p.y <= max_y;}

    public boolean equals(Point other) {return this.x == other.x && this.y == other.y;}

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {return ("( "+this.x+" , "+this.y+" )");}

}