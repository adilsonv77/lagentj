/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gabriel
 */
public class ObjetivoConfiguracao {

    private static Map<String, Class<? extends Objetivo>> objetivos;
    private String tipo;
    private int x;
    private int y;
    private String texto;
    private String valor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Objetivo gerar() throws Exception {
        return objetivos.get(this.tipo).getConstructor(new Class[]{ObjetivoConfiguracao.class}).newInstance(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    static {
        objetivos = new HashMap();
        objetivos.put("mover", Mover.class);
        objetivos.put("dizer", Dizer.class);
        objetivos.put("naoRepetirPosicoes", NaoRepetirPosicoes.class);
    }

}
