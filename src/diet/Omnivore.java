package diet;
import food.*;
import animals.*;
/**
 * A class that defines everything eater, implements the IDiet interface.
 * Animals that eats everything, will be called Omnivores.
 * @version 17/05/2022
 * @author  Tomer handali.
 * @see     IDiet
 */
public class Omnivore implements IDiet {

    /**
     * Gets element named food. checks if its actually food
     * @param food
     *        element from EFoodType enum to compare.
     * @return boolean result.
     */
    public boolean canEat(EFoodType food)
    {
        Carnivore c = new Carnivore();
        Herbivore h = new Herbivore();

        return c.canEat(food) || h.canEat(food);
    }

    /**
     * Gets animal and food type.
     * Check if it's the correct type of food that the animal eats.
     * If yes, The animal eats, and it gains wight.
     * else, it doesn't gain any weight.
     * @param animal
     *        represent the type of meat eater animal.
     * @param food
     *        represent the food type of herbivore.
     *
     * @return The gain that the animal added to its weight as double.
     */
    public double eat(Animal animal, IEdible food)
    {
        Carnivore c=new Carnivore();
        Herbivore h = new Herbivore();

        if(c.canEat(food.getFoodType()))
            return c.eat(animal, food);
        else if(h.canEat(food.getFoodType()))
            return h.eat(animal, food);

        return 0;
    }

    public String toString() { return "[Omnivore]";}

}
