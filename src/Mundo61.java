

import br.udesc.lagentj.AgenteJ;
import br.udesc.lagentj.MundoVisual;

public class Mundo61 extends AgenteJ {
    @Override
    public void inteligencia() {  	
    	andarDireita();
        int a = getInt();
        andarDireita();
        int b = getInt();
        andarDireita();
        maior(a, b);
    }
    
    public void maior(int a, int b){
        if (a > b){
            diga(a);
        } else {
            if (b > a){
                diga(b);
            } else {
                diga("IGUAIS");
            }
        } 
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo61.xml");
    }

}
