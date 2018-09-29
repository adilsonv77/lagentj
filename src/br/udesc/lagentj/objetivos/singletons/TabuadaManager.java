/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos.singletons;

import br.udesc.lagentj.Numero;
import br.udesc.lagentj.objetivos.GoalManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabriel
 */
public class TabuadaManager {

    private static TabuadaManager self;
    private Map<Integer, List<String>> map;
    private List<Numero> numeros;
    private int lastInteger;

    private TabuadaManager() {
        map = new HashMap<>();
        numeros = GoalManager.getNumeros();
    }

    public static TabuadaManager getInstance() {
        if (self == null) {
            self = new TabuadaManager();
        }
        return self;
    }

    public int getLastInteger() {
        return lastInteger;
    }

    public void setLastInteger(int lastInteger) {
        this.lastInteger = lastInteger;
    }

    public void storeOutput(String output) {
        List<String> lista = map.get(lastInteger);
        if (lista == null) {
            lista = new ArrayList<>();
            lista.add(output);
        } else {
            lista.add(output);
        }
        map.put(lastInteger, lista);
    }

    public boolean validateMap() {
        try {
            for (Numero numero : numeros) {
                int valor = numero.getValor();
                List<String> impressoes = map.get(valor);
                for (int i = 0; i < 10; i++) {
                    String impressao = "%s = %s";
                    int b = i + 1;
                    if (!impressoes.get(i).equals(String.format(impressao, b, (b * valor)))) {
                        return false;
                    }
                }
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
