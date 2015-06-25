/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Negocio;

import br.com.wpm.Dados.VeiculoDAO;
import br.com.wpm.Entidade.Veiculo;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class VeiculoBO {

    public void apagar(int id) throws SQLException{
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.apagar(id);
    }
    public void editar(Veiculo veiculo) {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.editar(veiculo);
    }

    public void cadastrar(Veiculo veiculo) throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.cadastrar(veiculo);
    }
}
