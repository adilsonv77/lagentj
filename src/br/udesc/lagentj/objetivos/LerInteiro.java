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
        String x = Integer.toString((Integer) coords.get("x"));
        String y = Integer.toString((Integer) coords.get("y"));
        if (config.getX().equals(x)) {
            if (config.getY().equals(y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescricao() {
        return String.format("Voc\352 precisa ler o numero da posi\347\343o Col %s Lin %s", config.getX(), config.getY());
    }

}
