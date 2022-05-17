package graphics;
import animals.Animal;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 * A class that creates a table, with all the data about the animals that exist in the system- using GUI.
 *
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see ZooPanel
 */
public class InfoDialog extends JDialog{

    private JScrollPane scrollPane = null;
    private ZooPanel pan;
    private ArrayList<Animal> animalArrayList = new ArrayList<>(); //List of all the animals, max 10

    public InfoDialog(ZooPanel pan, ArrayList<Animal> animalArrayList1)
    {
        super(new JFrame(),null,true);
        this.pan = pan;
        setSize(600,400);
        animalArrayList.addAll(animalArrayList1);
        setLayout(new BorderLayout());
        Indo();
    }

    public void Indo()
    {
        String[] TableCol = {"Animal", "Color", "Weight", "Hor. speed", "Ver. speed", "Eat counter"};
        String [][] TableData = new String[animalArrayList.size()+1][TableCol.length];//animalListSize+1: +1 For Total field

        int a=0;
        for(Animal animal : animalArrayList)
        {
            TableData[a][0] = animal.getAnimalName(); // "Animal"
            TableData[a][1] = animal.getColor();  // "Color"
            TableData[a][2] = String.valueOf(animal.getWeight());  // "Weight"
            TableData[a][3] = String.valueOf(animal.getHorSpeed());  // "Hor. speed"
            TableData[a][4] = String.valueOf(animal.getVerSpeed());  // "Ver. speed"
            TableData[a][5] = String.valueOf(animal.getEatCount());  // "Eat counter"
            a++; // Moves to the next column in the table
        }
        TableData[a][0] = "Total";
        TableData[a][5] = String.valueOf(pan.getTotalEatingAmount()) ;

        //Creates a table from all the collected data
        JTable table = new JTable(TableData, TableCol);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setSize(300,table.getRowHeight()*(animalArrayList.size()+1)+20); //+1 for Total
        this.add( scrollPane, BorderLayout.CENTER );
        scrollPane.setVisible(true);
    }
}