package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.objetivos.singletons.MundoManager;

public class PercorrerMundo extends Objetivo {

    public PercorrerMundo(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        return MundoManager.getInstance().validate();
    }

    public String getDescricao() {
        return "Voc\352 precisa percorrer todas as posi\347\365es poss\355veis do mundo.";
    }

}
