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

import br.udesc.lagentj.objetivos.Objetivo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import br.udesc.lagentj.suporte.Exercicio;
import br.udesc.lagentj.suporte.Mundo;
import br.udesc.lagentj.suporte.ObjetoMundoImpl;
import br.udesc.lagentj.suporte.TamanhoCelula;

public class MundoAgenteJ extends JPanel {

    private class MapaModel extends AbstractTableModel implements ListenerMundo {

        public int getColumnCount() {
            return mundo.getQtdadeCol();
        }

        public int getRowCount() {
            return mundo.getQtdadeLin();
        }

        public Object getValueAt(int lin, int col) {
            return mundo.getImagem(lin, col);
        }

        public void andou(ObjetoMundoImpl objMundo, Direcao direcao, int xAnt,
                int yAnt) {
            fireTableCellUpdated(yAnt, xAnt);
            fireTableCellUpdated(objMundo.getY(), objMundo.getX());
        }

        public void disse(ObjetoMundoImpl objetoMundo, String texto) {

            for (DisseramListener dl : disseramListeners) {
                dl.disse(texto);
            }
            if (console != null) {
                console.setText(console.getText() + "\n" + texto);
            }
        }

        public void limparConsole() {

            for (DisseramListener dl : disseramListeners) {
                dl.limpar();
            }
            if (console != null) {
                console.setText("");
            }
        }

        public void runMundo() {
            mundo.run();
        }

        public void stopMundo() {
            mundo.parar();
        }

        public void finalizar() {
            mundo.removeListener(this);
        }

        public void fimExecucao() {
            verificarObjetivos();
            if (botaoExecutar != null) {
                botaoExecutar.setEnabled(true);
            }
            if (botaoParar != null) {
                botaoParar.setEnabled(false);
            }
            for (FinalizouExecucaoListener f : finalizouListeners) {
                f.finalizouExecucao();
            }
            mv.fim();
        }

        public void repintar() {
            fireTableDataChanged();
        }

        private Mundo mundo;

        public MapaModel(int qtasColunas, int qtasLinhas, TamanhoCelula tamanhoCelula) {
            this.mundo = new Mundo(qtasLinhas, qtasColunas);
            this.mundo.setTamCell(tamanhoCelula.toString());
            this.mundo.addListener(this);
        }

        public MapaModel(Exercicio exercicio) throws Exception {

            this.mundo = exercicio.criarMundo();
            this.mundo.addListener(this);

        }

        private void verificarObjetivos() {
            mv.verificarObjetivos("usarMetodo", null);
            mv.verificarObjetivos("dizerTabuada", null);
        }

    }

    private class MapaRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {

            setIcon((ImageIcon) value);
            setToolTipText(" Col " + column + " Lin " + row);

            return this;
        }

