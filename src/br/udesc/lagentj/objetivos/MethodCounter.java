/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gabriel
 */
public class MethodCounter {

    private static MethodCounter self;
    private Map<String, UsarMetodo> objetivos;
    private String clazz;

    private MethodCounter() {
    }

    public static MethodCounter getInstance() {
        if (self == null) {
            self = new MethodCounter();
        }
        return self;
    }

    public void prepare(List<Objetivo> objs, String clazz) {
        this.clazz = clazz;
        objetivos = new HashMap();
        for (Objetivo obj : objs) {
            if (obj.getConfig().getTipo().equals("usarMetodo")) {
                objetivos.put(obj.getConfig().getNome(), (UsarMetodo) obj);
            }
        }
    }

    public void countMethodCall(String method, int line) {
        if (clazz != null && objetivos != null) {
            objetivos.get(method).call(line);
        }
    }
    
    public Set<String> getNomesMetodos(){
        return objetivos.keySet();
    }

}
