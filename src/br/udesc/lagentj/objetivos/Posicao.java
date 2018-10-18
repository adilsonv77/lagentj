/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.lagentj.objetivos;

/**
 *
 * @author gabriel
 */
public class Posicao {

    private int x;
    private int y;
    private int visits;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Posicao() {
        visits = 1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public void visit() {
        this.visits++;
    }

}
