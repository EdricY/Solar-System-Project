import java.awt.*;
import java.util.*;
/**
 * Write a description of class Planet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Planet
{
    // instance variables - replace the example below with your own
    public int mass, diameter;
    private double x, y, velX, velY, orbitSpeed;
    Color color;
    double accel, dirX, dirY;
    double dist, closest=999, furthest=0;
    int period, pcounter;


    /**
     * Constructor for objects of class Planet
     */
    public Planet(double x, double y, double velX, double velY, int mass, int diameter, Color color, double orbitSpeed)
    {
        // initialise instance variables
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.mass = mass;
        this.diameter = diameter;
        this.color = color;
        this.orbitSpeed = orbitSpeed;
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public int getMass(){return mass;}
    public int getDiameter(){return diameter;}
    public boolean containsPt(int x, int y, double scale)
    {
        return (x>600+(getX()-diameter-600)*scale && x<600+(getX()+diameter-600)*scale && 
                y>400+(getY()-diameter-400)*scale && y<400+(getY()+diameter-400)*scale);
    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void update(int StarX, int StarY, int StarMass)
    {
        dist = Math.sqrt((StarX - x)*(StarX - x) + (StarY - y)*(StarY - y));
        closest = Math.min(dist,closest);
        furthest = Math.max(dist,furthest);
        accel = StarMass/dist/dist;

        dirX = (StarX-x)/dist;
        dirY = (StarY-y)/dist;
        //double a = Math.sqrt((dirX*dirX + dirY*dirY));
        velX += dirX * accel;
        velY += dirY * accel;
        x += velX;
        y += velY;
        pcounter += 1;
        if(x>600 && velX > 0 && x-velX<600)
        {
            period = pcounter;
            pcounter = 0;
        }
    }
    public void draw(Graphics g, double scale)
    {
        g.setColor(color);
        g.fillOval((int)(600+(x-diameter/2-600)*scale), (int)(400+(y-diameter/2-400)*scale),
                    (int)(diameter*scale), (int)(diameter*scale));
    }
    public void dispDesc(Graphics g, double scale)
    {
        g.setFont(new Font("Dialog", Font.PLAIN, 16));
        g.setColor(Color.YELLOW);
        g.drawString("Distance from star: " + (Math.round(dist*100.0)/100.0) * 1000000 + " kilometers", 
                    diameter+(int)(600+(x-diameter/2-600)*scale), 16+(int)(400+(y-diameter/2-400)*scale)+diameter);
        if(closest < 999)
        g.drawString("Closest Approach: " + (Math.round(closest*100.0)/100.0) * 1000000 + " kilometers", 
                    diameter+(int)(600+(x-diameter/2-600)*scale), 16+(int)(400+(y-diameter/2-400)*scale)+diameter+16);
        if(furthest > 0)
        g.drawString("Furthest Distance: " + (Math.round(furthest*100.0)/100.0) * 1000000 + " kilometers",
                    diameter+(int)(600+(x-diameter/2-600)*scale), 16+(int)(400+(y-diameter/2-400)*scale)+diameter+32);
        if(period > 40)
        g.drawString("Previous Year: " + period + " Earth Days",
                    diameter+(int)(600+(x-diameter/2-600)*scale), 16+(int)(400+(y-diameter/2-400)*scale)+diameter+48);
    }
}




