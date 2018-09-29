
import br.udesc.lagentj.*;

public class Mundo63 extends AgenteJ {
    @Override
    public void inteligencia() {
        int x, y;
        andarAcima();
        x = getInt();
        andarDireita();
        y = getInt();
        andarAte(x, y);
    }
    
    public void delta(int a, int b, int c){
        int delta = (b * b) + 4 * a * c;
        diga(delta);
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo63.xml");
    }

    public void andarAte(int coluna, int linha) {
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

}
