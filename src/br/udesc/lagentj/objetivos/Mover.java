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
        return String.format("Voc� precisa mover o AgenteJ at� a posi��o Col %s Lin %s", getConfig().getX(), getConfig().getY());
    }

}
