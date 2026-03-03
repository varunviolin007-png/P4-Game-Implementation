package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class FlappyBird extends Game {
	static int counter = 0;
	boolean gameOver = false;
  	Bird bird;
  	ArrayList<Pipe> pipes = new ArrayList<Pipe>();

  public FlappyBird() {
	super("FlappyBird",800,600);
    this.setFocusable(true);
	this.requestFocus();
	
	this.addKeyListener(new KeyAdapter() {

	    public void keyPressed(KeyEvent e) {

	        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
	            bird.jump();
	        }

	    }

	});
	
    bird = new Bird();
    pipes.add(new Pipe(600,0));
    pipes.add(new Pipe(900,0));
    pipes.add(new Pipe(1200,0));
    pipes.add(new Pipe(1500,0));

    pipes.add(new Pipe(600,400));
    pipes.add(new Pipe(900,400));
    pipes.add(new Pipe(1200,400));
    pipes.add(new Pipe(1500,400));
  }
  
  public class Bird {

	    Point position;
	    double wingAngle = 0;
	    boolean wingUp = true;
	    double velocity = 0;
	    double gravity = 9.8;

	    public Bird() {
	        position = new Point(200,300);
	    }
	    
	    public void jump() {
	        velocity = -4.9;  
	    }
	    
	    public void fall() {
	        velocity += gravity * 0.016;
	        position.y += velocity;
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
  
  public boolean checkCollision() {

	    Rectangle birdBox = new Rectangle(
	        (int)bird.position.x,
	        (int)bird.position.y,
	        30,
	        30
	    );

	    for(Pipe p : pipes) {

	        Rectangle pipeBox = new Rectangle(
	            (int)p.position.x,
	            (int)p.position.y,
	            p.width,
	            p.height
	        );

	        if(birdBox.intersects(pipeBox)) {
	            return true;
	        }

	    }

	    return false;
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

  	    position.x -= 3;

  	    if(position.x < -width) {
  	        position.x = 800;   // send pipe back to the right side
  	    }
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
    	
        if(!gameOver) {
            bird.fall();
            for(Pipe p : pipes) {
                p.move();
            }
        }
    	
    	bird.draw(brush);
    	for(Pipe p : pipes) {
            p.draw(brush);
        }
    	
        if(checkCollision()) {
        	gameOver = true;
            brush.setColor(Color.red);
            brush.drawString("GAME OVER", width/2, height/2);
        }
        
  }
  
	public static void main (String[] args) {
   		FlappyBird a = new FlappyBird();
		a.repaint();
  }
}