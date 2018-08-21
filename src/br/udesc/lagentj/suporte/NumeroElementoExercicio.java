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

import br.udesc.lagentj.Numero;
import br.udesc.lagentj.ObjetoDoMundo;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class NumeroElementoExercicio extends ElementoExercicio
{

    public NumeroElementoExercicio()
    {
        valor = -1;
        randomInf = -1;
        randomSup = 20;
        setClazz("br.udesc.lagentj.Numero");
        setBloqueado(false);
    }

    public int getValor()
    {
        if(randomInf > -1)
        {
            Random sorteio = new Random();
            valor = sorteio.nextInt((randomSup - randomInf) + 1) + randomInf;
        }
        return valor;
    }

    public void setValor(int valor)
    {
        this.valor = valor;
    }

    public void outrasAtribuicoes(ObjetoMundoImpl obj, ObjetoDoMundo objMundo)
    {
        Numero num = (Numero)objMundo;
        num.setValorInicial(getValor());
        num.setSource(source);
    }

    public int getRandomInf()
    {
        return randomInf;
    }

    public void setRandomInf(String randomInf)
    {
        this.randomInf = Integer.valueOf(randomInf).intValue();
    }

    public int getRandomSup()
    {
        return randomSup;
    }

    public void setRandomSup(String randomSup)
    {
        this.randomSup = Integer.valueOf(randomSup).intValue();
    }

    public String getSource() {
		return source;
	}
    
    public void setSource(String source) {
		this.source = source;
	}
    
    public ElementoExercicio clonar()
        throws InstantiationException, IllegalAccessException
    {
        NumeroElementoExercicio e = (NumeroElementoExercicio)super.clonar();
        e.setValor(valor);
        e.setSource(source);
        e.setRandomInf((new StringBuilder()).append(randomInf).toString());
        e.setRandomSup((new StringBuilder()).append(randomSup).toString());
        return e;
    }

    private int valor;
    private int randomInf;
    private int randomSup;
    private String source;
}