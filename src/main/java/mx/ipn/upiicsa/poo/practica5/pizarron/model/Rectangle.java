package mx.ipn.upiicsa.poo.practica5.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle extends Figura{
	private static final int DEFAULT_WIDTH = 150;
	private static final int DEFAULT_HEIGHT = 100;	
	private static final Color DEFAULT_BORDER_COLOR = Color.PINK;
	private static final Color DEFAULT_FILL_COLOR = Color.BLACK;
	private int width;
	private int height;
	
	protected Rectangle(int x, int y) {
		super(x, y);
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		fillColor = DEFAULT_FILL_COLOR;
		borderColor = DEFAULT_BORDER_COLOR;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.borderColor);
		g2d.drawRect(this.x, this.y, this.width, this.height);
		g2d.setColor(this.fillColor);
		g2d.fillRect(this.x+1, this.y+1, this.width-1, this.height-1);
	}
	
	public static Rectangle getDefault(int x, int y) {
		return new Rectangle(x, y);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}