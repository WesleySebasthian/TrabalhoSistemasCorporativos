/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Negocio;

import br.com.wpm.Dados.UsuarioDAO;
import br.com.wpm.Entidade.Usuario;
import br.com.wpm.Excecao.LoginInvalido;
import br.com.wpm.Excecao.UsuarioDuplicado;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class UsuarioBO {

    public void login(Usuario usuario) throws LoginInvalido {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (!usuarioDAO.login(usuario)) {
            throw new LoginInvalido();
        }
    }

    public void cadastro(Usuario usuario) throws UsuarioDuplicado, SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.verificar(usuario.getUsuario())) {
            usuarioDAO.cadastrar(usuario);
        } else {
            throw new UsuarioDuplicado();
        }
    }
}
