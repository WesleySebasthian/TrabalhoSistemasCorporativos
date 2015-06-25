/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Dados;

import br.com.wpm.Entidade.Funcionario;
import br.com.wpm.Entidade.FuncionarioPorVenda;
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
public class FuncionarioDAO {

    public void apagar(int id) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        conexao = BDUtil.Conectar();
        comando = conexao.prepareStatement("DELETE FROM FUNCIONARIO WHERE ID=?");
        comando.setInt(1, id);
        comando.execute();
    }

    public void editar(Funcionario funcionario) {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("UPDATE FUNCIONARIO SET NOME=?, CPF=?, ENDERECO=?, SALARIO=? WHERE ID=?");
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getCpf());
            comando.setString(3, funcionario.getEndereco());
            comando.setDouble(4, funcionario.getSalario());
            comando.setInt(5, funcionario.getId());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FuncionarioPorVenda> listaFuncionarioPorVenda() {
        Connection conexao = null;
        PreparedStatement comando = null, comando2 = null;
        ResultSet resultado = null, resultado2 = null;
        List<FuncionarioPorVenda> lista = new ArrayList<>();
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT FUNCIONARIO, COUNT(*) FROM VENDA GROUP BY FUNCIONARIO ORDER BY COUNT(*) DESC");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                FuncionarioPorVenda funcionarioPorVenda = new FuncionarioPorVenda();
                for (Funcionario funcionario : listaFuncionario()) {
                    if (funcionario.getId() == resultado.getInt("FUNCIONARIO")) {
                        funcionarioPorVenda.setFuncionario(funcionario);
                    }
                }
                double soma = 0.0, salario;
                comando2 = conexao.prepareStatement("SELECT * FROM VENDA WHERE FUNCIONARIO=?");
                comando2.setInt(1, resultado.getInt("FUNCIONARIO"));
                resultado2 = comando2.executeQuery();
                while (resultado2.next()) {
                    soma += resultado2.getDouble("VALOR");
                }
                Funcionario funcionario = dadosFuncionario(resultado.getInt("FUNCIONARIO"));
                Double bonificacao = new BonificacaoDAO().verificar();
                if (bonificacao == 0) {
                    salario = funcionario.getSalario();
                } else {
                    salario = funcionario.getSalario() + (soma * (0+(bonificacao / 100)));
                }
                funcionarioPorVenda.setSalario(salario);
                funcionarioPorVenda.setQtd(resultado.getInt("COUNT(*)"));
                lista.add(funcionarioPorVenda);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return lista;
    }

    public static Funcionario dadosFuncionario(int id) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM FUNCIONARIO WHERE ID=?");
            comando.setInt(1, id);
            resultado = comando.executeQuery();
            Funcionario funcionario = new Funcionario();
            if (resultado.next()) {
                funcionario.setId(resultado.getInt("ID"));
                funcionario.setNome(resultado.getString("NOME"));
                funcionario.setSalario(resultado.getDouble("SALARIO"));
                funcionario.setEndereco(resultado.getString("ENDERECO"));
                funcionario.setCpf(resultado.getString("CPF"));
            }
            return funcionario;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Funcionario> listaFuncionario() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Funcionario> lista = new ArrayList<>();
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM FUNCIONARIO ORDER BY NOME");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultado.getInt("ID"));
                funcionario.setNome(resultado.getString("NOME"));
                funcionario.setSalario(resultado.getDouble("SALARIO"));
                funcionario.setEndereco(resultado.getString("ENDERECO"));
                funcionario.setCpf(resultado.getString("CPF"));
                lista.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            comando = conexao.prepareStatement("SELECT * FROM FUNCIONARIO WHERE " + campo + "=? AND ID!=?");
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
            comando = conexao.prepareStatement("SELECT * FROM FUNCIONARIO WHERE " + campo + "=?");
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

    public void cadastrar(Funcionario funcionario) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("INSERT INTO FUNCIONARIO(NOME,CPF,ENDERECO,SALARIO) VALUES(?,?,?,?)");
            comando.setString(1, funcionario.getNome());
            comando.setString(2, funcionario.getCpf());
            comando.setString(3, funcionario.getEndereco());
            comando.setDouble(4, funcionario.getSalario());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
