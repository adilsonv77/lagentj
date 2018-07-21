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

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class AgenteJRandom
{

    public AgenteJRandom()
    {
        tipoRandom = TipoRandom.NENHUM;
        limiteInfRandomX = 0;
        limiteInfRandomY = 0;
        limiteSupRandomX = -1;
        limiteSupRandomY = -1;
    }

    public int getLimiteSupRandomX()
    {
        return limiteSupRandomX;
    }

    public void setLimiteSupRandomX(String limiteSupRandomX)
    {
        this.limiteSupRandomX = Integer.valueOf(limiteSupRandomX).intValue();
    }

    public int getLimiteSupRandomY()
    {
        return limiteSupRandomY;
    }

    public void setLimiteSupRandomY(String limiteSupRandomY)
    {
        this.limiteSupRandomY = Integer.valueOf(limiteSupRandomY).intValue();
    }

    public int getLimiteInfRandomY()
    {
        return limiteInfRandomY;
    }

    public void setLimiteInfRandomY(String limiteInfRandomY)
    {
        this.limiteInfRandomY = Integer.valueOf(limiteInfRandomY).intValue();
    }

    public int getLimiteInfRandomX()
    {
        return limiteInfRandomX;
    }

    public void setLimiteInfRandomX(String limiteInfRandomX)
    {
        this.limiteInfRandomX = Integer.valueOf(limiteInfRandomX).intValue();
    }

    public boolean isRandom()
    {
        return tipoRandom != TipoRandom.NENHUM;
    }

    public boolean isRandomXY()
    {
        return tipoRandom == TipoRandom.XY;
    }

    public void setRandomXY()
    {
        tipoRandom = TipoRandom.XY;
    }

    public boolean isRandomX()
    {
        return tipoRandom == TipoRandom.X;
    }

    public void setRandomX()
    {
        tipoRandom = TipoRandom.X;
    }

    public boolean isRandomY()
    {
        return tipoRandom == TipoRandom.Y;
    }

    public void setRandomY()
    {
        tipoRandom = TipoRandom.Y;
    }

    private TipoRandom tipoRandom;
    private int limiteInfRandomX;
    private int limiteInfRandomY;
    private int limiteSupRandomX;
    private int limiteSupRandomY;
}