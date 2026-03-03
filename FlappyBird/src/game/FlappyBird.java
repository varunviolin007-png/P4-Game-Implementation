package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class FlappyBird extends Game {
	static int counter = 0;

  public FlappyBird() {
	super("FlappyBird",800,600);
    this.setFocusable(true);
	this.requestFocus();
  }
  
  public class Bird {

	    Point position;
	    double wingAngle = 0;
	    boolean wingUp = true;

	    public Bird() {
	        position = new Point(200,300);
	    }

	    public void update() {

	        if (wingUp) {
	            wingAngle += 5;
	            if (wingAngle > 30) wingUp = false;
	        } else {
	            wingAngle -= 5;
	            if (wingAngle < -30) wingUp = true;
	        }
	    }

	    public void draw(Graphics brush) {

	        brush.setColor(Color.yellow);
	        brush.fillOval((int)position.x,(int)position.y,30,30);

	        Graphics2D g2 = (Graphics2D) brush;

	        g2.translate(position.x + 15, position.y + 15);
	        g2.rotate(Math.toRadians(wingAngle));

	        brush.setColor(Color.orange);
	        brush.fillOval(-5,-5,15,10);

	        g2.rotate(-Math.toRadians(wingAngle));
	        g2.translate(-(position.x + 15), -(position.y + 15));
	    }
	}
  
  public class Pipe {

  	Point position;
  	int width = 60;
  	int height = 200;
  	int speed = 3;

  	public Pipe(int x, int y) {
  		position = new Point(x,y);
  	}


  	public void move() {
  		position.x -= speed;
  	}


  	public void draw(Graphics brush) {
  		brush.setColor(Color.green);
  		brush.fillRect((int)position.x,(int)position.y,width,height);
  	}
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
  }
  
	public static void main (String[] args) {
   		FlappyBird a = new FlappyBird();
		a.repaint();
  }
}