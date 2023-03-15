import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener {

	/* Attributes a.k.a. Instance Variables */
	int w = 800, h = 800;
	int x,y;

	public void paint(Graphics g) {
		super.paintComponent(g);
		clover(g,100,50,50);
		sundayDavid(g,360,100,300,100);
		squares(g,500,50,200);
		snowflake(g,200,400,100,6);
		triangle(g,400,600,300);
		

	}// end of paint method - put code above for anything dealing with drawing -

	public void clover(Graphics g, int radius, int x, int y) {
		g.drawOval(x, y, radius, radius);
		g.drawOval(x+radius, y+radius, radius, radius);
		g.drawOval(x+radius, y, radius, radius);
		g.drawOval(x, y+radius, radius, radius);
		
		if (radius > 0) {
			clover(g,radius-5,x+5,y+5);
		}
	
	

	}
	
	public void triangle(Graphics g,double x, double y, double size) {
		if (size< 1) return;
		g.drawLine((int) x, (int) y, (int)(x+size), (int) y);
		g.drawLine((int) x, (int) y, (int)(x+size/2), (int) (y-size/2*1.732));
		g.drawLine((int)(x), (int)(y), (int)(x+size/2), (int)(y-size/2*1.732));
		triangle(g,x,y,size/2);
		triangle(g,(int)(x+size/4),(int)(y-size/4*1.732),size/2);
		triangle(g,x+size/2,y,size/2);
	}
	
	public void sundayDavid(Graphics g, int n,int radius, int px, int py) {
		x = (int) (Math.cos(Math.toRadians(n-10))*((radius)/2));
		y = (int) (Math.sin(Math.toRadians(n-10))*((radius)/2));
		x += px;
		y += py;
		g.drawOval(x,y,radius,radius);
		
		if (n > 0) {
			sundayDavid(g,n-(10),radius,px,py);
		}
	}
	
	public void snowflake(Graphics g, int x, int y, int spokes, int b) {
		if (spokes<=1) {
			return;
		}
		double theta = 0;
		for (int i = 0; i < b; i++) {
			int x1 = (int)(spokes*Math.cos(Math.toRadians(theta)));
			int y1 = (int)(spokes*Math.sin(Math.toRadians(theta)));
			g.drawLine(x, y, x+x1, y-y1);
			snowflake(g,x+x1,y+y1,spokes/3,b);
			theta += 360.0/b;
			
		}
	}

	public void rings(Graphics g, int radius, int x, int y) {
		// each method call draws one part of the fractal
		g.drawOval(x, y, radius, radius);
		
		if (radius > 1) {
			rings(g,radius-20,x+10,y+10);
		}

	}
	
	public void squares(Graphics g, int px, int py, int size) {
		if (size <= 1) {
			return;
		}

		size /= 3;
		g.fillRect(px+size,py+size,size,size);
		
		squares(g,px,py,size);
		squares(g,px+size,py,size);
		squares(g,px+2*size,py,size);
		
		squares(g,px,py+size,size);
		squares(g,px+size*2,py+size,size);
		
		squares(g,px,py+2*size,size);
		squares(g,px+size,py+2*size,size);
		squares(g,px+2*size,py+2*size,size);
	}

	/**
	 * Update the positions of the ball and paddle. Update the scores, counter
	 * and time
	 */
	public void update() {

	}// end of update method - put code above for any updates on variable

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	/* Instantiate any attributes here (instance variables */
	public Driver() {

		JFrame f = new JFrame();
		f.setTitle("MR DAVID ITS NOT PONG");
		f.setSize(w, h);
		f.setBackground(Color.BLACK);
		f.setResizable(false);

		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	Timer t;

}
