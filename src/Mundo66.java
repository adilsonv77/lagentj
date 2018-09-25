
import br.udesc.lagentj.*;

public class Mundo66 extends AgenteJ {

    @Override
    public void inteligencia() {
        int a, b, c, d;

        andarDireita();
        a = getInt();
        convertC2F(a);

        andarDireita();
        b = getInt();
        convertC2F(b);

        andarDireita();
        c = getInt();
        convertC2F(c);

        andarDireita();
        d = getInt();
        convertC2F(d);
    }

    public void convertC2F(int celsius) {
        int farenheit = (9 * celsius / 5) + 32;
        diga(farenheit + "º F");
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo66.xml");
    }

}
