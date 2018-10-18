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

import br.udesc.lagentj.ObjetoDoMundo;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class ElementoExercicio
{

    public ElementoExercicio()
    {
        bloqueado = true;
        random = new AgenteJRandom();
        energia = -1;
        qtdade = 1;
    }

    public int getQtdade()
    {
        return qtdade;
    }

    public void setQtdade(int qtdade)
    {
        this.qtdade = qtdade;
    }

    public int getEnergia()
    {
        return energia;
    }

    public void setEnergia(int energia)
    {
        this.energia = energia;
    }

    public boolean isUsarEnergia()
    {
        return energia > 0;
    }

    public void setRandom(AgenteJRandom random)
    {
        this.random = random;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getIdDependeX()
    {
        return idDependeX;
    }

    public void setIdDependeX(String idDependeX)
    {
        this.idDependeX = idDependeX;
    }

    public String getIdDependeY()
    {
        return idDependeY;
    }

    public void setIdDependeY(String idDependeY)
    {
        this.idDependeY = idDependeY;
    }
    
    public String getIdDependeValorX() 
    {
		return idDependeValorX;
	}
    
    public void setIdDependeValorX(String idDependeValorX) 
    {
		this.idDependeValorX = idDependeValorX;
	}
    
    public String getIdDependeValorY() 
    {
		return idDependeValorY;
	}
    
    public void setIdDependeValorY(String idDependeValorY) 
    {
		this.idDependeValorY = idDependeValorY;
	}

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public String getClazz()
    {
        return clazz;
    }

    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }

    public boolean isBloqueado()
    {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado)
    {
        this.bloqueado = bloqueado;
    }

    public void outrasAtribuicoes(ObjetoMundoImpl objetomundoimpl, ObjetoDoMundo objetodomundo)
    {
    }

    public boolean isDependente()
    {
        return idDependeX != null || idDependeY != null || idDependeValorX != null || idDependeValorY != null ;
    }

    public AgenteJRandom getRandom()
    {
        return random;
    }
    
    public ElementoExercicio clonar()
        throws InstantiationException, IllegalAccessException
    {
        ElementoExercicio e = (ElementoExercicio)getClass().newInstance();
        e.setBloqueado(bloqueado);
        e.setClazz(clazz);
        e.setEnergia(energia);
        e.setId(id);
        e.setIdDependeValorX(idDependeValorX);
        e.setIdDependeValorY(idDependeValorY);
        e.setIdDependeX(idDependeX);
        e.setIdDependeY(idDependeY);
        e.setQtdade(qtdade);
        e.setRandom(random);
        e.setX(x);
        e.setY(y);
        return e;
    }

    private int x;
    private int y;
    private boolean bloqueado;
    private String clazz;
    private String id;
    private String idDependeX;
    private String idDependeY;
    
    private String idDependeValorX;
    private String idDependeValorY;
    
    private AgenteJRandom random;
    private int energia;
    private int qtdade;
}