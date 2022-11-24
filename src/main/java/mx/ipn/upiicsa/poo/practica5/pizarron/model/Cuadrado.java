package mx.ipn.upiicsa.poo.practica5.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cuadrado extends Figura{
	private static final int DEFAULT_SIDE = 100;
	private static final Color DEFAULT_BORDER_COLOR = new Color(0,0,0);
	private static final Color DEFAULT_FILL_COLOR = Color.GREEN;
	
	private int side;
	
	protected Cuadrado(int x, int y) {
		super(x, y);
		this.fillColor = DEFAULT_FILL_COLOR;
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.side = DEFAULT_SIDE;
}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.borderColor);
		g2d.drawRect(this.x, this.y, this.side, this.side);
		g2d.setColor(this.fillColor);
		g2d.fillRect(this.x+1, this.y+1, this.side-1, this.side-1);
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
	
	public static Cuadrado getDefault(int x, int y) {
		return new Cuadrado(x, y);
	}

}
