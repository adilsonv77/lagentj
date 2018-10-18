/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.suporte.Scripts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptException;

/**
 *
 * @author gabriel
 */
public class ObjetivoConfiguracao {

    private String x;
    private String y;
    private Object valor;
    private String xFormula;
    private String yFormula;
    private String vFormula;

    private boolean restrito;
    private String tipo;
    private String nome;
    private String retorno;
    private String comando;
    private String descricao;
    private String classe;
    private List<Parametro> parametros = new ArrayList();
    private static Map<String, Class<? extends Objetivo>> objetivos;

    public String getxFormula() {
        return xFormula;
    }

    public void setxFormula(String xFormula) {
        this.xFormula = xFormula;
    }

    public String getyFormula() {
        return yFormula;
    }

    public void setyFormula(String yYormula) {
        this.yFormula = yYormula;
    }

    public String getvFormula() {
        return vFormula;
    }

    public void setvFormula(String vFormula) {
        this.vFormula = vFormula;
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

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Object getValor() {
        if (vFormula != null) {
            try {
                valor = Scripts.eval(vFormula);
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }
        }
        return valor;
    }

    public void setValor(Object valor) {
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

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }

    public void addParametro(Parametro p) {
        parametros.add(p);
    }

    public Objetivo gerar() throws Exception {
        return objetivos.get(this.tipo).getConstructor(new Class[]{ObjetivoConfiguracao.class}).newInstance(this);
    }

    public Comando getTipoComando() {
        if (comando.equals("MOVIMENTO")) {
            return Comando.MOVIMENTO;
        } else if (comando.equals("INFO")) {
            return Comando.INFO;
        } else if (comando.equals("MUNDO")) {
            return Comando.MUNDO;
        } else if (comando.equals("CONSOLE")) {
            return Comando.CONSOLE;
        } else {
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
            } else if (className.equals("boolean[]")) {
                params[i] = boolean[].class;
            } else if (className.equals("byte[]")) {
                params[i] = byte[].class;
            } else if (className.equals("short[]")) {
                params[i] = short[].class;
            } else if (className.equals("int[]")) {
                params[i] = int[].class;
            } else if (className.equals("long[]")) {
                params[i] = long[].class;
            } else if (className.equals("float[]")) {
                params[i] = float[].class;
            } else if (className.equals("double[]")) {
                params[i] = double[].class;
            } else if (className.equals("char[]")) {
                params[i] = char[].class;
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
        objetivos.put("pegarObjeto", PegarObjeto.class);
        objetivos.put("percorrerMundo", PercorrerMundo.class);

    }

    public String getX() {
        if (xFormula != null) {
            try {
                x = Scripts.eval(xFormula).toString();
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }
        }
        return x;
    }

    public String getY() {
        if (yFormula != null) {
            try {
                y = Scripts.eval(yFormula).toString();
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }
        }
        return y;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

}
