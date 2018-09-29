package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.MundoVisual;
import java.util.Map;

public abstract class Objetivo {

    protected ObjetivoConfiguracao config;
    private MundoVisual mundo;

    public Objetivo(ObjetivoConfiguracao config) {
        this.config = config;
    }

    public ObjetivoConfiguracao getConfig() {
        return config;
    }

    public void setConfig(ObjetivoConfiguracao config) {
        this.config = config;
    }

    public boolean isCompleto(String tipo, Object opcoes) {
        if (tipo.equals(config.getTipo())) {
            return verificarObjetivo(opcoes);
        }
        return false;
    }

    public abstract boolean verificarObjetivo(Object opcoes);

    public abstract String getDescricao();

    public MundoVisual getMundo() {
        return mundo;
    }

    public void setMundo(MundoVisual mundo) {
        this.mundo = mundo;
    }

}
