/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Negocio;

import br.com.wpm.Dados.BonificacaoDAO;
import br.com.wpm.Entidade.Bonificacao;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class BonificacaoBO {

    public void cadastrar(Bonificacao bonificacao) throws SQLException{
        BonificacaoDAO bonificacaoDAO = new BonificacaoDAO();
        bonificacaoDAO.cadastrar(bonificacao);
    }
}
