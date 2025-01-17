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

import java.util.List;
import javax.swing.ImageIcon;

/**
 * 
 * @author Adilson Vahldick
 * 
 */
public interface PosicaoMundo extends Runnable {

	public abstract ImageIcon getImage();

	public abstract boolean isBloqueado();

	public abstract List<ObjetoMundoImpl> getObjetos();

	public abstract void parar();
}