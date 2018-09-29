
import br.udesc.lagentj.*;

public class Mundo60 extends AgenteJ {

    @Override
    public void inteligencia() {
        andar(ABAIXO, 2);
        andar(DIREITA, 4);
        andar(ACIMA, 2);
    }

    public void andar(Direcao d, int casas) {
        switch (d) {
            case ACIMA:
                for (int i = 0; i < casas; i++) {
                    andarAcima();
                }
                break;
            case ABAIXO:
                for (int i = 0; i < casas; i++) {
                    andarAbaixo();
                }
                break;
            case ESQUERDA:
                for (int i = 0; i < casas; i++) {
                    andarEsquerda();
                }
                break;
            case DIREITA:
                for (int i = 0; i < casas; i++) {
                    andarDireita();
                }
                break;
        }
    }

    public static void main(String[] args) {
        MundoVisual.iniciar("Mundo60.xml");

    }

}
