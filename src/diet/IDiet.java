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
    boolean canEat(EFoodType food);
    double eat(Animal animal, IEdible food);}