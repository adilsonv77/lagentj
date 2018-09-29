
import br.udesc.lagentj.*;

public class Mundo62 extends AgenteJ {

    @Override
    public void inteligencia() {
        andarDireita();
        tabuada(getInt());
        andarDireita();
        tabuada(getInt());
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo62.xml");
    }

    private void tabuada(int a) {
        for (int i = 1; i <= 10; i++) {
            String output = "%s = %s";
            diga(String.format(output, i, a * i));
        }
    }

}
