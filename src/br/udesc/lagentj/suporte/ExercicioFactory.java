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

import java.io.File;
import java.io.IOException;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import br.udesc.lagentj.objetivos.Ir;

/**
 * 
 * @author Adilson Vahldick
 *
 */
public class ExercicioFactory
{

    public ExercicioFactory()
    {
    }

    public static Exercicio create(String nomeArquivoXML, Class<?> clazz)
        throws IOException, SAXException
    {
        Exercicio exercicio = create(nomeArquivoXML);
        if (clazz != null) {
        	exercicio.setClassAgente(clazz.getName());
        }
        return exercicio;
    }

    public static Exercicio create(String nomeArquivoXML)
        throws IOException, SAXException
    {
        Exercicio exercicio = null;
        Digester d = new Digester();
        exercicio = new Exercicio(nomeArquivoXML);
        d.push(exercicio);
        
        d.addSetProperties("agentej");
        d.addBeanPropertySetter("*/enunciado", "enunciado");
        d.addBeanPropertySetter("*/mundo/qtdadeLin");
        d.addBeanPropertySetter("*/mundo/qtdadeCol");
        d.addBeanPropertySetter("*/mundo/explodir");
        d.addBeanPropertySetter("*/mundo/usarLinhasNaGrade");
		d.addBeanPropertySetter("*/mundo/tamanhoCel");

		addRulesRandom(d, "*/mundo");
        d.addObjectCreate("*/grupo", GrupoObjetos.class);
        d.addSetNext("*/grupo", "addGrupo");
        addRuleObjeto(d, "*/agente");
        d.addSetNext("*/agente", "addAgente");
        addRuleObjeto(d, "*/objeto");
        d.addSetNext("*/objeto", "addElemento");

        addRuleObjetoClass(d, "*/numero", NumeroElementoExercicio.class);
        d.addSetNext("*/numero", "addElemento");
        d.addCallMethod("*/numero/valor", "setRandomInf", 1);
        d.addCallParam("*/numero/valor", 0, "randomInf");
        d.addBeanPropertySetter("*/numero/valor");
        d.addCallMethod("*/numero/valor", "setRandomSup", 1);
        d.addCallParam("*/numero/valor", 0, "randomSup");

        addRuleObjetoClass(d, "*/booleano", BooleanoElementoExercicio.class);
        d.addSetNext("*/booleano", "addElemento");
        d.addCallMethod("*/booleano/valor", "setRandom", 1);
        d.addCallParam("*/booleano/valor", 0, "random");
        d.addBeanPropertySetter("*/booleano/valor");

        addRuleObjetoClass(d, "*/img", ImagemExercicio.class);
        d.addSetProperties("*/img");
        d.addSetNext("*/img", "addElemento");
        
        d.addObjectCreate("*/objetivo", Ir.class);
//        d.addSetNext("*/objetivo", "addObjetivo");
        
//        d.addObjectCreate("*/objetivo", Ir.class);
//        d.addSetNext("*/objetivo", "addObjetivo");
//        d.addBeanPropertySetter( "students/student/name");

        
        File srcfile = new File(nomeArquivoXML);
        d.parse(srcfile);
        exercicio.finalizar();
        return exercicio;
    }

    private static void addRuleObjeto(Digester d, String pattern)
    {
        addRuleObjetoClass(d, pattern, ElementoExercicio.class);
    }

