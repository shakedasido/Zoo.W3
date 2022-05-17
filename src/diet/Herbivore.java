package diet;

import food.*;
import animals.*;
/**
 * A class that defines plants eater, implements the IDiet interface.
 * Animals that are only plants eaters, will be called Herbivores.
 * @version 04/04/2022
 * @author  Shaked Asido.
 * @see     IDiet
 */
public class Herbivore implements IDiet {

    public boolean canEat(EFoodType food)
    {
        return food == EFoodType.VEGETABLE;
    }
    public double eat(Animal animal, IEdible food)
    {
        if(!canEat(food.getFoodType()))
            return 0;

        return animal.getWeight()*0.07;
    }

    public String toString()
    {
        return "[Herbivore]";

    }
}