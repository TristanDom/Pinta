package mx.ipn.upiicsa.poo.practica5.pizarron.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import mx.ipn.upiicsa.poo.practica5.pizarron.exception.DrawingException;
import mx.ipn.upiicsa.poo.practica5.pizarron.model.Circle;
import mx.ipn.upiicsa.poo.practica5.pizarron.model.Cuadrado;
import mx.ipn.upiicsa.poo.practica5.pizarron.model.Figura;
import mx.ipn.upiicsa.poo.practica5.pizarron.model.Pencil;
import mx.ipn.upiicsa.poo.practica5.pizarron.model.Text;
import mx.ipn.upiicsa.poo.practica5.pizarron.model.Triangle;

public class VentanaPizarron extends JFrame{
	private static final int TOOL_UNSELECT = -1;
	private static final int TOOL_CIRCLE = 1;
	private static final int TOOL_TRIANGLE = 2;
	private static final int TOOL_SQUARE = 3;
	private static final int TOOL_RECTANGLE = 4;
//	private static final int TOOL_POLYGON  = 5;
	private static final int TOOL_TEXT = 5;
//	private static final int TOOL_IMAGE  = 7;
	private static final int TOOL_PENCIL = 6;
//	private static final int TOOL_LINE = 9;
//	private static final int TOOL_SELECT = 10;
		
	private static final int DRAWING_ACTIVE = 1;
	private static final int DRAWING_INACTIVE = 2;

	
	private JPanel toolPanel;
	private JPanel dasboardPanel;
	private JPanel logPanel;
	
	private JButton btnCircle;
	private JButton btnTriangle;
	private JButton btnSquare;
	private JButton btnRectangle;
	private JButton btnText;
	private JButton btnPencil;
	
	private int selectedTool;
	private int drawingState;
	
	private Figura pencilTemp; 

	public VentanaPizarron() {
		initComponents();
		selectedTool= TOOL_UNSELECT;
		drawingState = DRAWING_ACTIVE;
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		instantiateComponents();
		buildLayout();
		initializeListener();
		
		setVisible(true);
	}

	private void initializeListener() {
		btnCircle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = TOOL_CIRCLE;
			}
		});
		
		btnTriangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = TOOL_TRIANGLE;
			}
		});
		
		btnSquare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = TOOL_SQUARE;
			}
		});
		
		btnRectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = TOOL_RECTANGLE;
			}
		});
		
		btnText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = TOOL_TEXT;
			}
		});
		
		btnPencil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTool = TOOL_PENCIL;
			}
		});
		
		dasboardPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(drawingState == DRAWING_ACTIVE && selectedTool == TOOL_PENCIL) {	
					pencilTemp = null;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(drawingState == DRAWING_ACTIVE && selectedTool == TOOL_PENCIL) {
					pencilTemp = getFigure(e.getX(), e.getY()); 
				}			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				System.out.println("Salió del dashboard");	
				drawingState = DRAWING_INACTIVE;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
//				System.out.println("Entró al dashboard");
				drawingState = DRAWING_ACTIVE;

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println("Clic en el dashboard");
				System.out.println("Dibujando");
				if (drawingState == DRAWING_ACTIVE) {
					Figura figura =  getFigure(e.getX(), e.getY()); 
				}
			}
		});
		
		dasboardPanel.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
