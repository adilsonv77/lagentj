package br.udesc.lagentj.objetivos;

import java.util.Map;

public class Dizer extends Objetivo {

    public Dizer(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        String texto = (String) opcoes;
        if (texto.equalsIgnoreCase(getConfig().getTexto())){
            return true;
        }
        return false;
    }

    public String getDescricao() {
        return String.format("Voce precisa dizer '%s'", getConfig().getTexto());
    }

}
