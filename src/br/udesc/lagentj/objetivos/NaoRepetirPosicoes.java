package br.udesc.lagentj.objetivos;

import java.util.Map;

public class NaoRepetirPosicoes extends Objetivo {

	public NaoRepetirPosicoes() {
	}

	@Override
	public boolean verificarObjetivo(Object opcoes) {
		return true;
	}

	public String getDescricao() {
		return String.format("Não é permitido visitar a mesma posição mais de uma vez.");
	}

}
