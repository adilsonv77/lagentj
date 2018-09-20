package br.udesc.lagentj.objetivos;

import java.util.Map;

public class Dizer extends Objetivo {

	private String fala;

	public Dizer() {
	}

	public Dizer(String fala) {
		this.fala = fala;
	}

	@Override
	public boolean verificarObjetivo(Object opcoes) {
		return true;
	}

	public String getDescricao() {
		return String.format("Você precisa dizer '%s'", getFala());
	}

	public String getFala() {
		return fala;
	}

	public void setFala(String fala) {
		this.fala = fala;
	}
	
	

}
