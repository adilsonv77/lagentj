/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos.singletons;

import br.udesc.lagentj.objetivos.Comando;
import br.udesc.lagentj.objetivos.Objetivo;
import br.udesc.lagentj.objetivos.UsarMetodo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gabriel
 */
public class MethodManager implements RestartInterface {

    private static MethodManager self;
    private Map<String, UsarMetodo> objetivos;
    private String clazz;

    private MethodManager() {
    }

    public static MethodManager getInstance() {
        if (self == null) {
            self = new MethodManager();
        }
        return self;
    }

    public void prepare(List<Objetivo> objs, String clazz) {
        this.clazz = clazz;
        objetivos = new HashMap();
        for (Objetivo obj : objs) {
            if (obj.getConfig().getTipo().equals("usarMetodo")) {
                String nomeMetodo = obj.getConfig().getNome();
                objetivos.put(nomeMetodo, (UsarMetodo) obj);
            }
        }
    }

    public void countMethodCall(String method) {
        if (clazz != null && objetivos != null) {
            objetivos.get(method).call();
        }
    }

    public void storeReturn(String method, Object info) {
        if (clazz != null && objetivos != null) {
            objetivos.get(method).retorno(info);
        }
    }

    public void invalidateMethodCall(Comando tipo) {
        if (clazz != null && objetivos != null) {
            for (String key : objetivos.keySet()) {
                if (objetivos.get(key).isRestrito() && (tipo == objetivos.get(key).getTipoComando() || objetivos.get(key).getTipoComando() == Comando.DEFAULT)) {
                    objetivos.get(key).invalidate();
                }
            }
        }
    }

    public Set<String> getNomesMetodos() {
        return objetivos.keySet();
    }

    @Override
    public void restart() {
        self = new MethodManager();
        objetivos.clear();
        clazz = null;
    }

}
