package br.udesc.lagentj.objetivos;

import java.util.Map;

public class Mover extends Objetivo {

    public Mover(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        Map<String, Object> coords = (Map) opcoes;
        int x = (Integer) coords.get("x");
        int y = (Integer) coords.get("y");
        if (getConfig().getX() == x) {
            if (getConfig().getY() == y) {
                return true;
            }
        }
        return false;
    }

    public String getDescricao() {
        return String.format("Voce precisa mover o agente ate a posicao Col %s Lin %s", getConfig().getX(), getConfig().getY());
    }

}
