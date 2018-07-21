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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import br.udesc.lagentj.suporte.LoadImage;

/**
 * 
 * @author Adilson Vahldick
 */
public class Tesouro extends Numero {

	public Tesouro() {
	}

	public ImageIcon criarImagem() {
		ImageIcon image = LoadImage.getInstance().getIcon("imagens/tesouro.png");
		BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = img.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, 50, 50);
		g.drawImage(image.getImage(), 2, 2, null);
		
		g.fillRect(30, 30, 20, 20);
		g.setColor(Color.black);
		g.drawRect(30, 30, 19, 19);
		
		g.setFont(fonte);
		g.setColor(Color.red);
		g.drawString(String.valueOf(getValor()), 32, 45);
		
		return new ImageIcon(img);
	}

	private static Font fonte = new Font("Arial", Font.PLAIN, 14);
	
}