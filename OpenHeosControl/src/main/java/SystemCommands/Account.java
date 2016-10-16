/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemCommands;

/**
 *
 * @author BartKneepkens
 */
public class Account {
    private String username;
    private String password;
    
    public Account(String username){
        this.username = username;
    }
    
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
}
