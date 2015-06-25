/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Entidade;

import java.sql.Date;

/**
 *
 * @author wesley
 */
public class Venda {

    private int id;
    private Funcionario funcionario;
    private Cliente cliente;
    private Veiculo veiculo;
    private Date data;
    private double valor;

    public Venda() {
    }

    public Venda(Funcionario funcionario, Cliente cliente, Veiculo veiculo) {
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", funcionario=" + funcionario + ", cliente=" + cliente + ", veiculo=" + veiculo + ", data=" + data + ", valor=" + valor + '}';
    }

}
