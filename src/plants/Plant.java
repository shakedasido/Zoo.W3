package plants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.Ilocatable;
import mobility.Point;

/**
 * Abstract class that defines the common features for all plants food type.
 * The class implements IEdible, Ilocatable and IDrawable interfaces.
 *
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see Cabbage
 */
public abstract class Plant implements IEdible, Ilocatable, IDrawable {

    private ZooPanel pan;
    private BufferedImage img;
    private double height;
    private Point location;
    private double weight;

    /**
     * Constructor for the Plant's attributes. It initializes elements of an abstract plant, and then it will be
     * sent to a specific plant to continue the plant creations.
     */
    public Plant(ZooPanel p) {
        this.pan=p;
        int size = 50;
        Random rand = new Random();
        int x = Point.max_x/2;
        int y = Point.max_y/2;
        this.location = new Point(x, y);
        this.height = rand.nextInt(30);
        this.weight = rand.nextInt(12);
    }
    //setters
    /**
     * (non-Javadoc)
     *
     * @see mobility.Ilocatable#setLocation(mobility.Point)
     */
    @Override
    public boolean setLocation(Point newLocation) {
        boolean isSuccess = Point.checkBoundaries(newLocation);
        if (isSuccess) {
            this.location = newLocation;
        }
        return isSuccess;
    }

    //getters
    /**
     * @see EFoodType
     */
    public EFoodType getFoodType() {return EFoodType.VEGETABLE;}

    /**
     * @return
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @see mobility.Ilocatable
     */
    @Override
    public Point getLocation() {
        return this.location;
    }
    public String getColor() {return "Green";}

    //methods
    @Override
    public void loadImages(String nm) {


        BufferedImage image = null;

        try {image = ImageIO.read(new File(PICTURE_PATH + nm+ ".png"));}
        catch (IOException e) { System.out.println("Cannot load image");}

        img = image;
    }

    public void drawObject(Graphics g)
    {g.drawImage(img, location.GetX()+50, location.GetY(), 50, 50, pan);}


    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }

}