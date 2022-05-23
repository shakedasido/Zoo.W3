package graphics;

/**
 * Java project: W3
 * @author  Shaked Asido, Tomer handali.
 * @campus: Ashdod.
 */
import animals.*;
import food.EFoodType;
import food.Meat;
import mobility.Point;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that activates a panel of choices- using GUI.
 *
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see ZooFrame
 */
public class ZooPanel extends JPanel implements Runnable{
    private BufferedImage img= null;
    String PICTURE_PATH = "src\\photos\\"; //Enter your own path!!
    private final ArrayList<Animal> animalArrayList = new ArrayList<>(); //List of all the animals, max 10
    private int totalEatingAmount = 0;
    private EFoodType foodType = EFoodType.NOTFOOD;
    private Plant plant = null; // plant object
    private Meat meat=null; // meat object
    private final Color color = null; // color of panel
    private ZooFrame zooFrame;
    private boolean coordChanged;
    private Thread controller;


    public ZooPanel(){
        this.setLayout(new BorderLayout());
        controller = new Thread(this);
        controller.start();//check
        createMenuBar();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g) ;
        Graphics2D gr = (Graphics2D) g;
        if(img!=null)
        {
            gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gr.drawImage(img,0,20,getWidth(),getHeight(), this);
        }
        else
            g.setColor(this.color);

        synchronized(this) {
            for(Animal an : animalArrayList)
                an.drawObject(g);
        }

        if(this.plant!=null) //drawing a plant
            plant.drawObject(g);

