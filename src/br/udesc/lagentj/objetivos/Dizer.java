package br.udesc.lagentj.objetivos;

public class Dizer extends Objetivo {

    public Dizer(ObjetivoConfiguracao config) {
        super(config);
    }

    @Override
    public boolean verificarObjetivo(Object opcoes) {
        String expectedText = config.getValor().toString();
        String texto = (String) opcoes;
        if (texto.equalsIgnoreCase(expectedText)) {
            return true;
        }
        return false;
    }

    public String getDescricao() {
        String extra = "";
        if (config.getDescricao() != null) {
            extra = ", ou seja, " + config.getDescricao();
        }
        String expectedText = config.getValor().toString();
        return String.format("Voc\352 precisa dizer %s %s", expectedText, extra);
    }

}
