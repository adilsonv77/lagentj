package br.udesc.lagentj.objetivos;

import java.util.Map;

public class NaoRepetirPosicoes extends Objetivo {

    public NaoRepetirPosicoes(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        return true;
    }

    public String getDescricao() {
        return String.format("N�o � permitido visitar a mesma posi��o mais de uma vez.");
    }

}
