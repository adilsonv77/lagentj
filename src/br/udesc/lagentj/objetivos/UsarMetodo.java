/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

/**
 *
 * @author gabriel
 */
public class UsarMetodo extends Objetivo {

    public UsarMetodo(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        return false;
    }

    @Override
    public String getDescricao() {
        return String.format("Você precisa usar o método com nome %s", getConfig().getNomeMetodo());
    }

}
