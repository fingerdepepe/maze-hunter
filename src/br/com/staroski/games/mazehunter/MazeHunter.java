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

		int frameWidth = 640;
		int frameHeight = 480;
		int mazeWidth = 480;
		int mazeHeight = 320;
		int linhas = 30;
		int colunas = 40;

		frame.setSize(frameWidth, frameHeight);
		frame.setResizable(false);

		int x = (frameWidth - mazeWidth) / 2;
		int y = (frameHeight - mazeHeight) / 2;
		maze = new Maze(linhas, colunas, x, y, mazeWidth, mazeHeight);
		Painter painter = new Painter();
		frame.setContentPane(painter);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
