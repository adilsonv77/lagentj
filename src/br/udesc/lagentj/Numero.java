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

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import br.udesc.lagentj.exceptions.MundoException;
import br.udesc.lagentj.suporte.LoadImage;

/**
 * 
 * @author Adilson Vahldick
 */
public class Numero extends ObjetoDoMundoAdapter {

	public Numero() {
	}

	public int getValor() {
		return valor;
	}

	public void setValorInicial(int valor) {
		this.valor = valor;
	}

	public void setValor(int numero) {
		valor = numero;
		try {
			repintar();
		} catch (MundoException e) {
			e.printStackTrace();
		}
	}

	public ImageIcon criarImagem() {

		BufferedImage img = new BufferedImage(50, 50,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = img.createGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, 50, 50);

		if (source != null) {
			ImageIcon image = LoadImage.getInstance().getIcon(source);		
			g.drawImage(image.getImage(), 2, 2, null);
			g.fillRect(30, 30, 20, 20);
			g.setColor(Color.black);
			g.drawRect(30, 30, 19, 19);
			g.setFont(fonteComImagem);
			g.setColor(Color.red);
			g.drawString(String.valueOf(getValor()), 32, 45);
		} else {
		
			g.setColor(Color.black);
			String v = String.valueOf(valor);
			if (v.length()>=4)
			  g.setFont(smallfonte);
			else
			  g.setFont(fonte);
			g.drawString(v, 10, 25);
		}
		ImageIcon imagem = new ImageIcon(img);
		return imagem;
	}

	
	public void executar() throws Exception {
	}

	public String toString() {
		return (new StringBuilder(String.valueOf(valor))).toString();
	}

	private String source;
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	private int valor;
	private static Font fonte = new Font("Arial", 0, 18);
	private static Font smallfonte = new Font("Arial", 0, 10);
	private static Font fonteComImagem = new Font("Arial", Font.PLAIN, 14);

}