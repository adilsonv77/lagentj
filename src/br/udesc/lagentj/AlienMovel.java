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

import java.util.*;
import javax.swing.ImageIcon;

import br.udesc.lagentj.suporte.LoadImage;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class AlienMovel extends ObjetoDoMundoAdapter
{

	public AlienMovel()
    {
    }

    public String toString()
    {
        return "AlienMovel";
    }

    public ImageIcon criarImagem()
    {
        return LoadImage.getInstance().getIcon("imagens/alienmovel.jpg");
    }
    
    public void executar()
        throws Exception
    {
        Random sorteio = new Random();
        List<Direcao> direcoes = new ArrayList<Direcao>();
        do {
        	
        	direcoes.clear();
            if(!ehFim(ACIMA) && ehVazio(ACIMA))
                direcoes.add(ACIMA);
            if(!ehFim(ABAIXO) && ehVazio(ABAIXO))
                direcoes.add(ABAIXO);
            if(!ehFim(ESQUERDA) && ehVazio(ESQUERDA))
                direcoes.add(ESQUERDA);
            if(!ehFim(DIREITA) && ehVazio(DIREITA))
                direcoes.add(DIREITA);
            
            if(direcoes.size() == 0) {
            	MundoVisual.setAtributo("AlienMovel preso", true);
                return;
            }
            
           	int destino = sorteio.nextInt(direcoes.size());
           	
           	switch(direcoes.get(destino))
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
                
            default: ;
            }
        } while(true);
        
    }
    
 }