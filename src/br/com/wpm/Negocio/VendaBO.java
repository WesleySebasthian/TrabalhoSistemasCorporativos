/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Negocio;

import br.com.wpm.Dados.VendaDAO;
import br.com.wpm.Entidade.Venda;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class VendaBO {

    public void cadastrar(Venda venda) throws SQLException {
        new VendaDAO().cadastrar(venda);
    }

    public boolean verificarDesconto(int cliente) {
        return new VendaDAO().verificarDesconto(cliente);
    }
}
