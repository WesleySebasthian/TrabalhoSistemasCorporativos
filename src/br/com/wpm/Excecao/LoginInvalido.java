/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Excecao;

/**
 *
 * @author wesley
 */
public class LoginInvalido extends Exception {

    /**
     * Creates a new instance of <code>LoginInvalido</code> without detail
     * message.
     */
    public LoginInvalido() {
    }

    /**
     * Constructs an instance of <code>LoginInvalido</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LoginInvalido(String msg) {
        super(msg);
    }
}
