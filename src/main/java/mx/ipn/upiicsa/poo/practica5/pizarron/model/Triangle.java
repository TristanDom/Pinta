package mx.ipn.upiicsa.poo.practica5.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Figura{
	private static final Color DEFAULT_BORDER_COLOR = new Color(0, 0, 0);
	private static final Color DEFAULT_FILL_COLOR = Color.RED;
	
	protected Triangle(int x, int y) {
		super(x, y);
		this.borderColor = DEFAULT_BORDER_COLOR;
		this.fillColor = DEFAULT_FILL_COLOR;
	}

	@Override
	public void paint(Graphics g) {
		int xcateto[] = { x + 50, x + 100, x +1 };
		int ycateto[] = { y+1, y + 100, y + 100 };
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.borderColor);
		g2d.drawPolygon(xcateto, ycateto, 3);
		g2d.setColor(this.fillColor);
		g2d.fillPolygon(xcateto, ycateto, 3);
	}
	
	public static Triangle getDefault(int x, int y) {
		return new Triangle(x, y);
	}
}
