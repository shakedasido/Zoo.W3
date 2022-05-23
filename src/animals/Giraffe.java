package animals;
import food.EFoodType;
import graphics.ZooPanel;
import diet.*;
import mobility.*;

/**
 * A class representing a Giraffe, which it is kind of animal, vegetable eater.
 * Extends class Animal.
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see     Animal
 */
public class Giraffe extends Animal{

    private double neckLength=0;

    public final static double minNeckLength=1.0;
    public final static double maxNeckLength=2.5;
    public final static double defNeckLength=1.5;
    public static final double def_weight = 450;
    public static final Point def_location = new Point(50,0);


    public Giraffe(int size, String col, int horSpeed, int verSpeed, ZooPanel pan)
    {
        super("Giraffe", size, size*2.2, horSpeed, verSpeed, col, pan);
        //here we create a new point, so we use new, in order to create a new default value.
        setLocation(new Point(50,0));
        this.setDiet(new Herbivore());
        this.SetWeight(size*2.2);
        loadImages("grf");
        neckLength = 1.5;
    }

    public Giraffe(String s) {this(s,def_location);}
    public Giraffe(String s, Point p) {this(s,p,def_weight);}
    public Giraffe(String s, Point p, double w)
    {
        this(s,p,w,defNeckLength);
    }
    public Giraffe(String s, Point p, double w, double n)
    {
        super(s,p);
        this.SetWeight(w);
        this.setNeckLength(n);
        this.setDiet(new Herbivore());
    }

    //setters
    public boolean setNeckLength(double t)
    {
        boolean x=true;

        if(t<minNeckLength||t>maxNeckLength)
            x = false;
        else
            this.neckLength=t;


        if(this.neckLength==0 && !x) // constructor call -> must be initialized with default value
        {
            this.neckLength=defNeckLength;
            x=true;

        }
        return x;
    }

    //getters
    public EFoodType getFoodType() { return EFoodType.MEAT; }
    public double getNeckLength() {return this.neckLength;}

    //methods
    public String toString()
    {
        return  "[ Giraffe]: "+this.getAnimalName();
    }

}