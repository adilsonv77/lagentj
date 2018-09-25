/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import java.util.Map;

/**
 *
 * @author gabriel
 */
public class LerInteiro extends Objetivo {

    public LerInteiro(ObjetivoConfiguracao config) {
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

    @Override
    public String getDescricao() {
        return String.format("Voce precisa ler o numero da posicao Col %s Lin %s", getConfig().getX(), getConfig().getY());
    }

}
