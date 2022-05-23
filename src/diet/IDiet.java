package diet;
import food.*;
import animals.*;
/**
 * An interface that describes the functionality of eating.
 * @version 04/04/2022
 * @author  Shaked Asido.
 * @see     Carnivore
 */
public interface IDiet {

    /**
     * Gets food. Check if the animal that use carnivore class is eat the same food.
     * @param food
     *        element from EFoodType enum to compare.
     * @return boolean result.
     */
    boolean canEat(EFoodType food);

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
    double eat(Animal animal, IEdible food);}