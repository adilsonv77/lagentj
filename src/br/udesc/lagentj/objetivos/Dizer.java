package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.suporte.Scripts;
import javax.script.ScriptException;

public class Dizer extends Objetivo {

    public Dizer(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        String expectedText = null;
        if (config.getTexto() != null){
            expectedText = config.getTexto();
        } else {
            try {
                expectedText = Scripts.eval(config.getFormula()).toString();
            } catch (ScriptException ex) {
            }
        }
        String texto = (String) opcoes;
        if (texto.equalsIgnoreCase(expectedText)){
            return true;
        }
        return false;
    }

    public String getDescricao() {
        String expectedText = "";
        if (config.getTexto() != null){
            expectedText = config.getTexto();
        } else {
            expectedText = config.getDescricao();
        }
        return String.format("Voce precisa dizer: '%s'", expectedText);
    }

}
