package br.udesc.lagentj.objetivos;

import java.util.Map;

public class Mover extends Objetivo {

    public Mover(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        Map<String, Object> coords = (Map) opcoes;
        String x = Integer.toString((Integer) coords.get("x"));
        String y = Integer.toString((Integer) coords.get("y"));
        if (config.getX().equals(x)) {
            if (config.getY().equals(y)) {
                return true;
            }
        }
        return false;
    }

    public String getDescricao() {
        return String.format("O agente precisa andar at\351 a posi\347\343o Col %s Lin %s", config.getX(), config.getY());
    }

}
