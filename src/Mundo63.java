
import br.udesc.lagentj.*;

public class Mundo63 extends AgenteJ {
    @Override
    public void inteligencia() {
        andarDireita();
        int a = getInt();
        andarDireita();
        int b = getInt();
        andarDireita();
        int c = getInt();
        andarDireita();
        delta(a, b, c);
    }
    
    public void delta(int a, int b, int c){
        int delta = (b * b) + 4 * a * c;
        diga(delta);
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo63.xml");
    }

}
