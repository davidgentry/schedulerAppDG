/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author David Gentry
 */
public class User {
    private String user;
    
    public User() {}
    
    //setter
    public void setUser(String username) {
        this.user = username;
    }   
    //getter
    public String getUser() {
        return user;
    }
}