//				System.out.println("Mouse moved en el dashboard");
			}
			
			@Override 
			public void mouseDragged(MouseEvent e) {
				if (drawingState == DRAWING_ACTIVE && selectedTool == TOOL_PENCIL) {
					Pencil lapiz = (Pencil) pencilTemp;
					lapiz.addPoint(e.getX(), e.getY());
					lapiz.paint(dasboardPanel.getGraphics());
				}
				}
		});
	}
	
	public Figura getFigure(int x, int y) {
		Figura figura = null;
//		String mensaje;
		System.out.println("Clic");
		try {
			figura = getFigureDraw(x, y);
			figura.paint(dasboardPanel.getGraphics());
//			mensaje="se dibujo una figura";
			System.out.println("Se agregó dibujo");
		} catch (DrawingException e1) {
//			mensaje="Exception";
			System.out.println("Error al dibujar");
		}finally{
			//Agregar mensaje de log
		}
		return figura;
	}
	
	private Figura getFigureDraw(int x, int y) throws DrawingException{
		Figura figura = null;
		if(selectedTool == TOOL_UNSELECT) {
			 throw new DrawingException();
		}else if(selectedTool == TOOL_CIRCLE) {
			 figura = Circle.getDefault(x, y);
		}else if(selectedTool == TOOL_PENCIL) {
			 figura = Pencil.getDefault(x, y);
		}else if(selectedTool == TOOL_RECTANGLE) {
			 figura = mx.ipn.upiicsa.poo.practica5.pizarron.model.Rectangle.getDefault(x, y);
		}else if(selectedTool == TOOL_SQUARE) {
			 figura = Cuadrado.getDefault(x, y);
		}else if(selectedTool == TOOL_TEXT) {
			 figura = Text.getDefault(x, y);
		}else if(selectedTool == TOOL_TRIANGLE) {
			 figura = Triangle.getDefault(x, y);
		}
		return figura;
	}

	private void buildLayout() {
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		System.out.println("--->"+ toolPanel);
		toolPanel.setLayout(new GridLayout(6, 1));
		toolPanel.add(btnCircle);
		toolPanel.add(btnTriangle);
		toolPanel.add(btnSquare);
		toolPanel.add(btnRectangle);
		toolPanel.add(btnText);
		toolPanel.add(btnPencil);
		
		pane.add(toolPanel, BorderLayout.WEST);
		pane.add(dasboardPanel, BorderLayout.CENTER);
		pane.add(logPanel, BorderLayout.SOUTH);
	}

	private void instantiateComponents() {
		toolPanel = new JPanel();
		dasboardPanel = new JPanel();
		dasboardPanel.setBackground(Color.WHITE);
		logPanel = new JPanel();
		
		try {
		File file = new File("/home/tripas/Escritorio/Materias/ProgramaciónOrientadaObjetos/2P/Íconos/circulo.png");
		Image image = ImageIO.read(file);
		image = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
		File file1 = new File("/home/tripas/Escritorio/Materias/ProgramaciónOrientadaObjetos/2P/Íconos/cuadrado.png");
		Image image1 = ImageIO.read(file1);
		image1 = image1.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
		File file2 = new File("/home/tripas/Escritorio/Materias/ProgramaciónOrientadaObjetos/2P/Íconos/rectangulo.png");
		Image image2 = ImageIO.read(file2);
		image2 = image2.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
		File file3 = new File("/home/tripas/Escritorio/Materias/ProgramaciónOrientadaObjetos/2P/Íconos/pencil.png");
		Image image3 = ImageIO.read(file3);
		image3 = image3.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
		File file4 = new File("/home/tripas/Escritorio/Materias/ProgramaciónOrientadaObjetos/2P/Íconos/text.png");
		Image image4 = ImageIO.read(file4);
		image4 = image4.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
		File file5 = new File("/home/tripas/Escritorio/Materias/ProgramaciónOrientadaObjetos/2P/Íconos/triangle.png");
		Image image5 = ImageIO.read(file5);
		image5 = image5.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
		btnCircle = new JButton(new ImageIcon(image));
		btnTriangle = new JButton(new ImageIcon(image5));
		btnSquare = new JButton(new ImageIcon(image1));
		btnRectangle = new JButton(new ImageIcon(image2));
		btnText = new JButton(new ImageIcon(image4));
		btnPencil = new JButton(new ImageIcon(image3));
		}catch(IOException e) {
			System.out.println("Error al insertar ícono");
		}finally{
			//Intertar mensaje
		}
		
//COlor de los botones
		
		btnCircle.setBackground(new Color(255, 255, 255));
		btnPencil.setBackground(new Color(255, 255, 255));
		btnSquare.setBackground(new Color(255, 255, 255));
		btnRectangle.setBackground(new Color(255, 255, 255));
		btnPencil.setBackground(new Color(255, 255, 255));
		btnText.setBackground(new Color(255, 255, 255));
		btnTriangle.setBackground(new Color(255, 255, 255));
	}
}