/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Dados;

import br.com.wpm.Entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class UsuarioDAO {

    public boolean login(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE USUARIO=? AND SENHA=?");
            comando.setString(1, usuario.getUsuario());
            comando.setString(2, usuario.getSenha());
            resultado = comando.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificar(String usuario) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM USUARIO WHERE USUARIO=?");
            comando.setString(1, usuario);
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

    public void cadastrar(Usuario usuario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("INSERT INTO USUARIO(USUARIO,SENHA) VALUES(?,?)");
            comando.setString(1, usuario.getUsuario());
            comando.setString(2, usuario.getSenha());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
