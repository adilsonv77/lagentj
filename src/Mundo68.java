
import br.udesc.lagentj.*;

public class Mundo68 extends AgenteJ {

    @Override
    public void inteligencia() {
        andarDireita();
        int pistas = 0;
        do {
            int x = getInt();
            andarDireita();
            int y = getInt();
            mover(y, x);
            pistas++;
        } while (pistas < 5);
        diga(getInt());
    }

    public void mover(int coluna, int linha) {
        int c = getColuna();
        int l = getLinha();
        int dl = l - linha;
        int dc = c - coluna;
        for (int i = 0; i < Math.abs(dc); i++) {
            if (dc < 0) {
                andarDireita();
            } else {
                andarEsquerda();
            }
        }
        for (int i = 0; i < Math.abs(dl); i++) {
            if (dl < 0) {
                andarAbaixo();
            } else {
                andarAcima();
            }
        }
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo68.xml");
    }

}
