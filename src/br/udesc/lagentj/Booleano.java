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

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class Booleano extends ObjetoDoMundoAdapter
{

    public Booleano()
    {
    }

    public boolean getValor()
    {
        return valor;
    }

    public void setValorInicial(boolean valor)
    {
        this.valor = valor;
    }

    public void setValor(boolean numero)
    {
        valor = numero;
        try
        {
            repintar();
        }
        catch(MundoException e)
        {
            e.printStackTrace();
        }
    }

    public ImageIcon criarImagem()
    {
    	/*
        BufferedImage img = new BufferedImage(MundoVisual.TAMCELL, MundoVisual.TAMCELL, 1);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, MundoVisual.TAMCELL, MundoVisual.TAMCELL);
        g.setColor(Color.black);
        g.setFont(fonte);
		
        int x = (int) (MundoVisual.TAMCELL * 0.1);
		int y = (int) (MundoVisual.TAMCELL * 0.5);

		g.drawString(String.valueOf(valor), x, y);
        ImageIcon imagem = new ImageIcon(img);
        return imagem;
        */
        BufferedImage img = new BufferedImage(50, 50, 1);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 50, 50);
        g.setColor(Color.black);
        g.setFont(fonte);
		
		g.drawString(String.valueOf(valor), 10, 25);
        ImageIcon imagem = new ImageIcon(img);
        return imagem;
    }

    public void executar()
        throws Exception
    {
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(valor))).toString();
    }

    private boolean valor;
    private static Font fonte = new Font("Arial", 0, 18);

}