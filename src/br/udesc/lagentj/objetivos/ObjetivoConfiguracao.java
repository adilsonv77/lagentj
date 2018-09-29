/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

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
    private String dx;
    private String dy;
    private String tipo;
    private String texto;
    private String valor;
    private String nome;
    private String retorno;
    private String condicao;
    private boolean restrito;
    private String comando;
    private String formula;
    private String descricao;
    private List<Parametro> parametros = new ArrayList();
    private static Map<String, Class<? extends Objetivo>> objetivos;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

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
        parametros.add(p);
    }

    public Objetivo gerar() throws Exception {
        return objetivos.get(this.tipo).getConstructor(new Class[]{ObjetivoConfiguracao.class}).newInstance(this);
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public Comando getTipoComando() {
        switch (comando) {
            case "MOVIMENTO":
                return Comando.MOVIMENTO;
            case "INFO":
                return Comando.INFO;
            case "MUNDO":
                return Comando.MUNDO;
            case "CONSOLE":
                return Comando.CONSOLE;
            default:
                return Comando.DEFAULT;
        }
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

    public boolean isRestrito() {
        return restrito;
    }

    public void setRestrito(boolean restrito) {
        this.restrito = restrito;
    }

    public Class getRetornoType() throws ClassNotFoundException {
        switch (retorno) {
            case "boolean":
                return boolean.class;
            case "byte":
                return byte.class;
            case "short":
                return short.class;
            case "int":
                return int.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "double":
                return double.class;
            case "char":
                return char.class;
            case "void":
                return void.class;
            default:
                String fqn = retorno.contains(".") ? retorno : "java.lang.".concat(retorno);
                return Class.forName(fqn);
        }
    }

    public boolean eval(String valor) {
        List<String> list = new ArrayList<>();
        if (condicao != null) {
            if (valor != null) {
                switch (condicao) {
                    case "==":
                        return valor.equals(this.valor);
                    case "<=":
                        return Integer.parseInt(valor) <= Integer.parseInt(this.valor);
                    case ">=":
                        return Integer.parseInt(valor) >= Integer.parseInt(this.valor);
                    case "<":
                        return Integer.parseInt(valor) < Integer.parseInt(this.valor);
                    case ">":
                        return Integer.parseInt(valor) > Integer.parseInt(this.valor);
                    case "!=":
                        return !valor.equals(this.valor);
                    default:
                        return false;
                }
            }
        }
        return false;
    }

    static {
        objetivos = new HashMap();
        objetivos.put("mover", Mover.class);
        objetivos.put("dizer", Dizer.class);
        objetivos.put("dizerTabuada", DizerTabuada.class);
        objetivos.put("usarMetodo", UsarMetodo.class);
        objetivos.put("lerInteiro", LerInteiro.class);
    }

}
