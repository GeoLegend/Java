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

class MyFrame extends JFrame implements ActionListener {
	private final JLabel title = new JLabel("Welcome to Connect 4");
	private int xValue;
	private int yValue;
	private Game game;
	private JButton[][] buttonArray = new JButton[6][7];
	public MyFrame(String n) {
		super(n);
		setTitle("Welcome to Connect 4");
		Container c = getContentPane();
		c.setLayout(null);
		game = new Game();
		JPanel p1 = new JPanel();
		p1.setLocation(1200, 200);
		add(p1);
		p1.setVisible(true);
		p1.setOpaque(true);
		MouseListener mouseListener = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int response, response2, count = 0;
				xValue = e.getLocationOnScreen().x; // if mouse is clicked set x and y coords of the ball to be wherever
													// the mouse is when clicked
				yValue = e.getLocationOnScreen().y;
				game.makeMove(xValue, yValue);
				sound();
				repaint();
				final JFrame jf = new JFrame();
				int winner = game.checkWinners();
				if (winner > 0) {
					winningSound();
					response = JOptionPane.showConfirmDialog(jf,
							"Player " + winner + " Wins!\nWould you like to play again?", "Play Again?",
							JOptionPane.YES_NO_OPTION);
					if (response == 0) {
						game = new Game();
						repaint();
					} else {
						System.exit(0);
					}
				}
				for (int i = 0; i < 7; i++) {
					if (game.board.grid[0][i].equals("-")) {
						count++;
					}
				}
				if (count == 0) {
					response2 = JOptionPane.showConfirmDialog(jf, "It's a tie!\nWould you like to play again?",
							"Play Again?", JOptionPane.YES_NO_OPTION);
					if (response2 == 0) {
						game = new Game();
						repaint();
					} else {
						System.exit(0);
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
		} else {
			g.drawString("Player Two's Turn (Red)", 830, 150);
			g.drawString("Click on any square to drop your piece in that column.", 830, 175);
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (game.board.grid[i][j].equals("1")) {
					buttonArray[i][j].setBackground(Color.yellow);
					buttonArray[i][j].setContentAreaFilled(false);
					buttonArray[i][j].setOpaque(true);
				} else if (game.board.grid[i][j].equals("2")) {
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
		game.makeMove(xValue, yValue);

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

	public static void main(String[] args) {
		MyFrame JFrame = new MyFrame("Player vs Player");
		JFrame.setSize(1600,1000); // Width of the screen, height of the screen
		JFrame.setVisible(true);
		JFrame.setResizable(true);
		JFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(JFrame,
				"Instructions:\n 1. Decide who will play first. Players would alternate turns after playing a token.\n 2. On your turn, drop one of your tokens down any of the slots at the top of the grid or board. \n 3. Take turns playing the game until one player gets 4 checks in a row (Vertically, Horizontally, Diagonally). ");

	}
}
