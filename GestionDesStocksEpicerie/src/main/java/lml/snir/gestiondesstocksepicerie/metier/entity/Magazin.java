/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.gestiondesstocksepicerie.metier.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author joris
 */
public class Magazin {
    private long id;
    private String nom;
    private String mdp;
    private String login;
    
    public Magazin() {
        
    }
    
    public Magazin (String nom, String login ,String mdp, boolean encode) {
        this.login = login;
        if (encode) {
            this.mdp = this.codeMD5(mdp);
        } else {
            this.mdp = mdp;
        }
    }

    public Magazin(String nom, String mdp, String login) {
        this.nom = nom;
        this.mdp = mdp;
        this.login = login;
    }
    
    private String codeMD5(String msg) {
        String code = "";
        byte[] b = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            b = md.digest(msg.getBytes());
            for (int i = 0; i < b.length; i++) {
                int x = b[i];
                if (x < 0) {
                    x += 256;
                }

                code += Integer.toHexString(x);
            }
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return code;
    }
    
    public boolean isValid(String mdp) {
        return this.mdp.equals(this.codeMD5(mdp));
    }
   
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the password
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the password to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }
    
    public void setEncodedMdp(String password) {
        this.mdp = password;
    }
}
