package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.objetivos.singletons.TabuadaManager;

public class DizerTabuada extends Objetivo {

    public DizerTabuada(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        return TabuadaManager.getInstance().validateMap();
    }

    public String getDescricao() {
        return "Voce precisa dizer a tabuada dos numeros lidos.";
    }

}
