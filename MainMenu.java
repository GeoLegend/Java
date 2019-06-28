/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyFrame;

/*
* Name: Kevin Zhu and Samuel Deng
* Date: June 13, 2019
* Course: ICS 3U7 - 02
* Teacher: Mr.Anthony
* Assignment: Computer Science Final Project (Connect 4)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class MainMenu extends JFrame implements ActionListener {
	private final JLabel title = new JLabel("Welcome to Connect 4!");
	private int xValue;
	private int yValue;
	private int count = 0;
	private JButton vsPlayer = new JButton("Play vs Player");
	private JButton vsComputer = new JButton("Play vs Computer");
	private JButton quit = new JButton("Quit");
	MyFrame vsP = new MyFrame("Welcome to Connect 4");
	MyFrame2 vsC = new MyFrame2("Welcome to Connect 4");
	private Image backgroundImage;

	public MainMenu(String n) {
		super(n);
		setTitle("Welcome to Connect 4!");
		Container c = getContentPane();
		c.setLayout(null);
		try {
			backgroundImage = ImageIO.read(new File("back.png"));
		} catch (Exception e) {
		}
		vsPlayer.setSize(300, 100);
		vsPlayer.setLocation(100, 100);
		c.add(vsPlayer);
		vsComputer.setSize(300, 100);
		vsComputer.setLocation(100, 225);
		c.add(vsComputer);
		quit.setSize(300, 100);
		quit.setLocation(100, 350);
		c.add(quit);
		vsPlayer.addMouseListener(mouseListener);
		vsComputer.addMouseListener(mouseListener);
		quit.addMouseListener(mouseListener);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}

	public void actionPerformed(ActionEvent e) {
	}

	MouseListener mouseListener = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			xValue = e.getLocationOnScreen().x; 
			yValue = e.getLocationOnScreen().y;
			if (yValue >= 100 && yValue <= 225) {// Play Vs Player
				vsP.setSize(1600, 1000);
				vsP.setVisible(true);
				if (count == 0) {
					// Pop Up Window For Instructions.
					JOptionPane.showMessageDialog(vsP,
							"Instructions:\n 1. Decide who will play first. Players would alternate turns after playing a token.\n 2. On your turn, drop one of your tokens down any of the slots at the top of the grid or board. \n 3. Take turns playing the game until one player gets 4 checks in a row (Vertically, Horizontally, Diagonally). ");
				}
			} else if (yValue >= 225 && yValue <= 350) {// Play Vs Computer
				vsC.setSize(1600, 1000);
				vsC.setVisible(true);
				if (count == 0) {
					// Pop Up Window For Instructions.
					JOptionPane.showMessageDialog(vsP,
							"Instructions:\n 1. Take turns playing the game vs the computer until one player gets 4 checks in a row (Vertically, Horizontally, Diagonally). ");

				}
			} else if (yValue >= 350 && yValue <= 500) {// Quit button
				System.exit(0);
			}
			count++;
		}
	};

	/*
	 * Link: https://stackoverflow.com/questions/25171205/playing-sound-in-java
	 */
	public static void sound() {
		// From: https://opengameart.org/content/menu-music
		try {
			// Open an audio input stream.
			File soundFile = new File("backMusic.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
			while (true) {
				clip.loop(1);
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MainMenu JFrame = new MainMenu("Main menu");
		JFrame.setSize(500, 500); // Width of the screen, height of the screen
		JFrame.setVisible(true);
		JFrame.setResizable(false);
		JFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		sound();
	}
}
