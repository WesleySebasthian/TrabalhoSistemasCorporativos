/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Dados;

import br.com.wpm.Entidade.Cliente;
import br.com.wpm.Entidade.Funcionario;
import br.com.wpm.Entidade.Veiculo;
import br.com.wpm.Entidade.Venda;
import br.com.wpm.Entidade.VendaPorMarca;
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
public class VendaDAO {
    
    public List<VendaPorMarca> listaVendaPorMarca() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<VendaPorMarca> lista = new ArrayList<>();
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT VE.MARCA, COUNT(V.ID) FROM VENDA V INNER JOIN VEICULO VE ON "
                    + "(V.VEICULO = VE.ID) GROUP BY VE.MARCA ORDER BY COUNT(V.ID) DESC");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                VendaPorMarca vendaPorMarca = new VendaPorMarca();
                vendaPorMarca.setMarca(resultado.getString("VE.MARCA"));
                vendaPorMarca.setQtd(resultado.getInt("COUNT(V.ID)"));
                lista.add(vendaPorMarca);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    public List<Venda> listaVenda() {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        List<Venda> lista = new ArrayList<>();
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM VENDA ORDER BY DATA");
            resultado = comando.executeQuery();
            while (resultado.next()) {
                Venda venda = new Venda();
                venda.setId(resultado.getInt("ID"));
                for (Funcionario funcionario : new FuncionarioDAO().listaFuncionario()) {
                    if (funcionario.getId() == resultado.getInt("FUNCIONARIO")) {
                        venda.setFuncionario(funcionario);
                        break;
                    }
                }
                for (Cliente cliente : new ClienteDAO().listaCliente()) {
                    if (cliente.getId() == resultado.getInt("CLIENTE")) {
                        venda.setCliente(cliente);
                        break;
                    }
                }
                for (Veiculo veiculo : new VeiculoDAO().listaVeiculo()) {
                    if (veiculo.getId() == resultado.getInt("VEICULO")) {
                        venda.setVeiculo(veiculo);
                        break;
                    }
                }
                venda.setData(resultado.getDate("DATA"));
                venda.setValor(resultado.getDouble("VALOR"));
                lista.add(venda);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    public boolean verificarDesconto(int cliente) {
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("SELECT * FROM VENDA WHERE CLIENTE=?");
            comando.setInt(1, cliente);
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
    
    public void cadastrar(Venda venda) throws SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        try {
            conexao = BDUtil.Conectar();
            comando = conexao.prepareStatement("INSERT INTO VENDA(FUNCIONARIO,CLIENTE,VEICULO,DATA,VALOR) VALUES(?,?,?,?,?)");
            comando.setInt(1, venda.getFuncionario().getId());
            comando.setInt(2, venda.getCliente().getId());
            comando.setInt(3, venda.getVeiculo().getId());
            comando.setDate(4, venda.getData());
            comando.setDouble(5, venda.getValor());
            comando.execute();
            int qtd = venda.getVeiculo().getQuantidade() - 1;
            comando = conexao.prepareStatement("UPDATE VEICULO SET QUANTIDADE=? WHERE ID=?");
            comando.setInt(1, qtd);
            comando.setInt(2, venda.getVeiculo().getId());
            comando.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
