package br.udesc.lagentj.objetivos;

public class Ir extends Objetivo {

	private Posicao posicao;

	public Ir() {
		super();
	}

	public Ir(Posicao posicao) {
		super();
		this.posicao = posicao;
	}

	@Override
	public boolean verificarObjetivo(Object opcoes) {
		Posicao pos = (Posicao) opcoes;
		if (pos.equals(posicao)) {
			return true;
		}
		return false;
	}

	public String getDescricao() {
		return String.format("Voc� precisa mover o AgenteJ at� a posi��o %s, %s", posicao.getX(), posicao.getY());
	}

}
