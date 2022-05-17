package diet;

import food.*;
import animals.*;

/**
 * A class that defines meat eater, implements the IDiet interface.
 * Animals that are only meat eaters, will be called Carnivores.
 * @version 04/04/2022
 * @author  Tomer handali.
 * @see     IDiet
 */
public class Carnivore implements IDiet {
    public boolean canEat(EFoodType food) { return food == EFoodType.MEAT;}

    public double eat(Animal animal, IEdible food)
    {
        if(!canEat(food.getFoodType()))
            return 0;

        return animal.getWeight()*0.1;
    }

    public String toString() { return "[Carnivore]";}


}
