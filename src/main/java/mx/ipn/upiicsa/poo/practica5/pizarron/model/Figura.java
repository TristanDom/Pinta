package mx.ipn.upiicsa.poo.practica5.pizarron.model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	protected int x;
	protected int y;
	protected int stroke;
	protected Color borderColor;
	protected Color fillColor;
	
	protected Figura(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getStroke() {
		return stroke;
	}
	public void setStroke(int stroke) {
		this.stroke = stroke;
	}
	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public abstract void paint(Graphics g);
}