        public MapaRenderer() {
            this.setHorizontalAlignment(0);
        }
    }

    private JTable mapa;
    private MapaModel mapaModel;
    private JComponent botaoExecutar;
    private List<DisseramListener> disseramListeners = new ArrayList<DisseramListener>();
    private List<FinalizouExecucaoListener> finalizouListeners = new ArrayList<FinalizouExecucaoListener>();
    private JComponent botaoParar;
    private Exercicio exercicio;
    private JTextArea console;
    private List<Objetivo> objetivos;
    private MundoVisual mv;

    public Mundo getMundo() {
    	return mapaModel.mundo;
    }
    
    public MundoVisual getMv() {
        return mv;
    }

    public void setMv(MundoVisual mv) {
        this.mv = mv;
    }

    public void addDisseramListener(DisseramListener disseramListener) {
        this.disseramListeners.add(disseramListener);
    }

    public void removeDisseramListener(DisseramListener disseramListener) {
        this.disseramListeners.remove(disseramListener);
    }

    public void addFinalizouExecucaoListener(FinalizouExecucaoListener finalizouExecucaoListener) {
        this.finalizouListeners.add(finalizouExecucaoListener);
    }

    public void removeFinalizouExecucaoListener(FinalizouExecucaoListener finalizouExecucaoListener) {
        this.finalizouListeners.remove(finalizouExecucaoListener);
    }

    public MundoAgenteJ(int qtasColunas, int qtasLinhas, TamanhoCelula tamanhoCelula) {
        constroiGrade(new MapaModel(qtasColunas, qtasLinhas, tamanhoCelula));

        configKeyListener();

    }

    public MundoAgenteJ(Exercicio exercicio) throws Exception {
        this.exercicio = exercicio;
        constroiGrade(new MapaModel(exercicio));
        configKeyListener();
    }

    private void configKeyListener() {
        KeyAdapter keyAdapter = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                pressionadaTecla(e.getKeyCode());
            }

        };
        addKeyListener(keyAdapter);
        mapa.addKeyListener(keyAdapter);
    }

    private void constroiGrade(MapaModel mapaModel) {
        this.mapaModel = mapaModel;
        if (mapa != null) // v 1.7. dava pau na troca de mundo, se eu reaproveitasse a JTable e s� mudasse o TableModel. Parece que ele dava um repaint 
        //  ... depois que a JTable j� tinha trocado a Model. 	
        {
            remove(mapa);
        }
        mapa = new JTable();
        mapa.setModel(mapaModel);
        mapa.setShowGrid(mapaModel.mundo.isUsarLinhasNaGrade());
        if (!mapaModel.mundo.isUsarLinhasNaGrade()) {
            mapa.setIntercellSpacing(new Dimension(0, 0));
        }
        mapa.setRowHeight(mapaModel.mundo.getTamCell());
        mapa.setAutoResizeMode(0);
        mapa.setDefaultRenderer(Object.class, new MapaRenderer());
        mapa.setGridColor(new Color(122, 138, 153)); // no Mac a cor padrao das linhas era branco.
        for (int x = 0; x < mapa.getColumnCount(); x++) {
            mapa.getColumnModel().getColumn(x).setMaxWidth(mapaModel.mundo.getTamCell());
            mapa.getColumnModel().getColumn(x).setMinWidth(mapaModel.mundo.getTamCell());
        }
        setLayout(new BorderLayout());
        add(mapa, BorderLayout.CENTER);

    }

    public void reiniciar() throws Exception {
        
        if (this.exercicio == null) {
            constroiGrade(new MapaModel(mapaModel.mundo.getQtdadeCol(),
                    mapaModel.mundo.getQtdadeLin(),
                    TamanhoCelula.pixelsToTamanhoCelula(mapaModel.mundo.getTamCell())
            )
            );
        } else {
            constroiGrade(new MapaModel(exercicio));
        }
    }

    public void addObjeto(ObjetoDoMundo objetoDoMundo, int x, int y) {

        objetoDoMundo.getObjetoMundoImpl().setX(x);
        objetoDoMundo.getObjetoMundoImpl().setY(y);
        objetoDoMundo.getObjetoMundoImpl().setMundo(mapaModel.mundo);

        mapaModel.mundo.addObjetoMundoImpl(objetoDoMundo.getObjetoMundoImpl());

    }

    public void addObjeto(ObjetoDoMundo objetoDoMundo) {

        Random random = new Random();

        int x = 0, y = 0;
        while (true) {

            x = random.nextInt(mapaModel.mundo.getQtdadeCol());
            y = random.nextInt(mapaModel.mundo.getQtdadeLin());

            if (mapaModel.mundo.getObjeto(null, x, y) == null) {
                break;
            }

        }

        addObjeto(objetoDoMundo, x, y);
    }

    public void removerObjeto(int x, int y) {
        mapaModel.mundo.removerObjeto(mapaModel.mundo.getObjeto(null, x, y));
    }

    public void setBotaoExecutar(JComponent botaoExecutar) {
        this.botaoExecutar = botaoExecutar;

    }

    public void executar() {

        if (botaoExecutar != null) {
            botaoExecutar.setEnabled(false);
        }

        if (botaoParar != null) {
            botaoParar.setEnabled(true);
        }

        mapaModel.runMundo();

    }

    public void setBotaoParar(JComponent botaoParar) {
        this.botaoParar = botaoParar;

        if (botaoParar != null) {
            botaoParar.setEnabled(false);
        }
    }

    public void parar() {
        mapaModel.stopMundo();
    }

    public void setTempoEspera(int milisegundos) {
        ObjetoMundoImpl.tempoEspera = milisegundos;
    }

    public int getTempoEspera() {
        return ObjetoMundoImpl.tempoEspera;
    }

    public void pressionadaTecla(int keyCode) {
        mapaModel.mundo.pressionadaTecla(keyCode);
    }

    public JTextArea getConsole() {
        return console;
    }

    public void setConsole(JTextArea console) {
        this.console = console;
    }

    public void executar(final ObjetoDoMundo objetoDoMundo) {
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    objetoDoMundo.executar();
                } catch (Exception e) {
                }
            }
        };
        t.start();
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }
    
    

    

}
