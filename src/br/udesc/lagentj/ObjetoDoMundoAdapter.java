/**
 * @license
 * Learning with AgentJ
 *
 * Copyright 2018 Google Inc.
 * https://sites.google.com/site/adilsonv77
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.udesc.lagentj;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

import br.udesc.lagentj.exceptions.MundoException;
import br.udesc.lagentj.suporte.ObjetoMundoImpl;

/**
 * 
 * @author Adilson Vahldick 
 */
public abstract class ObjetoDoMundoAdapter implements ObjetoDoMundo {

	public ObjetoDoMundoAdapter() {
		new ObjetoMundoImpl(this);
	}

	public void setObjetoMundoImpl(ObjetoMundoImpl objetoMundoImpl) {
		this.objetoMundoImpl = objetoMundoImpl;
	}

	public ObjetoMundoImpl getObjetoMundoImpl() {
		return objetoMundoImpl;
	}

	public void andarAbaixo() throws MundoException {
		objetoMundoImpl.andarAbaixo();
	}

	public void andarAcima() throws MundoException {
		objetoMundoImpl.andarAcima();
	}

	public void andarDireita() throws MundoException {
		objetoMundoImpl.andarDireita();
	}

	public void andarEsquerda() throws MundoException {
		objetoMundoImpl.andarEsquerda();
	}

	public void diga(String texto) throws MundoException {
		objetoMundoImpl.diga(texto);
	}

	public void diga(Object object) throws MundoException {
		objetoMundoImpl.diga(object);
	}

	public void limparConsole() throws MundoException {
		objetoMundoImpl.limparConsole();
	}

	public boolean ehFim(Direcao direcao) throws MundoException {
		return objetoMundoImpl.ehFim(direcao);
	}

	public boolean ehVazio(Direcao direcao) throws MundoException {
		return objetoMundoImpl.ehVazio(direcao);
	}

	@Override
	public <T extends ObjetoDoMundo> T getObjeto(Direcao direcao) throws MundoException {
		ObjetoMundoImpl ret = objetoMundoImpl.getObjeto(direcao);
		if (ret == null)
			return null;
		else
			return ret.getObjetoMundo();
	}

	@Override
	public <T extends ObjetoDoMundo> T getObjetoColLin(int col, int lin) throws MundoException {
		ObjetoMundoImpl ret = objetoMundoImpl.getObjeto(col, lin);
		if (ret == null)
			return null;
		else
			return ret.getObjetoMundo();
	}

	public int getColuna() {
		return objetoMundoImpl.getX();
	}
	
	public int getLinha() {
		return objetoMundoImpl.getY();
	}
	
	public void repintar() throws MundoException {
		objetoMundoImpl.setImage(null);
		objetoMundoImpl.repintar();
	}

	public int getUltimaTeclaPress() throws MundoException {
		return objetoMundoImpl.getUltimaTeclaPress();
	}

	public boolean ehDependenteEnergia() {
		return objetoMundoImpl.isUsarEnergia();
	}

	public int getEnergia() {
		return objetoMundoImpl.getEnergia();
	}

	public int getMaxEnergia() {
		return objetoMundoImpl.getMaxEnergia();
	}

