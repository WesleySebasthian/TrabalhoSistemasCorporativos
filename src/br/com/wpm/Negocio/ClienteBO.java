/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Negocio;

import br.com.wpm.Dados.ClienteDAO;
import br.com.wpm.Dados.UsuarioDAO;
import br.com.wpm.Entidade.Cliente;
import br.com.wpm.Excecao.CpfDuplicado;
import br.com.wpm.Excecao.EmailDuplicado;
import br.com.wpm.Excecao.NomeDuplicado;
import br.com.wpm.Excecao.TelefoneDuplicado;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class ClienteBO {

    public void apagar(int id) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.apagar(id);
    }

    public void editar(Cliente cliente) throws NomeDuplicado, CpfDuplicado, EmailDuplicado, TelefoneDuplicado, SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        if (clienteDAO.verificarDadosEditar("NOME", cliente.getNome(), cliente.getId())) {
            if (clienteDAO.verificarDadosEditar("CPF", cliente.getCpf(), cliente.getId())) {
                if (clienteDAO.verificarDadosEditar("EMAIL", cliente.getEmail(), cliente.getId())) {
                    if (clienteDAO.verificarDadosEditar("TELEFONE", cliente.getTelefone(), cliente.getId())) {
                        clienteDAO.editar(cliente);
                    } else {
                        throw new TelefoneDuplicado();
                    }
                } else {
                    throw new EmailDuplicado();
                }
            } else {
                throw new CpfDuplicado();
            }
        } else {
            throw new NomeDuplicado();
        }
    }

    public void cadastrar(Cliente cliente) throws NomeDuplicado, CpfDuplicado, EmailDuplicado, TelefoneDuplicado, SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        if (clienteDAO.verificarDados("NOME", cliente.getNome())) {
            if (clienteDAO.verificarDados("CPF", cliente.getCpf())) {
                if (clienteDAO.verificarDados("EMAIL", cliente.getEmail())) {
                    if (clienteDAO.verificarDados("TELEFONE", cliente.getTelefone())) {
                        clienteDAO.cadastrar(cliente);
                    } else {
                        throw new TelefoneDuplicado();
                    }
                } else {
                    throw new EmailDuplicado();
                }
            } else {
                throw new CpfDuplicado();
            }
        } else {
            throw new NomeDuplicado();
        }
    }
}
