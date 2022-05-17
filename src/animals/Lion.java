package animals;
import java.util.Random;

/**
 * Java project: W3
 * @author  Shaked Asido: 315853150.
 *          Tomer handali 206751489
 * @campus: Ashdod.
 */
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
        this.SetDiet(new Carnivore());
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
        this.SetDiet(new Carnivore());
        this.SetWeight(size*0.8);
        loadImages("lio");
    }

    public Lion(String s)
    {
        this(s,def_location);

    }

    public Lion(String s, Point p)
    {
        this(s,p,def_weight);

    }

    @Override
    public EFoodType getFoodType() {return EFoodType.NOTFOOD;}

    public Lion(String s, Point p,double w)
    {
        this(s,p,w,defScars);

    }
    public Lion(String s, Point p, double w, int scars)
    {
        super(s,p);


        this.SetWeight(w);
        this.setScars(scars);
        this.setDiet(new Carnivore());
    }

    public void addScar()
    {
        Random rand = new Random();
        int guess = rand.nextInt(2);
        if (guess==1)
            setScars(this.scarCount+1);

    }

    private boolean setScars(int s)
    {

        return true;
    }

    public int getScars() {return this.scarCount;}

    public String toString()
    {
        return "[ Lion]: "+this.getAnimalName();
    }

}