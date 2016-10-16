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
package SystemCommands;

import PlayerCommands.Player;
import com.bartkneepkens.openheoscontrol.Response;
import com.bartkneepkens.openheoscontrol.constants.Results;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bart-kneepkens
 */
public class HeosSystem {
    
    private String ipAddress;
    
    /* Networking */
    private static Socket socket;
    private static PrintWriter out;
    private static Scanner in;
    
    private static Gson gson;
    
    public HeosSystem(String ipAddress){
        if(ipAddress == null){
            // Also check with regex for the ip address signature.
            // Exception, BOOM.
        }
        
        this.ipAddress = ipAddress;
    }
    
    public void connect(){
        // Set up the socket, in and out
        try {
            socket = new Socket(this.ipAddress, 1255);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            in.useDelimiter("\r\n");
            gson = new GsonBuilder().create();
            
        } catch (IOException ex) {
            Logger.getLogger(HeosSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * Check whether or not the connection is alright, as well as the system, with heartbeat.
         */
    
        if(!this.systemHeartBeat()){
           // Deal with it. 
        }
    }
    
    /* System Commands (Protocol -> 4.1) */
   
    public Account accountCheck(){
        if(socket == null || out == null || in == null){
            // break
        }
        
        out.println(SystemCommands.ACCOUNT_CHECK);
        
        Response response = gson.fromJson(in.next(), Response.class);
        
        if(response.getResult().equals(Results.SUCCESS)){
            if(response.getMessage().contains("signed_in")){
                String username = response.getMessage().substring(response.getMessage().indexOf("un=") + 3);
                return new Account(username);
            }
        }
        
        return null;
    }
    
    public Account accountSignIn(String username, String password){
        out.println(SystemCommands.ACCOUNT_SIGN_IN(username, password));
        
        Response response = gson.fromJson(in.next(), Response.class);
        
        if(response.getResult().equals(Results.SUCCESS) && !response.getMessage().contains("command under process")){
            return new Account(username, password);
        }
        
        return null;
    }
    
    public boolean accountSignOut(){
        out.println(SystemCommands.ACCOUNT_SIGN_OUT);
        Response response = gson.fromJson(in.next(), Response.class);
        
        return response.getResult().equals(Results.SUCCESS) && response.getMessage().equals("signed_out");
    }
    
    public boolean systemHeartBeat(){
        out.println(SystemCommands.HEARTBEAT);
        
        Response response = gson.fromJson(in.next(), Response.class);
        
        return response.getResult().equals(Results.SUCCESS);
    }
    
    public List<Player> getPlayers(){
        
        out.println(SystemCommands.GET_PLAYERS);
        
        Response response = gson.fromJson(in.next(), Response.class);
        
        if(response.getResult().equals(Results.SUCCESS)){
            List<Player> toBeReturned = new ArrayList<>();
            
            for (Map<String, Object> map : (List<Map<String, Object>>) response.getPayload() ) {
                toBeReturned.add(new Player(map));
            }
            
            return toBeReturned;
        }
        
        return null;
    }
    
    public static Response communicate(String command) {
        // Check for null socket etc
        
        out.println(command);
        return gson.fromJson(in.next(), Response.class);
    }
}