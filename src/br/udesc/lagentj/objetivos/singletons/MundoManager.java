/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos.singletons;

import br.udesc.lagentj.objetivos.GoalManager;
import br.udesc.lagentj.objetivos.Posicao;
import br.udesc.lagentj.suporte.ObjetoMundoImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gabriel
 */
public class MundoManager implements RestartInterface {

    private static MundoManager self;
    private Map<String, Posicao> posicoes;

    private MundoManager() {
        posicoes = new HashMap();
        int lin = GoalManager.getQtdadeLin();
        int col = GoalManager.getQtdadeCol();
        for (int i = 0; i < lin; i++) {
            for (int j = 0; j < col; j++) {
                String pattern = "%s,%s";
                String key = String.format(pattern, i, j);
                posicoes.put(key, new Posicao(i, j));
            }
        }
        for (ObjetoMundoImpl objeto : GoalManager.getObjetos()) {
            if (objeto.isBloqueado()) {
                int ox = objeto.getX();
                int oy = objeto.getY();
                String pattern = "%s,%s";
                String key = String.format(pattern, ox, oy);
                posicoes.remove(key);
            }
            if (objeto.getObjetoMundo().getSouDoTipo().equals(objeto.getMundoVisual().getMundoAgenteJ().getExercicio().getClazzSimpleName())) {
                int ox = objeto.getX();
                int oy = objeto.getY();
                String pattern = "%s,%s";
                String key = String.format(pattern, ox, oy);                
                posicoes.get(key).visit();
            }
        }
    }

    public static MundoManager getInstance() {
        if (self == null) {
            self = new MundoManager();
        }
        return self;
    }

    public boolean validate() {
        for (String string : posicoes.keySet()) {
            Posicao p = posicoes.get(string);
            if (p.getVisits() == 0) {
                return false;
            }
        }
        return true;
    }

    public void visit(int x, int y) {
        String pattern = "%s,%s";
        String key = String.format(pattern, x, y);
        Posicao p = posicoes.get(key);
        if (p != null){
            p.visit();
        }          
        
    }

    @Override
    public void restart() {
        self = new MundoManager();
        posicoes.clear();
    }

}
