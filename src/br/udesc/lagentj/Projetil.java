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
import br.udesc.lagentj.suporte.LoadImage;
import br.udesc.lagentj.suporte.ObjetoMundoImpl;

import javax.swing.ImageIcon;

/**
 * 
 * @author Adilson Vahldick 
 */
public class Projetil extends ObjetoDoMundoAdapter
{

	public Projetil(Direcao direcao)
    {
        this.direcao = direcao;
        this.img = LoadImage.getInstance().getIcon("imagens/bala-"+direcao.ordinal()+".png");
        new ObjetoMundoImpl(this);
    }

    public ImageIcon criarImagem()
    {
        return img;
    }

    public void executar()
        throws Exception
    {
        do
            try
            {
                switch(direcao)
                {
                case ABAIXO: // '\002'
                    andarAbaixo();
                    break;

                case ACIMA: // '\004'
                    andarAcima();
                    break;

                case DIREITA: // '\003'
                    andarDireita();
                    break;

                case ESQUERDA: // '\001'
                    andarEsquerda();
                    break;
                }
            }
            catch(MundoException mundoexception)
            {
                removerMe();
            	return;
            }
        while(true);
    }

    private ImageIcon img;
    private Direcao direcao;
}