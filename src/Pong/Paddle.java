package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable{
	Paddle p1;
	Paddle p2;
	int x, y, id;
	int yDirection;
	int xDirection;
	Rectangle paddle;
	int cord = 300;

	public Paddle(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
		paddle = new Rectangle(x, y, 10, 50);
		}
		
	public void keyPressed(KeyEvent e) {
		int p = Ball.p;
		if (id == 1) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(-p);
			}if(e.getKeyCode() == KeyEvent.VK_DOWN) {		
				setYDirection(p);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if (id == 1) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
			}if(e.getKeyCode() == KeyEvent.VK_DOWN) {		
				setYDirection(0);
			}
		}
	}
	
	public void pc() {
		int l = Ball.l;
		int yDire = Ball.yDirection;
		cord = cord + yDire;
		if (id == 2) {
			if (paddle.y < cord) {
				setYDirection(+l);
				if (paddle.y >= 470) {
					setYDirection(-l);
				}
				if (paddle.y <= 80) {
			 		setYDirection(+l);
			 	}
			}
			if (paddle.y > cord) {
				setYDirection(-l);
				if (paddle.y >= 470) {
					setYDirection(-l);
				}
				if (paddle.y <= 80) {
			 		setYDirection(+l);
			 	}
			}
			if(paddle.y == cord) {
				setYDirection(0);
			}
		}
	}
	
	public void setYDirection(int yDir) {
		yDirection = yDir;
	}
	
	public void move() {
		int p = Ball.p;
	 	paddle.y += yDirection;
	 	if (paddle.y <= 80) {
	 		setYDirection(+p);
	 	}
	 	if (paddle.y >= 470) {
			setYDirection(-p);
		}
	 }
	public void draw(Graphics g) {
		if (id == 1) {
			g.setColor(Color.WHITE);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
		}
		if (id == 2) {
			g.setColor(Color.WHITE);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
		}	
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				move();
				Thread.sleep(7);
			}
		} catch(Exception e) { System.err.println(e.getMessage()); }
	}
}