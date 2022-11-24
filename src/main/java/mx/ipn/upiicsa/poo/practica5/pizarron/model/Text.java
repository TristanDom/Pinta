package mx.ipn.upiicsa.poo.practica5.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Text extends Figura{
	private static final Color TEXT_COLOR = Color.BLUE;	
	private static String DEFAULT_TEXT = "some text";
	private String value;
	
	protected Text(int x, int y) {
		super(x, y); 
		fillColor = TEXT_COLOR;
		value = DEFAULT_TEXT;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(fillColor);
		g2d.drawString(value, this.x, this.y);
	}
	
	public static Text getDefault(int x, int y) {
		return new Text(x, y);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	
	
}
