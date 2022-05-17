package animals;
import diet.Omnivore;
import food.EFoodType;
import graphics.ZooPanel;
import mobility.Point;


/**
 * A class representing a bear, all eater, which is kind of animal.
 * Extends class Animal.
 * @version 28/04/2022
 * @author  Shaked Asido.
 * @see     Animal
 */
public class Bear extends Animal {

    private String furColor;


    /**
     * Constructor for the bear's attributes. It creates a specific bear. and defines his attributes.
     */

    public Bear(int size, String col, int horSpeed, int verSpeed, ZooPanel pan)
    {
        super("Bear", size, size*1.5, horSpeed, verSpeed, col, pan);
        setLocation(new Point(100, 5));
        this.SetDiet(new Omnivore());
        this.SetWeight(size*1.5);
        loadImages("bea");
        furColor = "GREY";
    }


    /**
     * Constructor for the bear's attributes. It creates a specific bear. and defines his attributes.
     */
    public Bear(int size , int x_dir, int y_dir, int horSpeed, int verSpeed, String col, ZooPanel pan)
    {
        super("Bear", size, size*1.5, horSpeed, verSpeed, col, pan);
        setLocation(new Point(x_dir,y_dir));
        this.SetDiet(new Omnivore());
        this.SetWeight(size*1.5);
        loadImages("bea");
        furColor = "GREY";
    }


    /**
     * Getter.
     * @return the value of the attribute furColor as string .
     */
    public String GetFurColor() {return this.furColor;}

    /**
     * Setter.
     * @return boolean result.
     */
    public boolean setFurColor(String furColor) {
        for(FurColor color : FurColor.values()){
            if(color.toString().equals(furColor.toUpperCase()))
            {
                this.furColor = furColor;
                return true;
            }
        }
        this.furColor = "GREY";
        return false;
    }

    public EFoodType getFoodType() {return EFoodType.MEAT;}


}
