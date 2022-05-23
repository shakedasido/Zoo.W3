package graphics;
import java.awt.Graphics;

/**
 * An interface that includes functions that describes the functionality of drawing a picture on the screen.
 *
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see ZooPanel
 */
public interface IDrawable {
    String PICTURE_PATH = "src\\photos\\";
    void loadImages(String nm);
    void drawObject (Graphics g);
    String getColor();

}