package br.udesc.lagentj.objetivos;

import br.udesc.lagentj.Alien;
import br.udesc.lagentj.MundoVisual;
import br.udesc.lagentj.Numero;
import br.udesc.lagentj.ObjetoDoMundo;
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

    public static List<Numero> getNumeros() {
        List<Numero> numeros = new ArrayList();
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundoAdapter adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals("Numero")) {
                numeros.add((Numero) adapter);
            }
        }
        return numeros;
    }

    public static List<ObjetoMundoImpl> getObjetos() {
        return mundoVisual.getMundoAgenteJ().getMundo().getObjetos();
    }

    public static Object getElement(int index, String tipo) {
        List<ObjetoMundoImpl> lista = new ArrayList<ObjetoMundoImpl>();
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundoAdapter adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals(tipo)) {
                lista.add(objeto);
            }
        }
        if (tipo.equals("Numero")) {
            return ((Numero) lista.get(index).getObjetoMundo()).getValor();
        }
        return lista.get(index);
    }
    
    public static Object getElements(String tipo) {
        List<ObjetoDoMundo> lista = new ArrayList<ObjetoDoMundo>();
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundoAdapter adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals(tipo)) {
                lista.add(adapter);
            }
        }
        return lista;
    }
    
    public static Object maisLonge(List<ObjetoDoMundo> objetos) {
        Alien a = null;
        for (ObjetoDoMundo alien : objetos) {
            if (a == null){
                a = (Alien) alien;
            } else {
                if (alien.getLinha() + alien.getColuna() >= a.getLinha() + a.getColuna()){
                    if (alien.getLinha() + alien.getColuna() == a.getLinha() + a.getColuna()){
                        if (alien.getColuna() > a.getColuna()){
                            a = (Alien) alien;
                        }
                    } else {
                        a = (Alien) alien;
                    }
                }
            }
        }
        return a;
    }
    
    public static int getElementColuna(ObjetoDoMundo obj) {
        return obj.getColuna();
    }
    
    public static int getElementLinha(ObjetoDoMundo obj) {
        return obj.getLinha();
    }

    public static int getElementQuadrant(int index, String tipo, int xd, int yd) {
        ObjetoDoMundo obj = null;
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundo adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals(tipo)) {
                obj = adapter;
                break;
            }
        }
        int x = obj.getColuna();
        int y = obj.getLinha();
        int xl = getQtdadeCol() / xd;
        int yl = getQtdadeLin() / yd;
        int quadrante = 0;
        if (y < yl) {
            if (x < xl) {
                quadrante = 1;
            } else {
                quadrante = 2;
            }
        } else {
            if (x < xl) {
                quadrante = 3;
            } else {
                quadrante = 4;
            }
        }
        return quadrante;
    }

    public static List<ObjetoDoMundo> getQuadrantElements(int quadrante, String tipo, int xd, int yd) {
        List<ObjetoDoMundo> objetos = new ArrayList();
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundo adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals(tipo)) {
                int x = adapter.getColuna();
                int y = adapter.getLinha();
                int xl = getQtdadeCol() / xd;
                int yl = getQtdadeLin() / yd;
                int objquadrante = 0;
                if (y < yl) {
                    if (x < xl) {
                        objquadrante = 1;
                    } else {
                        objquadrante = 2;
                    }
                } else {
                    if (x < xl) {
                        objquadrante = 3;
                    } else {
                        objquadrante = 4;
                    }
                }
                if (objquadrante == quadrante){
                    objetos.add(adapter);
                }
            }
        }
        return objetos;
    }

    public static int getElementPosX(int index, String tipo) {
        List<ObjetoMundoImpl> lista = new ArrayList<ObjetoMundoImpl>();
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundoAdapter adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals(tipo)) {
                lista.add(objeto);
            }
        }
        return lista.get(index).getX();
    }
    
    public static int getElementPosY(int index, String tipo) {
        List<ObjetoMundoImpl> lista = new ArrayList<ObjetoMundoImpl>();
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            ObjetoDoMundoAdapter adapter = objeto.getObjetoMundo();
            if (adapter.getSouDoTipo().equals(tipo)) {
                lista.add(objeto);
            }
        }
        return lista.get(index).getY();
    }
    
    public static int somarNumeros(List<Numero> numeros){
        int result = 0;
        for (Numero numero : numeros) {
            result += numero.getValor();
        }
        return result;
    }

    

    public static int getQtdadeCol() {
        return mundoVisual.getMundoAgenteJ().getMundo().getQtdadeCol();
    }

    public static int getQtdadeLin() {
        return mundoVisual.getMundoAgenteJ().getMundo().getQtdadeLin();
    }

    public static int getCount(String tipo) {
        int c = 0;
        for (ObjetoMundoImpl objeto : mundoVisual.getMundoAgenteJ().getMundo().getObjetos()) {
            if (objeto.getObjetoMundo().getSouDoTipo().equals(tipo)) {
                c++;
            }
        }
        return c;
    }

}
