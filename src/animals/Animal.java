package animals;

/**
 * Java project: W3
 * @author  Shaked Asido: 315853150
 *          Tomer handali: 206751489
 * @campus: Ashdod.
 */
import graphics.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import diet.*;
import food.*;
import mobility.*;


/**
 * Abstract class that defines the common features for all animals The class expands Mobile and implements IEdible,
 * IDrawable, IAnimalBehavior.
 *
 * @version 18/05/2022
 * @author  Shaked Asido, Tomer handali.
 * @see Lion
 */
public abstract class Animal extends Mobile implements Runnable, IEdible, IDrawable, IAnimalBehavior{

    private String name;
    private double weight=0;
    private IDiet diet;
    private final int EAT_DISTANCE = 10;
    private int size;
    private String col;
    private int horSpeed;
    private int verSpeed;
    private boolean coordChanged;
    private int x_dir=1;
    private int y_dir=1;
    private int eatCount;
    private ZooPanel pan;
    private BufferedImage img1, img2;
    protected Thread thread;
    protected boolean threadSuspended = false;
    private int new_x, new_y;

    public Animal(String kind, int size, double weight, int horSpeed, int verSpeed, String col, ZooPanel pan)
    {
        super(new Point(0,0));
        this.name = kind;
        this.size=size;
        this.weight = weight;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.col = col;
        this.pan = pan;
        thread = new Thread(this);
        this.pan.repaint();
        new_x =0;
        new_y =0;
    }

    public Animal(String s, Point p)
    {
        super(p);
        setName(s);

    }

    public Thread getThread() { return this.thread;}
    public void setThread(Thread t) { thread=t;}
    public void setX_dir(int x) { x_dir = x;}

    public void setY_dir(int y) { y_dir = y;}

    private boolean setName(String s) {
        this.name=s;
        return true;}
    public double getWeight() { return this.weight;}

    public String getAnimalName() {return this.name;}

    public boolean SetWeight(double weight)
    {
        boolean isSuccess = (weight >= 0);
        if (isSuccess) { this.weight = weight;}
        else
        {
            this.weight = 0;
        }
        return isSuccess;
    }

    protected boolean setDiet(IDiet d)
    {
        this.diet=d;

        return true;

    }

    public boolean eat(IEdible other)
    {
        boolean x=true;
        double wad=diet.eat(this, other);

        if(wad==0)
            x = false;
        else
        {
            this.SetWeight(this.weight+wad);
        }


        return x;
    }

    public double move(Point p)
    {
        double distance =super.move(p);

        double weight =this.getWeight();

        this.SetWeight(weight-(distance*weight*0.00025));

        this.coordChanged=true;

        boolean x= (distance != 0);

        return distance;
    }
    public abstract EFoodType getFoodType();

    public int getEatDistance() {return this.EAT_DISTANCE;}

    @Override
    public String getColor() {return col;}

    @Override
    public int getEatCount() {return eatCount;}

    @Override
    public void eatInc() {eatCount++;}

    public int getSize() {return this.size;}

    @Override
    public boolean getChanges() {return this.coordChanged;}
    public void setChanges (boolean state) {this.coordChanged=state;}

    public int getHorSpeed() {return this.horSpeed;}
    public int getVerSpeed() {return this.verSpeed;}

    //nm=name
    @Override
    public void loadImages(String nm) {
        String color = this.col;
        switch(color) {
            case "Red":
                try {
                    img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_r_1.png"));
                    img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_r_2.png"));
                }
                catch (IOException e) { System.out.println("Cannot load image"); }
                break;
            case "Blue":
                try {
                    img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_b_1.png"));
                    img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_b_2.png"));
                }
                catch (IOException e) { System.out.println("Cannot load image"); }
                break;
            case "Natural":
                try {
                    img1 = ImageIO.read(new File(PICTURE_PATH + nm+ "_n_1.png"));
                    img2 = ImageIO.read(new File(PICTURE_PATH + nm+ "_n_2.png"));
                }
                catch (IOException e) { System.out.println("Cannot load image"); }
                break;
            default:
                System.out.println("Cannot load image");
        }
    }

    public boolean isChange() {return this.coordChanged;}

    public void resetChange() {this.coordChanged=false;}

    public void drawObject (Graphics g)
    {

        if(x_dir==1) // animal goes to the right side
            g.drawImage(img1, this.getLocation().GetX()-size/2, this.getLocation().GetY() -size/10, size, size, pan);
        else // animal goes to the left side
            g.drawImage(img2, this.getLocation().GetX(), this.getLocation().GetY() -size/10, size, size, pan);
    }

    public boolean SetDiet(IDiet diet)
    {
        this.diet=diet;
        return true;
    }


    @Override
    public void setSuspended() { this.threadSuspended = true;}

    @Override
    synchronized public void setResumed() { this.threadSuspended = false;}

    public void start() { thread.start(); } //activates run()

    public void interrupt() { thread.interrupt(); }

    @Override
    public void run() {

        while(true)
        {
            if(threadSuspended)
            {
                synchronized (this)
                {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }

            try {
                thread.sleep(100);
            } catch (InterruptedException e) {return;}

            ///(800, 600) is a point which is the limit boundaries of a point(determinate in Point)
            if (new_x <=800 && new_y <=600 && new_x >=0 && new_y >=0)
            {
                new_x = this.getLocation().GetX()+this.x_dir*this.horSpeed;
                new_y = this.getLocation().GetY()+this.y_dir*this.verSpeed;
                this.move(new Point(new_x, new_y));
            }
            else
            {
                if (new_x >800) { this.setX_dir((-x_dir));
                    new_x = this.getLocation().GetX()+this.x_dir*this.horSpeed;}
                if (new_y >600) { this.setY_dir((-y_dir));
                    new_y = this.getLocation().GetY()+this.y_dir*this.verSpeed;}
                if (new_x <0) { this.setX_dir((-x_dir));
                    new_x = this.getLocation().GetX()+this.x_dir*this.horSpeed;}
                if (new_y <0) { this.setY_dir((-y_dir));
                    new_y = this.getLocation().GetY()+this.y_dir*this.verSpeed;}
            }
        }

    }

}
