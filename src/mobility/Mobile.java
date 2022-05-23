package mobility;

/**
 * An abstract class that defines motion in space,
 * and implements the ILocatable interface
 * @version 02/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see     Ilocatable
 */
public abstract class Mobile implements Ilocatable {
    private Point location; //current location
    private double totalDistance; // distance the object traveled>=0

    /**
     * Constructor that gets a point. initialize location and totalDistance fields.
     * @param p
     *        represent the location.
     */
    public Mobile(Point p)
    {
        this.location= new Point(p);
        this.totalDistance=0;
    }

    //setters
    /**
     * Setter.
     * sets the attribute location, if the point is in the range.
     * @param p
     *      point
     * @return Boolean result
     */
    public boolean setLocation(Point p)
    {
        this.location.setPoint(p);
        return true;

    }

    //getters
    public Point getLocation(){return this.location;}

    //methods
    /**
     * Gets distance and add it to the total distance.
     * @param distance
     *        represent a distance as string.
     *
     */
    public void addTotalDistance(double distance) { this.totalDistance+=distance;}

    /**
     * Gets p (point), And calculate the distance of the point- the current location from the last location.
     * @param p
     *        represent a point from class Point.
     *
     * @return the distance from the last location to the current location as double.
     */
    public double calcDistance(Point p)
    { return Math.sqrt(Math.pow(this.location.GetX()-p.GetX(), 2)+Math.pow(this.location.GetY()-p.GetY(),2));}

    /**
     * Gets a location (point). if the location is in the allowed range,it updates the totalDistance with
     * the new distance that where added. and set the new location.
     * @param p
     *        represent a point from class Point.
     *
     * @return the distance from the last location to the current location as double.
     */
    public double move(Point p)
    {
        if(this.location.equals(p)||!Point.checkBoundaries(p)) return 0;

        double distance = this.calcDistance(p);

        this.addTotalDistance(distance);

        this.setLocation(p);

        return distance;
    }

    public String toString1() {return ("("+this.location.GetX()+", "+this.location.GetY()+")");}



}