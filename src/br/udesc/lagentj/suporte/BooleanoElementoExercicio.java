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
package br.udesc.lagentj.suporte;

import java.util.Random;

import br.udesc.lagentj.Booleano;
import br.udesc.lagentj.ObjetoDoMundo;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class BooleanoElementoExercicio extends ElementoExercicio
{

    public BooleanoElementoExercicio()
    {
        valor = true;
        random = false;
        setClazz("br.udesc.lagentj.Booleano");
        setBloqueado(false);
    }

    public boolean getValor()
    {
        if(random)
        {
            Random sorteio = new Random();
            valor = sorteio.nextInt(2) == 1;
        }
        return valor;
    }

    public void setValor(boolean valor)
    {
        this.valor = valor;
    }

    public void outrasAtribuicoes(ObjetoMundoImpl obj, ObjetoDoMundo objMundo)
    {
        Booleano bool = (Booleano)objMundo;
        bool.setValorInicial(getValor());
    }

    public boolean isRandom()
    {
        return random;
    }

    public void setRandom(String random)
    {
        this.random = Boolean.valueOf(random).booleanValue();
    }

    public ElementoExercicio clonar()
        throws InstantiationException, IllegalAccessException
    {
        BooleanoElementoExercicio e = (BooleanoElementoExercicio)super.clonar();
        e.setValor(valor);
        e.setRandom((new StringBuilder()).append(random).toString());
        return e;
    }

    private boolean valor;
    private boolean random;
}