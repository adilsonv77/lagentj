
import br.udesc.lagentj.*;

public class Mundo67 extends AgenteJ {

    @Override
    public void inteligencia() {
        int n, e, w, s;
        andarAcima();
        andarDireita();
        n = getInt();
        isPrimo(n);
        andarAbaixo();
        andarAbaixo();
        s = getInt();
        isPrimo(s);
        andarEsquerda();
        andarEsquerda();
        e = getInt();
        isPrimo(e);
        andarAcima();
        andarAcima();
        w = getInt();
        isPrimo(w);
        andarDireita();
        andarAbaixo();
    }

    public void isPrimo(int numero) {
        int divisoes = 0;
        for (int i = 1; i <= numero; i++) {
            if (numero % i == 0) {
                divisoes++;
            }
        }
        if (divisoes == 2) {
            diga(numero + " é primo");
        } else {
            diga(numero + " não é primo");
        }
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo67.xml");
    }

}
