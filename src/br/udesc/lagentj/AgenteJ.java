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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;

import br.udesc.lagentj.exceptions.MundoException;
import br.udesc.lagentj.suporte.LoadImage;

/**
 * 
 * @author Adilson Vahldick
 */
public abstract class AgenteJ extends ObjetoDoMundoAdapter {

	private ImageIcon[] images;
	private int imgIndex;
	
	public AgenteJ() {
		images = new ImageIcon[2];

		images[0] = LoadImage.getInstance().getIcon("imagens/agentej-dir.gif");
		images[1] = LoadImage.getInstance().getIcon("imagens/agentej-esq.gif");
		
		imgIndex = 0;

	}
	
	public ImageIcon criarImagem() {
		ImageIcon image = images[imgIndex];
		
		if (ehDependenteEnergia()) {
			BufferedImage img = new BufferedImage(50, 50, 1);
			Graphics2D g = img.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, 50, 50);
			g.drawImage(image.getImage(), 2, 2, null);
			float fator = (float) getEnergia()
					/ ((float) getMaxEnergia() * 1.0F);
			int altura = Math.round(40F * fator);
			g.setColor(Color.red);
			g.fillRoundRect(40, 45 - altura, 5, altura, 5, 5);
			g.setColor(Color.black);
			g.drawRoundRect(40, 5, 5, 40, 5, 5);
			image = new ImageIcon(img);
		}
		
		return image;
	}

	public void executar() throws Exception {
		MundoException ex = null;
		try {
			esperar(1);
			inteligencia();
		} catch (MundoException e) {
			ex = e;
		}
		objetoMundoImpl.pararMundo();
		if (ex != null)
			throw ex;
		else
			return;
	}

	public abstract void inteligencia() throws Exception;

	public boolean ehVazio(int coluna, int linha) {
		return objetoMundoImpl.ehVazio(coluna, linha);
	}

	public int getQtdadeLinMundo() {
		return objetoMundoImpl.getQtdadeLin();
	}

	public int getQtdadeColMundo() {
		return objetoMundoImpl.getQtdadeCol();
	}

	public void pararMundo() throws MundoException {
		objetoMundoImpl.pararMundo();
	}

	public <T extends ObjetoDoMundo> List<T> getListaObjetos(Class<T> clazz) {
		return this.objetoMundoImpl.getObjetos(clazz);
	}
	
	@Override
	public void andarDireita() throws MundoException {
		
		if (imgIndex == 1) {
			imgIndex = 0;
			troqueImagem();
		}
		
		super.andarDireita();
	}
	
	@Override
	public void andarEsquerda() throws MundoException {
		if (imgIndex == 0) {
			imgIndex = 1;
			troqueImagem();
		}
		
		super.andarEsquerda();
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
	
	public static final double VERSAO = 1.03;
}