	public void esperar(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ObjetoDoMundo esperarAlguem() {
		terminarEspera = null;
		objetoMundoImpl.esperarAlguem();
		for (int conta = 0; terminarEspera == null && conta < 5; conta++)
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		return terminarEspera;
	}

	public void chegouUmObjetoNaPosicao(ObjetoDoMundo objetoChegou) {
		terminarEspera = objetoChegou;
	}

	public void adicionarObjetoNoMundo(ObjetoDoMundo objeto, Direcao direcao)
			throws MundoException {
		doAdicionarObj(objeto, direcao, false);
	}

	public void adicionarObjetoNoMundoEmbaixo(ObjetoDoMundo objeto,
			Direcao direcao) throws MundoException {
		doAdicionarObj(objeto, direcao, true);
	}

	private void doAdicionarObj(ObjetoDoMundo objeto, Direcao direcao,
			boolean embaixo) {
		int x = getColuna();
		int y = getLinha();
		switch (direcao) {
		case ABAIXO: // '\002'
			y++;
			break;

		case ACIMA: // '\004'
			y--;
			break;

		case DIREITA: // '\001'
			x++;
			break;

		case ESQUERDA: // '\003'
			x--;
			break;
		default :
		}
		doAdicionarObjXY(objeto, x, y, embaixo);
	}

	private void doAdicionarObjXY(ObjetoDoMundo objeto, int x, int y,
			boolean embaixo) {
		ObjetoMundoImpl obj = ((ObjetoDoMundoAdapter) objeto).objetoMundoImpl;
		if (obj.getMundo() != null)
			throw new MundoException("O objeto j\341 existe no mundo");
		obj.setX(x);
		obj.setY(y);
		obj.setMundo(objetoMundoImpl.getMundo());
		if (embaixo)
			objetoMundoImpl.inserirObjetoNoMundo(obj);
		else
			objetoMundoImpl.adicionarObjetoNoMundo(obj);
		(new Thread(obj)).start();
	}

	public void adicionarObjetoNoMundoColLin(ObjetoDoMundo objeto, int col, int lin)
			throws MundoException {
		doAdicionarObjXY(objeto, col, lin, false);
	}

	public void removerObjetoDoMundo(ObjetoDoMundo objeto)
			throws MundoException {
		ObjetoMundoImpl obj = ((ObjetoDoMundoAdapter) objeto).objetoMundoImpl;
		obj.removerMe();
	}

	public boolean jahExplodiu() throws MundoException {
		return objetoMundoImpl.isExplodiu();
	}

	public void removerMe() throws MundoException {
		objetoMundoImpl.removerMe();
	}

	public int getTempoEspera() {
		return objetoMundoImpl.getTempoEspera();
	}

	public void setTempoEspera(int milisegundos) {
		objetoMundoImpl.setTempoEspera(milisegundos);
	}
	
	public int sortear() {
		return sorteio.nextInt();
	}

	public void mudarPosicao(int col, int lin) {
		objetoMundoImpl.mudarPosicao(col, lin);
	}

	public boolean ehObjetoDoMundoTipo(String classe, Direcao direcao) {
		ObjetoDoMundo obj = getObjeto(direcao);
		if (obj == null)
			return false;
		else
			return obj.getClass().getSimpleName().toUpperCase().equals(
					classe.toUpperCase());
	}

	public boolean ehBloqueado() {
		return objetoMundoImpl.isBloqueado();
	}

	public void bloquear() {
		objetoMundoImpl.setBloqueado(true);
	}

	public void desbloquear() {
		objetoMundoImpl.setBloqueado(false);
	}

	public String getSouDoTipo() {
		return getClass().getSimpleName();
	}

	public boolean jahRemovido() {
		return objetoMundoImpl.isRemovido();
	}

	@SuppressWarnings("unchecked")
	public <T extends ObjetoDoMundoAdapter> T[] getObjetos(Class<T> clazz) {
		
		List<T> l = objetoMundoImpl.getObjetos(clazz);
		
		T[] ret = (T[]) new ObjetoDoMundoAdapter[l.size()];
		
		for (int x = 0; x < l.size(); x++)
			ret[x] = l.get(x);
		
		return ret;
	}

	public void troqueImagem() {
		objetoMundoImpl.changeImage();
	}

	@Override
	public void colidido(ObjetoDoMundo objetoMundo) {
		// aguardar que os descendentes implementem
	}

	public void invoqueApos(final int segundos, final String nomeMetodo) {
		final ObjetoDoMundoAdapter me = this;
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(segundos*1000);
					Method m = me.getClass().getMethod(nomeMetodo);
					m.setAccessible(true);
					m.invoke(me);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	public int getInt() throws MundoException {
		if (objetoMundoImpl.getObjeto(AQUIMESMO) != null && ehObjetoDoMundoTipo("Numero", AQUIMESMO)) {
			Numero n = getObjeto(AQUIMESMO);
			return n.getValor();
		}
		return 0;
	}
	
	public int getInt(Direcao direcao) throws MundoException {
		if (objetoMundoImpl.getObjeto(direcao) == null) {
			throw new NullPointerException();
		}
		
		if (ehObjetoDoMundoTipo("Numero", direcao)) {
			Numero n = getObjeto(direcao);
			return n.getValor();
		}
		return 0;
	}
	
	public static final Direcao ESQUERDA;
	public static final Direcao DIREITA;
	public static final Direcao ACIMA;
	public static final Direcao ABAIXO;
	public static final Direcao AQUIMESMO;
	public static final int TECLACIMA = 38;
	public static final int TECLABAIXO = 40;
	public static final int TECLAESQUERDA = 37;
	public static final int TECLADIREITA = 39;
	public static final int TECLAESPACO = 32;
	protected ObjetoMundoImpl objetoMundoImpl;
	private ObjetoDoMundo terminarEspera;
	private static final Random sorteio = new Random();

	static {
		ESQUERDA = Direcao.ESQUERDA;
		DIREITA = Direcao.DIREITA;
		ACIMA = Direcao.ACIMA;
		ABAIXO = Direcao.ABAIXO;
		AQUIMESMO = Direcao.AQUIMESMO;
	}
}