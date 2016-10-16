/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartkneepkens.openheoscontrol;

import SystemCommands.HeosSystem;

/**
 *
 * @author BartKneepkens
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("STARTING");
        HeosSystem sys = new HeosSystem("192.168.2.3");
        sys.connect();
        
        sys.accountCheck();
    }
    
}
