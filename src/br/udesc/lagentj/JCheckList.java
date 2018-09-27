/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JCheckList extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel jl;
    private JList<CheckBoxItem> list;

    private DefaultListModel<CheckBoxItem> listModel;

    public JCheckList() {

        setLayout(new BorderLayout());

        jl = new JLabel("Title");
        add(jl, BorderLayout.PAGE_START);
        
        listModel = new DefaultListModel<CheckBoxItem>();
        list = new JList<CheckBoxItem>(listModel);
        list.setCellRenderer(new CheckListCellRenderer());
        
        JScrollPane scroll = new JScrollPane(list);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(210, 100));
        
        add(scroll, BorderLayout.CENTER);
    }

    public void setTitle(String title) {
        jl.setText(title);
    }

    public void addItem(String item) {
        listModel.addElement(new CheckBoxItem(item));
    }

    public void checkItem(int index) {
        listModel.getElementAt(index).checked = true;
        repaint();
    }

    public void uncheckItem(int index) {
        listModel.getElementAt(index).checked = false;
        repaint();
    }

    public void uncheckAllItems() {
        for (int i = 0; i < listModel.size(); i++) {
            uncheckItem(i);
        }

    }

    class CheckBoxItem {

        boolean checked;
        String title;

        CheckBoxItem(String title) {
            this.title = title;
        }
    }

    class CheckListCellRenderer extends DefaultListCellRenderer {

        private static final long serialVersionUID = 1L;

        String checked = new String(Character.toChars(9745));
        String unchecked = new String(Character.toChars(9744));

        @Override
        public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            if (c instanceof JLabel) {
                JLabel l = (JLabel) c;
                CheckBoxItem cbi = (CheckBoxItem) value;
                String s = (cbi.checked ? checked : unchecked) + " " + cbi.title;
                String text = String.format("<html><div style=\"width:%dpx; padding:2dpx\">%s</div><html>", 145, s);
                l.setText(text);
            }

            return c;
        }
    }

    public boolean isAllGoalsAchieved() {
        for (int i = 0; i < listModel.size(); i++) {
            if (!listModel.getElementAt(i).checked) {
                return false;
            }
        }

        return true;
    }

}
