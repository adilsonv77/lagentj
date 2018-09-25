
import br.udesc.lagentj.*;

public class Mundo65 extends AgenteJ {

    @Override
    public void inteligencia() {
        int a, b, c, d;

        andarDireita();
        a = getInt();
        convertF2C(a);

        andarDireita();
        b = getInt();
        convertF2C(b);

        andarDireita();
        c = getInt();
        convertF2C(c);

        andarDireita();
        d = getInt();
        convertF2C(d);
    }

    public void convertF2C(int fahrenheit) {
        int celsius = 5 * (fahrenheit - 32) / 9;
        diga(celsius + "º C");
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo65.xml");
    }

}
