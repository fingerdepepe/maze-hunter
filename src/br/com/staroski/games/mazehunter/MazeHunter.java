package br.com.staroski.games.mazehunter;

import java.awt.*;

import javax.swing.*;

public class MazeHunter {

	private class Painter extends JPanel {

		private static final long serialVersionUID = 1;

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fill(getBounds());
			maze.paint(g2d);
		}
	}

	public static void main(String[] args) {
		new MazeHunter().execute();
	}

	private Maze maze;

	private void execute() {
		JFrame frame = new JFrame("Maze Hunter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setResizable(false);

		int x = (640 - 480) / 2;
		int y = (480 - 320) / 2;
		maze = new Maze(30, 30, x, y, 480, 320);
		Painter painter = new Painter();
		frame.setContentPane(painter);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
