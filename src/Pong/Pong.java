package Pong;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Pong extends JFrame {
	private static final long serialVersionUID = 5307087227887701350L;
	//screen size variables.
	int gWidth = 800;
	int gHeigh = 600;
	Dimension screenSize = new Dimension(gWidth, gHeigh);
	Image dbImage;
	Graphics dbGraphics;
	//ball object
	static Ball b = new Ball(400, 300);
	//constructor for window
	public Pong() {
		this.setTitle("PingPong");
		this.setSize(screenSize);
		this.setResizable(false);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(new AL());
		JOptionPane.showMessageDialog(null,"Sie sind linker Spieler es geht auf 10 Punkte! Viel Spass");
	}
	
	public static void main(String[] args) {
		new Pong();
		//create and start threads.
		Thread ball = new Thread(b);
		ball.start();
		Thread p1 = new Thread(b.p1);
		Thread p2 = new Thread(b.p2);
		p2.start();
		p1.start();
	}
	
	@Override
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbGraphics = dbImage.getGraphics();
		draw(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void draw(Graphics g) {
	    final BasicStroke stroke = new BasicStroke(5.0f);
	    final BasicStroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
		b.draw(g);
		b.p1.draw(g);
		b.p2.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("Powerup "+Ball.p, 12, 120);
		g.drawString("Powerup "+Ball.l, 727, 120);
		Font test = new Font("Arial",Font.BOLD,60);
		g.setFont(test);
		g.drawString(""+b.powerup, 385, 580);
		g.drawString(""+b.p1score, 360, 75);
		g.drawString(""+b.p2score, 410, 75);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(stroke);
        g2.setStroke(dashed);
		g2.drawLine(80,520,80,80);
		g2.drawLine(720,520,720,80);
		g2.drawLine(80,80,720,80);
		g2.drawLine(80, 520, 720, 520);
		g2.drawLine(400, 0, 400, 520);
		repaint();
	}
	
	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			b.p1.keyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			b.p1.keyReleased(e);
		}
	}
}