/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class BDUtil {

    public BDUtil() {
    }

    public static Connection Conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bd", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return con;
    }

}
