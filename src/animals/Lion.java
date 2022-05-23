package animals;
import java.util.Random;
import diet.*;
import food.EFoodType;
import graphics.ZooPanel;
import mobility.*;

/**
 * A class representing a Lion, which it is kind of animal, meat eater.
 * Extends class Animal.
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see     Animal
 */
public class Lion extends Animal{

    private int scarCount=0;
    public static final int defScars=0;

    public static final double def_weight = 408.2;
    public static final Point def_location = new Point(20,0);

    /**
     * Constructor for the Lion's attributes. It creates a specific Lion. and defines his attributes.
     */
    public Lion(int size, String col, int horSpeed, int verSpeed, ZooPanel pan)
    {
        super("Lion", size, size*0.8, horSpeed, verSpeed, col, pan);
        setLocation(new Point(20,0));
        this.setDiet(new Carnivore());
        this.SetWeight(size*0.8);
        loadImages("lio");
    }

    /**
     * Constructor for the Lion's attributes. It creates a specific Lion. and defines his attributes.
     */
    public Lion(int size, String col, int x_dir, int y_dir, int horSpeed, int verSpeed, ZooPanel pan)
    {
        super("Lion", size, size*0.8, horSpeed, verSpeed, col, pan);
        setLocation(new Point(x_dir,y_dir));
        this.setDiet(new Carnivore());
        this.SetWeight(size*0.8);
        loadImages("lio");
    }
    public Lion(String s) { this(s,def_location);}
    public Lion(String s, Point p) { this(s,p,def_weight);}
    public Lion(String s, Point p,double w) { this(s,p,w,defScars);}
    public Lion(String s, Point p, double w, int scars)
    {
        super(s,p);
        this.SetWeight(w);
        this.setScars(scars);
        this.setDiet(new Carnivore());
    }

    //setters
    private boolean setScars(int s) { return true;}

    //getters
    public int getScars() {return this.scarCount;}
    @Override
    public EFoodType getFoodType() {return EFoodType.NOTFOOD;}

    //methods
    public void addScar()
    {
        Random rand = new Random();
        int guess = rand.nextInt(2);
        if (guess==1)
            setScars(this.scarCount+1);

    }
    public String toString()
    {
        return "[ Lion]: "+this.getAnimalName();
    }
}