package br.com.staroski.games.mazehunter;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Maze implements Paintable {

	public final int ROWS;
	public final int COLS;

	private Cell[][] cells;
	private List<Cell> graph;

	private Rectangle bounds;

	public Maze(int rows, int cols, int width, int height) {
		this(rows, cols, 0, 0, width, height);
	}

	public Maze(int rows, int cols, int x, int y, int width, int height) {
		ROWS = rows;
		COLS = cols;
		bounds = new Rectangle(x, y, width, height);
		graph = new ArrayList<>();
		cells = createCells(rows, cols, bounds);
		connectCells(cells);
		createPath();
	}

	private Cell[][] connectCells(Cell[][] cells) {
		int rows = cells.length;
		int cols = cells[0].length;

		// canto superior esquerdo
		cells[0][0].rightCell = cells[0][1];
		cells[0][0].downCell = cells[1][0];

		// canto superior direito
		cells[0][cols - 1].leftCell = cells[0][cols - 2];
		cells[0][cols - 1].downCell = cells[1][cols - 1];

		// canto inferior esquerdo
		cells[rows - 1][0].upCell = cells[rows - 2][0];
		cells[rows - 1][0].rightCell = cells[rows - 1][1];

		// canto inferior direito
		cells[rows - 1][cols - 1].upCell = cells[rows - 2][cols - 1];
		cells[rows - 1][cols - 1].leftCell = cells[rows - 1][cols - 2];

		for (int j = 1; j < cols - 1; j++) {
			// linha superior
			cells[0][j].leftCell = cells[0][j - 1];
			cells[0][j].rightCell = cells[0][j + 1];
			cells[0][j].downCell = cells[1][j];
			// linha inferior			
			cells[rows - 1][j].leftCell = cells[rows - 1][j - 1];
			cells[rows - 1][j].rightCell = cells[rows - 1][j + 1];
			cells[rows - 1][j].upCell = cells[rows - 2][j];
		}

		for (int i = 1; i < rows - 1; i++) {
			// coluna esquerda
			cells[i][0].upCell = cells[i - 1][0];
			cells[i][0].downCell = cells[i + 1][0];
			cells[i][0].rightCell = cells[i][1];
			// coluna direita
			cells[i][cols - 1].upCell = cells[i - 1][cols - 1];
			cells[i][cols - 1].downCell = cells[i + 1][cols - 1];
			cells[i][cols - 1].leftCell = cells[i][cols - 2];
		}

		// celulas centrais
		for (int i = 1; i < rows - 1; i++) {
			for (int j = 1; j < cols - 1; j++) {
				cells[i][j].upCell = cells[i - 1][j];
				cells[i][j].downCell = cells[i + 1][j];
				cells[i][j].leftCell = cells[i][j - 1];
				cells[i][j].rightCell = cells[i][j + 1];
			}
		}
		return cells;
	}

	private Cell[][] createCells(int rows, int cols, Rectangle bounds) {
		int x = bounds.x;
		int y = bounds.y;
		int cellWidth = bounds.width / cols;
		int cellHeight = bounds.height / rows;
		Cell[][] cells = new Cell[rows][cols];
		for (int i = 0; i < rows; i++) {
			int cellY = y + i * cellHeight;
			for (int j = 0; j < cols; j++) {
				int cellX = x + j * cellWidth;
				cells[i][j] = new Cell(cellX, cellY, cellWidth, cellHeight);
				graph.add(cells[i][j]);
			}
		}
		return cells;
	}

	private void createPath() {
		dfsVisit(cells[0][0]);
	}

	private List<Cell> visited = new ArrayList<>();

	private void dfsVisit(Cell v) {
		visited.add(v);
		List<Cell> adjacents = v.adjacents();
		Collections.shuffle(adjacents);
		for (Cell w : adjacents) {
			if (!visited.contains(w)) {
				if (w == v.upCell) {
					v.upWall.setBroken(true);
					w.downWall.setBroken(true);
					dfsVisit(w);
				} else if (w == v.downCell) {
					v.downWall.setBroken(true);
					w.upWall.setBroken(true);
					dfsVisit(w);
				} else if (w == v.leftCell) {
					v.leftWall.setBroken(true);
					w.rightWall.setBroken(true);
					dfsVisit(w);
				} else if (w == v.rightCell) {
					v.rightWall.setBroken(true);
					w.leftWall.setBroken(true);
					dfsVisit(w);
				}
			}
		}
	}

	@Override
	public void paint(Graphics2D g) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				Cell cell = cells[i][j];
				cell.paint(g);
			}
		}
	}
}
