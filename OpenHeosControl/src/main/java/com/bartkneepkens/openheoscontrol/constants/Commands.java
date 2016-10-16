/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartkneepkens.openheoscontrol.constants;

/**
 *
 * @author BartKneepkens
 */
public class Commands {
    public static String HEARTBEAT = "heos://system/heart_beat";
    public static String GET_PLAYERS = "heos://player/get_players";
    public static String CHECK_ACCOUNT = "heos://system/check_account";
    public static String ACCOUNT_SIGN_IN(String username, String password){
        return "heos://system/sign_in?un=" + username + "&pw=" + password;
    } 
}
