/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import java.lang.reflect.Executable;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabriel
 */
public class ObjetivoConfiguracao {

    private int x;
    private int y;
    private String tipo;
    private String texto;
    private String valor;
    private String nome;
    private String retorno;
    private String condicao;
    private List<Parametro> parametros = new ArrayList();
    private static Map<String, Class<? extends Objetivo>> objetivos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public List<Parametro> getParametros() {
        return parametros;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public void addParametro(Parametro p) {
        System.out.println("addParametro");
        parametros.add(p);
    }

    public Objetivo gerar() throws Exception {
        return objetivos.get(this.tipo).getConstructor(new Class[]{ObjetivoConfiguracao.class}).newInstance(this);
    }

    public Class[] getParametrosTypes() throws ClassNotFoundException {
        Class[] params = new Class[parametros.size()];
        for (int i = 0; i < params.length; i++) {
            Parametro p = parametros.get(i);
            String className = p.getTipo();
            if (className.equals("boolean")) {
                params[i] = boolean.class;
            } else if (className.equals("byte")) {
                params[i] = byte.class;
            } else if (className.equals("short")) {
                params[i] = short.class;
            } else if (className.equals("int")) {
                params[i] = int.class;
            } else if (className.equals("long")) {
                params[i] = long.class;
            } else if (className.equals("float")) {
                params[i] = float.class;
            } else if (className.equals("double")) {
                params[i] = double.class;
            } else if (className.equals("char")) {
                params[i] = char.class;
            } else if (className.equals("void")) {
                params[i] = void.class;
            } else {
                String fqn = className.contains(".") ? className : "java.lang.".concat(className);
                params[i] = Class.forName(fqn);
            }
        }
        return params;
    }

    public Class getRetornoType() throws ClassNotFoundException {
        if (retorno.equals("boolean")) {
            return boolean.class;
        } else if (retorno.equals("byte")) {
            return byte.class;
        } else if (retorno.equals("short")) {
            return short.class;
        } else if (retorno.equals("int")) {
            return int.class;
        } else if (retorno.equals("long")) {
            return long.class;
        } else if (retorno.equals("float")) {
            return float.class;
        } else if (retorno.equals("double")) {
            return double.class;
        } else if (retorno.equals("char")) {
            return char.class;
        } else if (retorno.equals("void")) {
            return void.class;
        } else {
            String fqn = retorno.contains(".") ? retorno : "java.lang.".concat(retorno);
            return Class.forName(fqn);
        }
    }

    static {
        objetivos = new HashMap();
        objetivos.put("mover", Mover.class);
        objetivos.put("dizer", Dizer.class);
        objetivos.put("usarMetodo", UsarMetodo.class);
        objetivos.put("lerInteiro", LerInteiro.class);
    }

}
