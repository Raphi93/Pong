package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JOptionPane;


public class Ball implements Runnable {
	//global variables
	int x, y, xDirection;
	static int yDirection;
	int p1score, p2score;
	Paddle p1 = new Paddle(90, 300, 1);
	Paddle p2 = new Paddle(700, 300, 2);
	Rectangle ball;
	static int l = 1;
	static int p = 1;
	int powerup = 0;
	
	//test
	public Ball(int x, int y){
		p1score = 0;
		p2score = 0;
		this.x = x;
		this.y = y;
		//Set ball moving randomly
		Random r = new Random();
		int rXDir = r.nextInt(p);
		if (rXDir == 0)
			rXDir--;
		setXDirection(rXDir);
		int rYDir = r.nextInt(p);
		if (rYDir == 0)
			rYDir--;
		setYDirection(rYDir);
		//create "ball"
		ball = new Rectangle(this.x, this.y, 15, 15);
	}///Sadsadasdsadasd
	
	public void winner(String[] args) {
		if (p1score == 10) {
			JOptionPane.showMessageDialog(null,"Du hast Gewonnen Herzlichen Glückwunsch");
	        p1score = 0;
		}
		if (p2score == 10) {
			JOptionPane.showMessageDialog(null,"Du hast Verloren Try Again");
	        p2score = 0;
		}
		if (powerup == 10) {
			p = p + 1;
			l = l + 1;
			powerup = 0;
		}
		if (l >= 6) {
			l = 5;
		}
		if (p >= 7){
			p = 6;
		}
	}
	
	public void setXDirection(int xDir){
		xDirection = xDir;
	}
	
	public void setYDirection(int yDir){
		yDirection = yDir;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
	}
	
	public void collision(){
        if(ball.intersects(p1.paddle))
            setXDirection(+p);
        if(ball.intersects(p2.paddle))
            setXDirection(-p);
	}
	
	public void powerup () {
		if(ball.intersects(p1.paddle) == true) {
			powerup = powerup +1;
		}else if(ball.intersects(p2.paddle) == true) {
			powerup = powerup +1;
		}
	}
	
	public void move() {
		collision();
		powerup();
		ball.x += xDirection;
		ball.y += yDirection;
		//bounce the ball when it hits the edge of the screen
		if (ball.x <= 80) {
			setXDirection(+p);
			p2score++;
			powerup = 0;
	}
		if (ball.x >= 720) {
			setXDirection(-p);
			p1score++;
			powerup = 0;
		}
		if (ball.y <= 80) {
			setYDirection(+p);
		}
		if (ball.y >= 505) {
			setYDirection(-p);
		}
	}	
	
	@Override
	public void run() {
		try {
			while(true) {
				move();
				p2.pc();
				winner(null);
				Thread.sleep(8);
			}
		}catch(Exception e) { System.err.println(e.getMessage()); }

	}
}