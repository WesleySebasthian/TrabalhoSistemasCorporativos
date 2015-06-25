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
public class Veiculo {

    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String descricao;
    private String placa;
    private String chassi;
    private int quantidade;
    private double preco;

    public Veiculo() {
    }

    public Veiculo(String marca, String modelo, int ano, String descricao, String placa, String chassi, int quantidade, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.descricao = descricao;
        this.placa = placa;
        this.chassi = chassi;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Marca: " + this.marca + " Modelo:" + this.modelo + " Preço:" + this.preco;
    }

}
