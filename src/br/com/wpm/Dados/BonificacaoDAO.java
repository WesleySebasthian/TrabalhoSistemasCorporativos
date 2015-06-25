/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Dados;

import br.com.wpm.Entidade.Bonificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class BonificacaoDAO {

    public double verificar() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM BONIFICACAO ORDER BY ID DESC");
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return resultado.getDouble("VALOR");
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void cadastrar(Bonificacao bonificacao) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("INSERT INTO BONIFICACAO(VALOR) VALUES(?)");
            comando.setDouble(1, bonificacao.getValor());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
