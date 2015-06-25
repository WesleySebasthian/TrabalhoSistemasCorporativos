/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wpm.Negocio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author wesley
 */
public class Criptografia {

    public static String criptografarSenha(String senha) {

        if (senha.isEmpty()) {
            return "";
        } else {
            String md5 = null;
            try {

                //Create MessageDigest object for MD5  
                MessageDigest digest = MessageDigest.getInstance("MD5");

                //Update input string in message digest  
                digest.update(senha.getBytes(), 0, senha.length());

                //Converts message digest value in base 16 (hex)   
                md5 = new BigInteger(1, digest.digest()).toString(16);

            } catch (NoSuchAlgorithmException e) {

                e.printStackTrace();
            }
            return md5;
        }
    }
}
