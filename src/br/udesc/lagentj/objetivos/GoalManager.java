package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.MundoVisual;
import br.udesc.lagentj.Numero;
import br.udesc.lagentj.ObjetoDoMundoAdapter;
import br.udesc.lagentj.suporte.ObjetoMundoImpl;
import java.util.ArrayList;
import java.util.List;

public class GoalManager {

    private static MundoVisual mundoVisual;

    public static MundoVisual getMundoVisual() {
        return mundoVisual;
    }

    public static void setMundoVisual(MundoVisual mundoVisual) {
        GoalManager.mundoVisual = mundoVisual;
    }

    public static int getInt(int x, int y) {
        Numero n = (mundoVisual.getMundoAgenteJ().getMundo().getObjeto(null, x, y).getObjetoMundo());
        return n.getValor();
    }
    
    public static List<Numero> getNumeros(){
        List<Numero> numeros = new ArrayList<>();
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundoAdapter adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals("Numero")){
                numeros.add((Numero) adapter);
            }
        }
        return numeros;
    }

}
