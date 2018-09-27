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
    private List<Integer> lines;

    public UsarMetodo(ObjetivoConfiguracao config) {
        super(config);
        lines = new ArrayList<Integer>();
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        if (hasMethod() && getConfig().eval(Integer.toString(calls))) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescricao() {
        String parametros = "";
        for (int i = 0; i < getConfig().getParametros().size(); i++) {
            Parametro p = getConfig().getParametros().get(i);
            parametros += p.getTipo();
            if (i < getConfig().getParametros().size() - 1) {
                parametros += ", ";
            }
        }
        return String.format("Você precisa usar o método %s (%s): %s", getConfig().getNome(), parametros, getConfig().getRetorno());
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
                if (m.getName().equals(getConfig().getNome())) {
                    boolean hasAllParamTypes = true;
                    for (Class param : m.getParameterTypes()) {
                        boolean found = false;
                        for (Class param2 : getConfig().getParametrosTypes()) {
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
                        hasMethod = true;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hasMethod;
    }

}
