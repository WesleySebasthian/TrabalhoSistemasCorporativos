/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Dados;

import br.com.wpm.Entidade.Cliente;
import br.com.wpm.Entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wesley
 */
public class ClienteDAO {

    public void apagar(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BDUtil.Conectar();
        comando = conexao.prepareStatement("DELETE FROM CLIENTE WHERE ID=?");
        comando.setInt(1, id);
        comando.execute();
    }

    public void editar(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("UPDATE CLIENTE SET NOME=?, CPF=?, ENDERECO=?, EMAIL=?, TELEFONE=? WHERE ID=?");
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getCpf());
            comando.setString(3, cliente.getEndereco());
            comando.setString(4, cliente.getEmail());
            comando.setString(5, cliente.getTelefone());
            comando.setInt(6, cliente.getId());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listaCliente() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Cliente> lista = new ArrayList<>();
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM CLIENTE ORDER BY NOME");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("ID"));
                cliente.setNome(resultado.getString("NOME"));
                cliente.setCpf(resultado.getString("CPF"));
                cliente.setEndereco(resultado.getString("ENDERECO"));
                cliente.setEmail(resultado.getString("EMAIL"));
                cliente.setTelefone(resultado.getString("TELEFONE"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public boolean verificarDadosEditar(String campo, String dados, int id) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM CLIENTE WHERE " + campo + "=? AND ID!=?");
            comando.setString(1, dados);
            comando.setInt(2, id);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarDados(String campo, String dados) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM CLIENTE WHERE " + campo + "=?");
            comando.setString(1, dados);
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cadastrar(Cliente cliente) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("INSERT INTO CLIENTE(NOME,CPF,ENDERECO,EMAIL,TELEFONE) VALUES(?,?,?,?,?)");
            comando.setString(1, cliente.getNome());
            comando.setString(2, cliente.getCpf());
            comando.setString(3, cliente.getEndereco());
            comando.setString(4, cliente.getEmail());
            comando.setString(5, cliente.getTelefone());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
