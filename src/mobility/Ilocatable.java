package mobility;

/**
 * An interface that describes the functionality of a place.
 * @version 02/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see Mobile
 */
public interface Ilocatable {
    Point getLocation();
    boolean setLocation(Point p);}
