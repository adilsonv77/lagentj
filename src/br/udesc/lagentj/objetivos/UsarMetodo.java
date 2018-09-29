/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class UsarMetodo extends Objetivo {

    private int calls;
    private boolean invalid;
    private List<Integer> lines;

    public UsarMetodo(ObjetivoConfiguracao config) {
        super(config);
        lines = new ArrayList<Integer>();
        invalid = false;
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        if (hasMethod() && config.eval(Integer.toString(calls))) {
            return !invalid;
        }
        return false;
    }

    @Override
    public String getDescricao() {
        String parametros = "";
        for (int i = 0; i < config.getParametros().size(); i++) {
            Parametro p = config.getParametros().get(i);
            String tipo = "";
            if (p.getTipo().contains(".")) {
                String a = p.getTipo().replace('.', '-');
                String[] b = a.split("-");
                tipo = b[b.length - 1];
            } else {
                tipo = p.getTipo();
            }
            parametros += tipo;
            if (i < config.getParametros().size() - 1) {
                parametros += ", ";
            }
        }
        String descricao = String.format("Você precisa usar o método %s (%s): %s. ", config.getNome(), parametros, config.getRetorno());
        if (config.isRestrito()){
            descricao += "Os " + config.getTipoComando().descricao + " só podem ser chamados de dentro deste método.";
        }
        return descricao;
    }

    public void call(int line) {
        boolean has = false;
        for (Integer l : lines) {
            if (l == line) {
                has = true;
                break;
            }
        }
        if (!has) {
            calls++;
            lines.add(line);
        }
    }

    public boolean hasMethod() {
        boolean hasMethod = false;
        Class obj = null;
        try {
            obj = Class.forName(getMundo().getMundoAgenteJ().getExercicio().getClazz());
            for (Method m : obj.getDeclaredMethods()) {
                if (m.getName().equals(config.getNome())) {
                    boolean hasAllParamTypes = true;
                    for (Class param : m.getParameterTypes()) {
                        boolean found = false;
                        for (Class param2 : config.getParametrosTypes()) {
                            if (param.equals(param2)) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            hasAllParamTypes = false;
                        }
                    }
                    if (hasAllParamTypes) {
                        if (config.getRetornoType() == m.getReturnType()){
                            hasMethod = true;
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hasMethod;
    }
    
    public void invalidate(){
        invalid = true;
    }
    
    public boolean isRestrito(){
        return config.isRestrito();
    }
    
    public Comando getTipoComando(){
        return config.getTipoComando();
    }

}
