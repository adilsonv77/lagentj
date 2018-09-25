
import br.udesc.lagentj.*;

public class Mundo64 extends AgenteJ {

    @Override
    public void inteligencia() {
        while (!ehFim(DIREITA)) {
            andarDireita();
            imprimirTabuada(getInt());
        }
    }

    public void imprimirTabuada(int numero) {
        String tabuada = "%s x %s = %s";
        for (int i = 1; i <= 10; i++) {
            diga(String.format(tabuada, numero, i, i * numero));
        }
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo64.xml");
    }

}
