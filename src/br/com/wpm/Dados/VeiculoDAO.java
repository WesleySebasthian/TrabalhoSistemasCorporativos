/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Dados;

import br.com.wpm.Entidade.Cliente;
import br.com.wpm.Entidade.Usuario;
import br.com.wpm.Entidade.Veiculo;
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
public class VeiculoDAO {

    public void apagar(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BDUtil.Conectar();
        comando = conexao.prepareStatement("DELETE FROM VEICULO WHERE ID=?");
        comando.setInt(1, id);
        comando.execute();
    }

    public void editar(Veiculo veiculo) {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("UPDATE VEICULO SET MARCA=?, MODELO=?, ANO=?, DESCRICAO=?, PLACA=?, CHASSI=?, "
                    + "QUANTIDADE=?, PRECO=? WHERE ID=?");
            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3, veiculo.getAno());
            comando.setString(4, veiculo.getDescricao());
            comando.setString(5, veiculo.getPlaca());
            comando.setString(6, veiculo.getChassi());
            comando.setInt(7, veiculo.getQuantidade());
            comando.setDouble(8, veiculo.getPreco());
            comando.setInt(9, veiculo.getId());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Veiculo> listaVeiculo() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Veiculo> lista = new ArrayList<>();
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM VEICULO ORDER BY MARCA");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(resultado.getInt("ID"));
                veiculo.setMarca(resultado.getString("MARCA"));
                veiculo.setModelo(resultado.getString("MODELO"));
                veiculo.setAno(resultado.getInt("ANO"));
                veiculo.setDescricao(resultado.getString("DESCRICAO"));
                veiculo.setPlaca(resultado.getString("PLACA"));
                veiculo.setChassi(resultado.getString("CHASSI"));
                veiculo.setQuantidade(resultado.getInt("QUANTIDADE"));
                veiculo.setPreco(resultado.getDouble("PRECO"));
                lista.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void cadastrar(Veiculo veiculo) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("INSERT INTO VEICULO(MARCA,MODELO,ANO,DESCRICAO,PLACA,CHASSI,QUANTIDADE,PRECO) "
                    + "VALUES(?,?,?,?,?,?,?,?)");
            comando.setString(1, veiculo.getMarca());
            comando.setString(2, veiculo.getModelo());
            comando.setInt(3, veiculo.getAno());
            comando.setString(4, veiculo.getDescricao());
            comando.setString(5, veiculo.getPlaca());
            comando.setString(6, veiculo.getChassi());
            comando.setInt(7, veiculo.getQuantidade());
            comando.setDouble(8, veiculo.getPreco());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
