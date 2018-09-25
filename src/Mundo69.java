
import br.udesc.lagentj.*;

public class Mundo69 extends AgenteJ {

    @Override
    public void inteligencia() {
        andarDireita();
        int ano1 = getInt();
        andarDireita();
        int ano2 = getInt();
        idade(ano1, ano2);
    }

    public void idade(int ano1, int ano2) {
        if (ano2 > ano1){
            if (ano2 > 1900){
                diga(ano2 - ano1);
                return;
            }
        }
        diga("ano de nascimento inválido");        
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo69.xml");
    }

}
