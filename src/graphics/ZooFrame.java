package graphics;
/**
 * Java project: W3
 * @author  Shaked Asido: 315853150
 *          Tomer handali: 206751489
 * @campus: Ashdod.
 */
import java.awt.*;
import javax.swing.*;

/**
 * A class that activates a frame, with panels of choices- using GUI.
 *
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see ZooFrame
 */
public class ZooFrame extends JFrame {

    public ZooFrame() {

        setTitle("Zoo");
        setSize(1000, 650);
        ZooPanel zooPanel=new ZooPanel();
        add(zooPanel);
        Thread thread1 = new Thread(zooPanel);
        thread1.start();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            ZooFrame open = new ZooFrame();
            open.setVisible(true);
        });
    }
}
