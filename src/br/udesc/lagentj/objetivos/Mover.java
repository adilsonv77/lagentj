package br.udesc.lagentj.objetivos;

import java.util.Map;

public class Mover extends Objetivo {

	private int x;
	private int y;

	public Mover() {
	}

	public Mover(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean verificarObjetivo(Object opcoes) {
		Map<String, Object> coords = (Map<String, Object>) opcoes;
		int x = (int) coords.get("x");
		int y = (int) coords.get("y");
		if (this.x == x) {
			if (this.y == y) {
				return true;
			}
		}
		return false;
	}

	public String getDescricao() {
		return String.format("Você precisa mover o AgenteJ até a posição Col %s Lin %s", getX(), getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
