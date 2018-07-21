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

	public  void executar() throws Exception;

	public  void diga(String s) throws MundoException;

	public  void diga(Object obj) throws MundoException;

	public  void limparConsole() throws MundoException;

	public  void andarEsquerda() throws MundoException;

	public  void andarDireita() throws MundoException;

	public  void andarAcima() throws MundoException;

	public  void andarAbaixo() throws MundoException;

	public  boolean ehVazio(Direcao direcao) throws MundoException;

	public  boolean ehFim(Direcao direcao) throws MundoException;

	public  <T extends ObjetoDoMundo> T getObjeto(Direcao direcao)
			throws MundoException;

	public  <T extends ObjetoDoMundo> T getObjetoColLin(int col, int lin)
			throws MundoException;

	public  int getColuna();

	public  int getLinha();

	public  ImageIcon criarImagem();

	public  void setObjetoMundoImpl(ObjetoMundoImpl objetomundoimpl);

	public  void repintar() throws MundoException;

	public  int getUltimaTeclaPress() throws MundoException;

	public  void chegouUmObjetoNaPosicao(ObjetoDoMundo objetodomundo);

	public  ObjetoMundoImpl getObjetoMundoImpl();

	public  void removerObjetoDoMundo(ObjetoDoMundo objetodomundo)
			throws MundoException;

	public  void adicionarObjetoNoMundoColLin(ObjetoDoMundo objetodomundo,
			int col, int lin) throws MundoException;

	public  void adicionarObjetoNoMundo(ObjetoDoMundo objetodomundo,
			Direcao direcao) throws MundoException;

	public  void adicionarObjetoNoMundoEmbaixo(
			ObjetoDoMundo objetodomundo, Direcao direcao) throws MundoException;

	public  void removerMe() throws MundoException;

	public  boolean jahExplodiu() throws MundoException;

	public  int getTempoEspera();

	public  void setTempoEspera(int milisegundos);

	public  boolean ehBloqueado();

	public  void bloquear();

	public  void desbloquear();

	public  String getSouDoTipo();

	public void colidido(ObjetoDoMundo objetoMundo);
}