

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JFrame
{
	
	final static int WIDTH = 1280;
	final static int HEIGHT = 720;
	Engine engine;

	public Main(int screenWidth, int screenHeight) throws FontFormatException,
			IOException
	{		
		super("MainScreen");
		this.setSize(screenWidth,screenHeight);
		

		engine = new Engine();
		
		getContentPane().add(new DrawingPanel(), BorderLayout.CENTER);
		setVisible(true);
		
	}


	public static void main(String[] args) throws IOException,
			FontFormatException
	{
		Main mainScreen = new Main(WIDTH, HEIGHT);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}


	private class DrawingPanel extends JPanel implements ActionListener
	{

		MouseHandler mouse;
		Timer timer;

		public DrawingPanel()
		{
			this.setFocusable(true);

			mouse = new MouseHandler();
			this.addMouseListener(mouse);
			this.addMouseMotionListener(mouse);
			this.addKeyListener(new KeyHandler());
			setBackground(Color.black);
			setResizable(false);

			// The game updates every time the timer finishes
			timer = new Timer(30, this);
			timer.start();
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			engine.draw(g);
		}

		public void actionPerformed(ActionEvent e)
		{
			engine.run();
			repaint();
		}
	}
	

	private class MouseHandler extends MouseAdapter
	{
	
		public void mousePressed(MouseEvent event)
		{
			engine.mousePressed(event);
		}

		public void mouseReleased(MouseEvent event)
		{
			engine.mouseReleased(event);
		}

		public void mouseMoved(MouseEvent event)
		{
			engine.mouseMoved(event);
		}
		
		public void mouseDragged(MouseEvent event)
		{
			engine.mouseDragged(event);		
		}
	}

	
	private class KeyHandler extends KeyAdapter
	{
		
		public void keyPressed(KeyEvent event)
		{
			engine.keyPressed(event);
		}
		
	}

}
