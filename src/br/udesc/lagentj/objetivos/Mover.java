package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.suporte.Scripts;
import java.util.Map;
import javax.script.ScriptException;

public class Mover extends Objetivo {

    public Mover(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        Map<String, Object> coords = (Map) opcoes;
        int x = (Integer) coords.get("x");
        int y = (Integer) coords.get("y");
        evaluate();
        if (config.getX() == x) {
            if (config.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public String getDescricao() {
        evaluate();
        return String.format("Voce precisa mover o agente ate a posicao Col %s Lin %s", config.getX(), config.getY());
    }
    
    public void evaluate(){
        if (config.getDx() != null){
            try {
                config.setX((int) Scripts.eval(config.getDx()));
            } catch (ScriptException ex) {
            }
        }
        if (config.getDy() != null){
            try {
                config.setY((int) Scripts.eval(config.getDy()));
            } catch (ScriptException ex) {
            }
        }
    }

}
