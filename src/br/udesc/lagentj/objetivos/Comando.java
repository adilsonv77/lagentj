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
public enum Comando {

    CONSOLE("comandos de console"),
    INFO("comandos informação"),
    MOVIMENTO("comandos de movimento"),
    MUNDO("comandos do mundo"),
    DEFAULT("comandos padrão");

    String descricao;

    private Comando(String descricao) {
        this.descricao = descricao;
    }

}
