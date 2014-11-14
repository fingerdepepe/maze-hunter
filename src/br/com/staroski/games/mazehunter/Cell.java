package br.com.staroski.games.mazehunter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Cell implements Paintable {

	public Cell upCell;
	public Cell downCell;
	public Cell leftCell;
	public Cell rightCell;

	public Wall upWall;
	public Wall rightWall;
	public Wall downWall;
	public Wall leftWall;

	public final Rectangle bounds;

	Cell(int width, int height) {
		this(0, 0, width, height);
	}

	public Cell(int x, int y, int width, int height) {
		bounds = new Rectangle(x, y, width, height);
		upWall = new Wall(x, y, width, 1);
		downWall = new Wall(x, y + height, width, 1);
		leftWall = new Wall(x, y, 1, height);
		rightWall = new Wall(x + width, y, 1, height);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fill(bounds);
		upWall.paint(g);
		downWall.paint(g);
		leftWall.paint(g);
		rightWall.paint(g);
	}

	public List<Cell> adjacents() {
		List<Cell> adjacents = new ArrayList<>();
		if (upCell != null) {
			adjacents.add(upCell);
		}
		if (downCell != null) {
			adjacents.add(downCell);
		}
		if (leftCell != null) {
			adjacents.add(leftCell);
		}
		if (rightCell != null) {
			adjacents.add(rightCell);
		}
		return adjacents;
	}
}
