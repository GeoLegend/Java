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
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

class MyFrame2 extends JFrame implements ActionListener {
	private final JLabel title = new JLabel("Welcome to Connect 4");
	private int xValue;
	private int yValue;
	private Game game;
	private JButton[][] buttonArray = new JButton[6][7];
	public MyFrame2(String n) {
		super(n);
		setTitle("Welcome to Connect 4");
		Container c = getContentPane();
		c.setLayout(null);
		game = new Game();
		MouseListener mouseListener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int response, response2;
				xValue = e.getLocationOnScreen().x; // if mouse is clicked set x and y coords of the ball to be wherever
													// the mouse is when clicked
				yValue = e.getLocationOnScreen().y;
				game.makeMoveVsComputer(xValue, yValue);
				sound();
				repaint();
				final JFrame jf = new JFrame();
				if (xValue >= 1300 && xValue <= 1550) {
					jf.dispose();
				}
				int winner = game.checkWinners();
				if (winner == 1) {
					winningSound();
					response = JOptionPane.showConfirmDialog(jf, "Player 1 Wins!\nWould you like to play again?",
							"Play Again?", JOptionPane.YES_NO_OPTION);
					if (response == 0) {
						game = new Game();
						repaint();
					} else if (response == 1) {
						System.exit(0);
					}
				}

				if (winner != 0 && winner != 1 && winner != 2) {
					for (int i = 0; i < 7; i++) {
						if (!buttonArray[0][i].equals("-")) {
							computerWins();
							response2 = JOptionPane.showConfirmDialog(jf,
									"The Computer Wins!\nWould you like to play again?", "Play Again?",
									JOptionPane.YES_NO_OPTION);
							if (response2 == 0) {
								game = new Game();
								repaint();
								break;
							} else if (response2 == 1) {
								System.exit(0);
							}
						}
					}
				}
			}
		};

		// Initializing buttons.
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				buttonArray[i][j] = new JButton();
				c.add(buttonArray[i][j]);
				buttonArray[i][j].addMouseListener(mouseListener);
				buttonArray[i][j].setSize(100, 100);
				buttonArray[i][j].setLocation(100 + 100 * j, 100 + 100 * i);

			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		if (game.p1Turn == true) {
			g.drawString("Player One's Turn (Yellow)", 830, 150);
			g.drawString("Click on any square to drop your piece in that column.", 830, 175);
		} else if (game.p1Turn == false) {
			g.drawString("This is the current state of the board.", 830, 150);
			g.drawString("Click on any square to proceed to computer's turn.", 830, 175);
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (game.board.grid[i][j].equals("1")) {
					buttonArray[i][j].setBackground(Color.yellow);
					buttonArray[i][j].setContentAreaFilled(false);
					buttonArray[i][j].setOpaque(true);

				} else if (game.board.grid[i][j].equals("C")) {
					buttonArray[i][j].setBackground(Color.red);
					buttonArray[i][j].setContentAreaFilled(false);
					buttonArray[i][j].setOpaque(true);

				} else {
					buttonArray[i][j].setBackground(UIManager.getColor("Button.background"));
					buttonArray[i][j].setContentAreaFilled(true);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {

	}

	/*
	 * Link: https://stackoverflow.com/questions/25171205/playing-sound-in-java
	 */
	public void sound() {
		try {
			// Open an audio input stream.
			File soundFile = new File("Clink.wav"); // you could also get the sound file with an URL
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Link: https://stackoverflow.com/questions/25171205/playing-sound-in-java
	 */
	public void winningSound() {
		try {
			// Open an audio input stream.
			File soundFile = new File("Yay.wav"); // you could also get the sound file with an URL
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void computerWins() {
		try {
			// Open an audio input stream.
			File soundFile = new File("compWin.wav"); // you could also get the sound file with an URL
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyFrame2 JFrame2 = new MyFrame2("Welcome to Connect 4");
		JFrame2.setSize(1600,1000); // Width of the screen, height of the screen
		JFrame2.setVisible(true);
		JFrame2.setResizable(false);
		JFrame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

}
