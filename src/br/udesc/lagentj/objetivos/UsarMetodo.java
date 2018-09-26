/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import java.lang.reflect.Method;
import javassist.CtMethod;

/**
 *
 * @author gabriel
 */
public class UsarMetodo extends Objetivo {

    private boolean called;

    public UsarMetodo(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        System.out.println(st);
        if (hasMethod() && called) {
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
        return String.format("Você precisa usar o método com nome %s (%s): %s", getConfig().getNome(), parametros, getConfig().getRetorno());
    }

    public void call() {
        System.out.println("called counter");
        called = true;
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
