/*
 * Copyright (C) 2016 bart-kneepkens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bartkneepkens.openheoscontrol;

import PlayerCommands.Player;
import SystemCommands.HeosSystem;
import com.bartkneepkens.openheoscontrol.constants.PlayStates;
import java.util.Scanner;

/**
 *
 * @author bart-kneepkens
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("STARTING");
        HeosSystem sys = new HeosSystem("192.168.2.3");
        
        sys.accountCheck();
        
        Player p = sys.getPlayers().get(0);
        
        while(true){
            String input = in.next();
            
            if(input.equals("end")){
                break;
            }
            
            if(input.equals("up")){
                p.volumeUp(5);
            }
            
            if(input.equals("down")){
                p.volumeDown(5);
            }
            
            if(input.equals("play")){
                p.setPlayState("play");
            }
            
            if(input.equals("pause")){
                p.setPlayState("pause");
            }
        }
    }
    
}
