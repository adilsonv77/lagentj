package br.udesc.lagentj.objetivos;

import java.util.Map;

public class Dizer extends Objetivo {

    public Dizer(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        return true;
    }

    public String getDescricao() {
        return String.format("Voc� precisa dizer '%s'", getConfig().getTexto());
    }

}