    private static void addRuleObjetoClass(Digester d, String pattern, Class<?> clazz)
    {
        d.addObjectCreate(pattern, clazz);
        d.addSetProperties(pattern, new String[] {
            "class", "qtdade"
        }, new String[] {
            "clazz", "qtdade"
        });
        d.addBeanPropertySetter((new StringBuilder(String.valueOf(pattern))).append("/x").toString());
        d.addBeanPropertySetter((new StringBuilder(String.valueOf(pattern))).append("/y").toString());
        d.addBeanPropertySetter((new StringBuilder(String.valueOf(pattern))).append("/id").toString());
        d.addCallMethod((new StringBuilder(String.valueOf(pattern))).append("/x").toString(), "setIdDependeX", 1);
        d.addCallParam((new StringBuilder(String.valueOf(pattern))).append("/x").toString(), 0, "depende");
        d.addCallMethod((new StringBuilder(String.valueOf(pattern))).append("/y").toString(), "setIdDependeY", 1);
        d.addCallParam((new StringBuilder(String.valueOf(pattern))).append("/y").toString(), 0, "depende");
        d.addCallMethod((new StringBuilder(String.valueOf(pattern))).append("/x").toString(), "setIdDependeValorX", 1);
        d.addCallParam((new StringBuilder(String.valueOf(pattern))).append("/x").toString(), 0, "dependeValor");
        d.addCallMethod((new StringBuilder(String.valueOf(pattern))).append("/y").toString(), "setIdDependeValorY", 1);
        d.addCallParam((new StringBuilder(String.valueOf(pattern))).append("/y").toString(), 0, "dependeValor");
        addRulesRandom(d, pattern);
        d.addBeanPropertySetter((new StringBuilder(String.valueOf(pattern))).append("/energia").toString());
        d.addBeanPropertySetter((new StringBuilder(String.valueOf(pattern))).append("/bloqueado").toString());
    }

    private static void addRulesRandom(Digester d, String pattern)
    {
        d.addObjectCreate((new StringBuilder(String.valueOf(pattern))).append("/random").toString(), AgenteJRandom.class);
        d.addObjectCreate((new StringBuilder(String.valueOf(pattern))).append("/randomX").toString(), AgenteJRandom.class);
        d.addObjectCreate((new StringBuilder(String.valueOf(pattern))).append("/randomY").toString(), AgenteJRandom.class);
        definirRandom(d, (new StringBuilder(String.valueOf(pattern))).append("/random").toString(), "setRandomXY", "limiteInfX", "limiteSupX", "limiteInfY", "limiteSupY");
        definirRandomXY(d, (new StringBuilder(String.valueOf(pattern))).append("/randomX").toString(), "setRandomX", "X", "limiteInf", "limiteSup");
        definirRandomXY(d, (new StringBuilder(String.valueOf(pattern))).append("/randomY").toString(), "setRandomY", "Y", "limiteInf", "limiteSup");
        d.addSetNext((new StringBuilder(String.valueOf(pattern))).append("/random").toString(), "setRandom");
        d.addSetNext((new StringBuilder(String.valueOf(pattern))).append("/randomX").toString(), "setRandom");
        d.addSetNext((new StringBuilder(String.valueOf(pattern))).append("/randomY").toString(), "setRandom");
    }

    private static void definirRandomXY(Digester d, String pattern, String methodName, String chave, String limiteInf, String limiteSup)
    {
        d.addCallMethod(pattern, (new StringBuilder("setLimiteSupRandom")).append(chave).toString(), 1);
        d.addCallParam(pattern, 0, limiteSup);
        d.addCallMethod(pattern, (new StringBuilder("setLimiteInfRandom")).append(chave).toString(), 1);
        d.addCallParam(pattern, 0, limiteInf);
        d.addCallMethod(pattern, methodName);
    }

    private static void definirRandom(Digester d, String pattern, String methodName, String limiteInfX, String limiteSupX, String limiteInfY, String limiteSupY)
    {
        d.addCallMethod(pattern, "setLimiteSupRandomY", 1);
        d.addCallParam(pattern, 0, limiteSupY);
        d.addCallMethod(pattern, "setLimiteInfRandomY", 1);
        d.addCallParam(pattern, 0, limiteInfY);
        d.addCallMethod(pattern, "setLimiteSupRandomX", 1);
        d.addCallParam(pattern, 0, limiteSupX);
        d.addCallMethod(pattern, "setLimiteInfRandomX", 1);
        d.addCallParam(pattern, 0, limiteInfX);
        d.addCallMethod(pattern, methodName);
    }
}