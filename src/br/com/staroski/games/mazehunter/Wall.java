package br.com.staroski.games.mazehunter;

import java.awt.*;

public class Wall implements Paintable {

	private Rectangle bounds;

	public Wall(int x, int y, int width, int height) {
		bounds = new Rectangle(x, y, width, height);
	}

	@Override
	public void paint(Graphics2D g) {
		if (broken) {
			return;
		}
		g.setColor(Color.GRAY);
		g.fill(bounds);
	}

	private boolean broken;

	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}
}
