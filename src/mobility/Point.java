package mobility;
/**
 * Java project: W3
 * @author  Shaked Asido: 315853150
 *          Tomer handali: 206751489
 * @campus: Ashdod.
 */

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

    /**
     * Gets animal and food type.
     * Check if it's the correct type of food that the animal eats.
     * if yes, The animal eats, and it gains wight.
     * else, it doesn't gain any weight.
     * @param p
     *        represent a point from class Point.
     *
     * @return Boolean result. True- if the point is in the range, False- if not.
     */
    public static boolean checkBoundaries(Point p) { return p.x >= min_x && p.x <= max_x && p.y >= min_y && p.y <= max_y;}

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

    public boolean setPoint(int x,int y)
    {
        if(!checkBoundaries(new Point(x,y)))
            return false;

        this.x=x;
        this.y=y;

        return true;
    }

    public boolean setpoint(Point other)
    {
        if(!checkBoundaries(other))
            return false;

        this.x=other.x;
        this.y=other.y;

        return true;
    }

    public boolean equals(Point other) {return this.x == other.x && this.y == other.y;}

    public String toString() {return ("( "+this.x+" , "+this.y+" )");}

}