        if(this.meat!=null) // drawing meat
            meat.drawObject(g);
    }

    private void createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        // menu
        JMenu fileMenu = new JMenu("File");
        JMenu backgroundMenu = new JMenu("Background");
        JMenu helpMenu = new JMenu("Help");

        //default- Savanna image will appear
        try {
            img = ImageIO.read(new File(PICTURE_PATH+"savanna.jpg"));
            repaint();
        }
        catch (IOException e) { System.out.println("Cannot load image"); }

        //Submenu: Exit
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        //Submenu: image
        JMenuItem image = new JMenuItem("Image");
        image.addActionListener(e -> {
            try {
                repaintToImage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        backgroundMenu.add(image);

        //Submenu: green
        JMenuItem green = new JMenuItem("Green");
        green.addActionListener(e -> repaintToGreen());
        backgroundMenu.add(green);

        //Submenu: None
        JMenuItem none = new JMenuItem("None");
        none.addActionListener(e -> repaintToNone());
        backgroundMenu.add(none);

        //Submenu: help
        JMenuItem Help = new JMenuItem("Help");
        Help.addActionListener(e -> JOptionPane.showMessageDialog(null, "Home Work 3 \nGUI"));
        helpMenu.add(Help);

        //activate the menu
        backgroundMenu.add(none);
        menuBar.add(fileMenu);
        menuBar.add(backgroundMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createHorizontalGlue());
        this.add(menuBar, BorderLayout.NORTH);

        //panel of buttons
        JPanel downMenu = new JPanel();
        downMenu.setLayout(new GridLayout(1,6,2,0));

        //buttons
        JButton addAnimal = new JButton("Add Animal");
        JButton sleep = new JButton("Sleep");
        JButton wakeUp = new JButton("Wake Up");
        JButton clear = new JButton("Clear");
        JButton food = new JButton("Food");
        JButton info = new JButton("Info");
        JButton exit = new JButton("Exit");

        //activate the downMenu
        downMenu.add(addAnimal);

        //add animal
        addAnimal.addActionListener(e -> ConnectToAddAnimalDialog());

        //sleep
        downMenu.add(sleep);
        sleep.addActionListener(e -> { SleepAnimal();});

        //Wake up
        downMenu.add(wakeUp);
        wakeUp.addActionListener(e -> {
            wakeUpAnimal();});

        //clear
        downMenu.add(clear);
        clear.addActionListener(e -> {
            for(Animal animal: animalArrayList) { animal.getThread().stop(); }
            animalArrayList.clear();
            foodType = EFoodType.NOTFOOD;
            plant = null;
            meat = null;
            totalEatingAmount = 0;
            repaint();
        });

        //food
        downMenu.add(food);
        food.addActionListener(e -> Food());

        //info
        info.addActionListener(e -> ConnectToInfoDialog());

        downMenu.add(info);

        //exit
        downMenu.add(exit);
        exit.addActionListener(e -> System.exit(0));

        //put the panel of buttons on the south of the frame
        this.add(downMenu,BorderLayout.SOUTH);

    }

    //open an image you choose and convert it into file
    public void repaintToImage() throws IOException {

        FileDialog fileDialog = new FileDialog((Frame) null, "Choose an image: ", FileDialog.LOAD);
        fileDialog.setDirectory(PICTURE_PATH);
        fileDialog.setVisible(true);
        String fileDirectory = fileDialog.getDirectory();
        String filename = fileDialog.getFile();
        if (filename != null)
        {
            File file = new File(fileDirectory, filename);
            try {
                img = ImageIO.read(file);
                repaint();
            } catch (IOException ex) { System.out.println("Cannot load image");}
        }
        else
            JOptionPane.showMessageDialog(null, "No image loaded");
        System.out.println("You cancelled the choice");
    }

    //creates a green screen
    public void repaintToGreen(){
        img = null;
        this.setBackground(new Color(34,139,34));
        repaint();
    }

    //creates a white screen
    public void repaintToNone(){
        img = null;
        this.setBackground(new Color(225,225,225));
        repaint();
    }

    public void ConnectToAddAnimalDialog()
    {
        int MAX_ANIMAL_AMOUNT = 10;
        if(animalArrayList.size()< MAX_ANIMAL_AMOUNT) {
            AddAnimalDialog animalAdded = new AddAnimalDialog(this,"Add an animal to the zoo: ");
            animalAdded.setVisible(true); }
        else {JOptionPane.showMessageDialog(
                null, "You cannot add more than "+ MAX_ANIMAL_AMOUNT +" animals");}
    }

    public void AddAnimal(String animal, int size, int horSpeed, int verSpeed, String col)
    {
        Animal newAnimal = switch (animal) {
            case "Elephant" -> new Elephant(size, col, horSpeed, verSpeed, this);
            case "Lion" -> new Lion(size, col, horSpeed, verSpeed, this);
            case "Turtle" -> new Turtle(size, col, horSpeed, verSpeed, this);
            case "Bear" -> new Bear(size, col, horSpeed, verSpeed, this);
            default -> new Giraffe(size, col, horSpeed, verSpeed, this);
        };
        animalArrayList.add(newAnimal);
        newAnimal.start();
    }

    public void ConnectToInfoDialog()
    {
        if(animalArrayList.size()>0) {
            InfoDialog info = new InfoDialog(this, animalArrayList);
            info.setVisible(true); }
        else { JOptionPane.showMessageDialog(null, "There is no animals to Present!"); }
        repaint();
    }

    public int getTotalEatingAmount() { return this.totalEatingAmount; }

    public void Food()
    {
        if(foodType == EFoodType.NOTFOOD){
            Object[] foodTypes = {"Lettuce", "Cabbage", "Meat" };
            int n = JOptionPane.showOptionDialog(zooFrame, "Please choose food", "Food for animals",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, foodTypes, foodTypes[2]);
            switch (n) {
                case 0 -> {
                    foodType = EFoodType.VEGETABLE;  // Lettuce
                    meat = null;
                    plant = new Lettuce(this);
                }

                case 1 -> {
                    foodType = EFoodType.VEGETABLE;  // Cabbage
                    meat = null;
                    plant = new Cabbage(this);
                }
                default -> {
                    foodType = EFoodType.MEAT;  // Meat
                    plant = null;
                    meat = new Meat(this);
                }
            }
        }
        else {
            foodType = EFoodType.NOTFOOD;
            plant = null; }

        repaint();
    }

    public Meat getMeat() { return this.meat;}
    public Plant getPlant() { return this.plant;}
    private void SleepAnimal() {
        for(Animal animal : animalArrayList)
            animal.setSuspended();
    }

    public void wakeUpAnimal() {
        for (Animal animal : animalArrayList) {
            synchronized (animal){
                animal.setResumed();
                animal.notify();
            }
        }
    }


    /**
     * This method will check each time an animal moves if they can eat an object that is near them
     */
    public void manageZoo()
    {
        synchronized(this)
        {
            for (Animal animal : animalArrayList) { // going through the entire array

                if (plant!=null && animal.getDiet().canEat(plant.getFoodType()))
                {
                    if(animal.getLocation().GetX()>plant.getLocation().GetX())
                        animal.setX_dir(-1);
                    else
                        animal.setX_dir(1);

                    if(animal.getLocation().GetY()>plant.getLocation().GetY())
                        animal.setY_dir(-1);
                    else
                        animal.setY_dir(1);

                    if(Math.abs(animal.getLocation().GetX()-plant.getLocation().GetX())<=animal.getHorSpeed() && animal.getHorSpeed()!=0)
                    {
                        animal.setLocation(new Point(plant.getLocation().GetX(),animal.getLocation().GetY()));

                        animal.setoldHspeed(animal.getHorSpeed());

                        animal.setHorSpeed(0);

                    }

                    if(Math.abs(animal.getLocation().GetY()-plant.getLocation().GetY())<=animal.getVerSpeed()&&animal.getVerSpeed()!=0)
                    {
                        animal.setLocation(new Point(animal.getLocation().GetX(),plant.getLocation().GetY()));

                        animal.setoldVspeed(animal.getVerSpeed());

                        animal.setVerSpeed(0);
                    }

                }

                if (meat!=null && animal.getDiet().canEat(meat.getFoodType()))
                {
                    if(animal.getLocation().GetX()>meat.getLocation().GetX())
                        animal.setX_dir(-1);
                    else
                        animal.setX_dir(1);

                    if(animal.getLocation().GetY()>meat.getLocation().GetY())
                        animal.setY_dir(-1);
                    else
                        animal.setY_dir(1);

                    if(Math.abs(animal.getLocation().GetX()-meat.getLocation().GetX())<=animal.getHorSpeed() && animal.getHorSpeed()!=0)
                    {
                        animal.setLocation(new Point(meat.getLocation().GetX(),animal.getLocation().GetY()));

                        animal.setoldHspeed(animal.getHorSpeed());

                        animal.setHorSpeed(0);

                    }

                    if(Math.abs(animal.getLocation().GetY()-meat.getLocation().GetY())<=animal.getVerSpeed()&&animal.getVerSpeed()!=0)
                    {
                        animal.setLocation(new Point(animal.getLocation().GetX(),meat.getLocation().GetY()));

                        animal.setoldVspeed(animal.getVerSpeed());

                        animal.setVerSpeed(0);
                    }

                }

                if(animal.getHorSpeed()==0 && plant==null && meat==null)
                    animal.setHorSpeed(animal.getoldHspeed());

                if(animal.getVerSpeed()==0&&plant==null&&meat==null)
                    animal.setVerSpeed(animal.getoldVspeed());

                if (animal.isChange()) // if there's a change in animal coordinates
                {
                    repaint();
                    animal.resetChange(); // resetting to no change so loop won't repeat this for no reason
                    System.out.println(animal.getAnimalName()+": "+animal.toString1());
                    //checking if plant can be eaten
                    if (plant != null && animal.calcDistance(plant.getLocation()) <= animal.getEatDistance() && animal.eat(plant)) {
                        animal.eatInc();
                        totalEatingAmount += 1;
                        plant = null;
                    }
                    //checking if meat can be eaten
                    if (meat != null && animal.calcDistance(meat.getLocation()) <= animal.getEatDistance() && animal.eat(meat)) {
                        animal.eatInc();
                        totalEatingAmount += 1;
                        if (animal instanceof Lion) {
                            ((Lion) animal).addScar();
                        }
                        meat = null;
                    }
                    //checking if other close animals can be eaten
                    for (Animal other : animalArrayList) {
                        if ( animal.eat(other) && animal != other && animal.getWeight() >= 2*(other.getWeight()) && animal.calcDistance(other.getLocation()) < other.getSize()) {
                            animal.eatInc();
                            totalEatingAmount -= (other.getEatCount()-1);
                            if (animal instanceof Lion) {
                                ((Lion) animal).addScar();
                            }
                            other.interrupt();
                            animalArrayList.remove(other); //removing eaten animal from array
                            return;
                        }
                    }
                }
            }
        }

        this.repaint(); //repainting panel
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(100);
                manageZoo();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
