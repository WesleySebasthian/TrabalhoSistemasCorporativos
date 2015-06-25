/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Negocio;

import br.com.wpm.Dados.ClienteDAO;
import br.com.wpm.Dados.FuncionarioDAO;
import br.com.wpm.Entidade.Funcionario;
import br.com.wpm.Excecao.CpfDuplicado;
import br.com.wpm.Excecao.NomeDuplicado;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class FuncionarioBO {

    public void apagar(int id) throws SQLException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.apagar(id);
    }
    
    public void editar(Funcionario funcionario) throws CpfDuplicado, NomeDuplicado {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        if (funcionarioDAO.verificarDadosEditar("NOME", funcionario.getNome(), funcionario.getId())) {
            if (funcionarioDAO.verificarDadosEditar("CPF", funcionario.getCpf(), funcionario.getId())) {
                funcionarioDAO.editar(funcionario);
            } else {
                throw new CpfDuplicado();
            }
        } else {
            throw new NomeDuplicado();
        }
    }

    public void cadastro(Funcionario funcionario) throws NomeDuplicado, CpfDuplicado, SQLException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        if (funcionarioDAO.verificarDados("NOME", funcionario.getNome())) {
            if (funcionarioDAO.verificarDados("CPF", funcionario.getCpf())) {
                funcionarioDAO.cadastrar(funcionario);
            } else {
                throw new CpfDuplicado();
            }
        } else {
            throw new NomeDuplicado();
        }
    }
}
