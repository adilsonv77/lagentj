import br.udesc.lagentj.*;

public class Mundo01 extends AgenteJ {
	
	@Override
	public void inteligencia() {
		andar(ABAIXO, 1);
		andar(ESQUERDA, 2);
		andar(ABAIXO, 3);
		andar(ESQUERDA, 1);
		andar(ABAIXO, 2);
		andar(DIREITA, 3);
		andar(ACIMA, 1);
		andar(DIREITA, 1);
		andar(ACIMA, 2);
		andar(DIREITA, 1);
	}
	
	public void andar(Direcao d, int casas) {
		switch(d) {
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
		MundoVisual.iniciar("Mundo01.xml");
	}
	

}
