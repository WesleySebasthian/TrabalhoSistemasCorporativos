/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Entidade;

/**
 *
 * @author wesley
 */
public class Bonificacao {

    private int id;
    private double valor;

    public Bonificacao() {
    }

    public Bonificacao(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Bonificacao{" + "id=" + id + ", valor=" + valor + '}';
    }

}
