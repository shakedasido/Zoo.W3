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
 * @author baroh
 *
 */
public abstract class Plant implements IEdible, Ilocatable, IDrawable {
    /**
     *
     */
    private ZooPanel pan;
    private BufferedImage img;

    private double height;
    /**
     *
     */
    private Point location;
    /**
     *
     */
    private double weight;

    /**
     *
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

    /*
     * (non-Javadoc)
     *
     * @see food.IFood#getFoodtype()
     */
    public EFoodType getFoodType() {return EFoodType.VEGETABLE;}

    /**
     * @return
     */
    public double getHeight() {
        return this.height;
    }

    /*
     * (non-Javadoc)
     *
     * @see mobility.ILocatable#getLocation()
     */
    @Override
    public Point getLocation() {
        return this.location;
    }

    /**
     * @return
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param height
     * @return
     */
    public boolean setHeight(double height) {

        boolean isSuccess = (height >= 0);
        if (isSuccess) {
            this.height = height;
        } else {
            this.height = 0;
        }
        return isSuccess;
    }

    /*
     * (non-Javadoc)
     *
     * @see mobility.ILocatable#setLocation(mobility.Point)
     */
    @Override
    public boolean setLocation(Point newLocation) {
        boolean isSuccess = Point.checkBoundaries(newLocation);
        if (isSuccess) {
            this.location = newLocation;
        }
        return isSuccess;
    }

    /**
     * @param weight
     * @return boolean
     */
    public boolean setWeight(double weight) {
        boolean isSuccess = (weight >= 0);
        if (isSuccess) {
            this.weight = weight;
        } else {
            this.weight = 0;
        }

        return isSuccess;
    }


    @Override
    public void loadImages(String nm) {


        BufferedImage image = null;

        try {image = ImageIO.read(new File(PICTURE_PATH + nm+ ".png"));}
        catch (IOException e) { System.out.println("Cannot load image");}

        img = image;
    }

    public void drawObject(Graphics g)
    {g.drawImage(img, location.GetX()+50, location.GetY(), 50, 50, pan);}

    public String getColor() {return "Green";}

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "] ";
    }

}