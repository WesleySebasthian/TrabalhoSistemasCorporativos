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
public class UsuarioDuplicado extends Exception {

    /**
     * Creates a new instance of <code>UsuarioDuplicado</code> without detail
     * message.
     */
    public UsuarioDuplicado() {
    }

    /**
     * Constructs an instance of <code>UsuarioDuplicado</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioDuplicado(String msg) {
        super(msg);
    }
}
