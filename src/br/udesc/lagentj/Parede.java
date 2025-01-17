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

import javax.swing.ImageIcon;

import br.udesc.lagentj.suporte.LoadImage;

/**
 * 
 * @author Adilson Vahldick 
 */
public class Parede extends ObjetoDoMundoAdapter
{

    public Parede()
    {
    }

    public String toString()
    {
        return "Parede";
    }

    public ImageIcon criarImagem()
    {
        return LoadImage.getInstance().getIcon("imagens/parede.png");
    }

    public void executar()
        throws Exception
    {
    }
}