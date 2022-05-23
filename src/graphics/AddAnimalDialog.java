package graphics;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A class that gets all the details of a new animal, and send it to the proper constructor- using GUI.
 *
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see ZooPanel
 */
public class AddAnimalDialog extends JDialog implements ItemListener, ActionListener {

    private final String[] animals = {"Elephant","Lion","Giraffe","Turtle","Bear"};
    private final String[] colors = {"Red","Blue", "Natural"};
    private JButton ok;
    private JSlider SizeSelection, HorzSelection, VerSelection;
    private String chosenAnimal, col;
    private JRadioButton[] Select1, Select2;
    private ZooPanel pan;


    public AddAnimalDialog(ZooPanel pan, String title)
    {
        super(new JFrame(),title,true);
        this.pan = pan;
        setSize(700,350);
        setLayout(new BorderLayout());
        AddAnimalToPanel();
    }

    public void AddAnimalToPanel()
    {

        JPanel AnimalChoice = new JPanel();
        JPanel sizeAndSpeedPanel = new JPanel();
        JPanel ColorChoice = new JPanel();
        JPanel KindAndColorPanel = new JPanel();
        JPanel OkAndCancelButtons = new JPanel();

        //Add Animal:
        //kind of animal
        AnimalChoice.setLayout(new GridLayout(3,2,15,20));
        AnimalChoice.setBorder(BorderFactory.createTitledBorder("Choose a kind of an animal: "));
        ButtonGroup AnimalsButton = new ButtonGroup();
        Select1 =new JRadioButton[animals.length];

        for(int i = 0; i<animals.length; i++)
        {
            Select1[i] = new JRadioButton(animals[i], false);
            Select1[i].addItemListener(this);
            AnimalsButton.add(Select1[i]);
            AnimalChoice.add(Select1[i]);
        }


        //color of an animal
        ColorChoice.setLayout(new GridLayout(1,3,5,30));
        ColorChoice.setBorder(BorderFactory.createTitledBorder("Choose a color: "));
        ButtonGroup colorsButton = new ButtonGroup();
        Select2 =new JRadioButton[colors.length];

        for(int i = 0; i<colors.length; i++)
        {
            Select2[i] = new JRadioButton(colors[i],false);
            Select2[i].addItemListener(this);
            colorsButton.add(Select2[i]);
            ColorChoice.add(Select2[i]);
        }
        Select1[0].setSelected(true);
        Select2[0].setSelected(true);

        //size of animal
        sizeAndSpeedPanel.setLayout(new GridLayout(2,3,15,-25));
        JLabel animalSize = new JLabel("Animal's size: ",JLabel.CENTER);
        sizeAndSpeedPanel.add(animalSize);

        //Horizontal speed of animal
        JLabel horAnimalSpeed = new JLabel("Horizontal speed: ",JLabel.CENTER);
        sizeAndSpeedPanel.add(horAnimalSpeed);


        //Vertical speed of animal
        JLabel verAnimalSpeed = new JLabel("Vertical speed: ",JLabel.CENTER);
        sizeAndSpeedPanel.add(verAnimalSpeed);

        //Scale of the size of the animal
        SizeSelection = new JSlider(50,300);
        SizeSelection.setMajorTickSpacing(50);
        SizeSelection.setMinorTickSpacing(10);
        SizeSelection.setPaintTicks(true);
        SizeSelection.setPaintLabels(true);
        sizeAndSpeedPanel.add(SizeSelection);

        //Scale of the Horizontal speed of the animal
        HorzSelection = new JSlider(1,10);
        HorzSelection.setMajorTickSpacing(1);
        HorzSelection.setPaintTicks(true);
        HorzSelection.setPaintLabels(true);
        sizeAndSpeedPanel.add(HorzSelection);

        //Scale of the Vertical speed of the animal
        VerSelection = new JSlider(1,10);
        VerSelection.setMajorTickSpacing(1);
        VerSelection.setPaintTicks(true);
        VerSelection.setPaintLabels(true);
        sizeAndSpeedPanel.add(VerSelection);


        OkAndCancelButtons.setLayout(new GridLayout(1,2,3,0));
        ok = new JButton("Ok");
        ok.addActionListener(this);
        ok.setBackground(new Color(167, 199, 231));  //light blue
        OkAndCancelButtons.add(ok);

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setBackground(new Color(167, 199, 231));
        OkAndCancelButtons.add(cancel);

        KindAndColorPanel.setLayout(new GridLayout(1,2,20,10));
        KindAndColorPanel.add(AnimalChoice);
        KindAndColorPanel.add(ColorChoice);

        add("North", KindAndColorPanel);
        add("Center" , sizeAndSpeedPanel);
        add("South" , OkAndCancelButtons);
    }

    public void itemStateChanged(ItemEvent e)
    {
        for(int i = 0; i< Select1.length; i++)
        {
            if(Select1[i].isSelected())
            {
                chosenAnimal = animals[i];
                break;
            }
        }

        for(int i = 0; i< Select2.length; i++) {
            if (Select2[i].isSelected()) {
                col = colors[i];
                break;
            }
        }


    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == ok) //the user typed on the "Ok" button
        {
            //add the animal to the animals list as the user required.
            pan.AddAnimal(chosenAnimal, SizeSelection.getValue(), HorzSelection.getValue(), VerSelection.getValue(), col);
            setVisible(false);
        }
        setVisible(false);
    }

}