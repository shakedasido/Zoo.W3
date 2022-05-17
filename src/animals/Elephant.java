package animals;
import diet.Herbivore;
import food.EFoodType;
import graphics.ZooPanel;
import mobility.Point;



/**
 * A class representing an Elephant, which it is kind of animal, vegetable eater.
 * Extends class Animal.
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali..
 * @see     Animal
 */
public class Elephant extends Animal
{
    private double trunkLength= 1;

    /**
     * Constructor for the Elephant's name. It creates a specific Elephant. and defines his location.
     */
    public Elephant(int size, String col, int horSpeed, int verSpeed, ZooPanel pan)
    {
        super("Elephant", size, size*10, horSpeed, verSpeed, col, pan);
        setLocation(new Point(50, 90));
        this.SetDiet(new Herbivore());
        this.SetWeight(size*10);
        loadImages("elf");
    }
    /**
     * Constructor for the Elephant's name. It creates a specific Elephant. and defines his location.
     */
    public Elephant(int size , int x_dir, int y_dir, int horSpeed, int verSpeed, String col, ZooPanel pan)
    {
        super("Elephant", size, size*10, horSpeed, verSpeed, col, pan);
        setLocation(new Point(x_dir,y_dir));
        this.SetDiet(new Herbivore());
        this.SetWeight(size*10);
        loadImages("elf");
    }
    /**
     * Setter.
     * sets the attribute furColor, and print a massage with the function logSetter, from MassageUtilities class
     * @return boolean result.
     */
    public boolean setTrunkLength(double trunkLength)
    {
        boolean isSuccess = (trunkLength >= 0.5&&trunkLength <= 3);
        if (isSuccess) {this.trunkLength = trunkLength;}
        else {this.trunkLength = 1;}
        return isSuccess;
    }

    /**
     * Getter.
     * returns the attribute trunkLength, and print a massage with the function logGetter, from MassageUtilities class.
     * @return the value of the attribute trunkLength as double.
     */
    public double getTrunkLength() {return this.trunkLength;}

    @Override
    public EFoodType getFoodType() { return EFoodType.MEAT; }
}

