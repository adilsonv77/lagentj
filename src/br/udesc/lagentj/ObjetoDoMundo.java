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

import br.udesc.lagentj.exceptions.MundoException;
import br.udesc.lagentj.suporte.ObjetoMundoImpl;

import javax.swing.ImageIcon;

/**
 * 
 * @author Adilson Vahldick
 */
public interface ObjetoDoMundo {

	void executar() throws Exception;

	void diga(String s) throws MundoException;

	void diga(Object obj) throws MundoException;

	void limparConsole() throws MundoException;

	void andarEsquerda() throws MundoException;

	void andarDireita() throws MundoException;

	void andarAcima() throws MundoException;

	void andarAbaixo() throws MundoException;

	boolean ehVazio(Direcao direcao) throws MundoException;

	boolean ehFim(Direcao direcao) throws MundoException;

	<T extends ObjetoDoMundo> T getObjeto(Direcao direcao)
			throws MundoException;

	<T extends ObjetoDoMundo> T getObjetoColLin(int col, int lin)
			throws MundoException;

	int getColuna();

	int getLinha();

	ImageIcon criarImagem();

	void setObjetoMundoImpl(ObjetoMundoImpl objetomundoimpl);

	void repintar() throws MundoException;

	int getUltimaTeclaPress() throws MundoException;

	void chegouUmObjetoNaPosicao(ObjetoDoMundo objetodomundo);

	ObjetoMundoImpl getObjetoMundoImpl();

	void removerObjetoDoMundo(ObjetoDoMundo objetodomundo)
			throws MundoException;

	void adicionarObjetoNoMundoColLin(ObjetoDoMundo objetodomundo,
			int col, int lin) throws MundoException;

	void adicionarObjetoNoMundo(ObjetoDoMundo objetodomundo,
			Direcao direcao) throws MundoException;

	void adicionarObjetoNoMundoEmbaixo(
			ObjetoDoMundo objetodomundo, Direcao direcao) throws MundoException;

	void removerMe() throws MundoException;

	boolean jahExplodiu() throws MundoException;

	int getTempoEspera();

	void setTempoEspera(int milisegundos);

	boolean ehBloqueado();

	void bloquear();

	void desbloquear();

	String getSouDoTipo();

	void colidido(ObjetoDoMundo objetoMundo);
	
	
}