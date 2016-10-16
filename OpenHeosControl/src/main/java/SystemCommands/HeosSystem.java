/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package SystemCommands;

import com.bartkneepkens.openheoscontrol.Response;
import com.bartkneepkens.openheoscontrol.constants.Commands;
import com.bartkneepkens.openheoscontrol.constants.Results;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.*;

/**
 *
 * @author BartKneepkens
 */
public class HeosSystem {
    
    private String ipAddress;
    
    /* Networking */
    private Socket socket;
    private PrintWriter out;
    private Scanner in;
    
    private Gson gson;
    
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
    
        out.println(Commands.HEARTBEAT);
        
        Response response = gson.fromJson(in.next(), Response.class);
        
        if(response.getResult().equals(Results.FAIL)){
            // Do something
        }
    }
    
    public Account accountCheck(){
        if(socket == null || out == null || in == null){
            // break
        }
        
        out.println(Commands.CHECK_ACCOUNT);
        
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
        out.println(Commands.ACCOUNT_SIGN_IN(username, password));
        
        Response response = gson.fromJson(in.next(), Response.class);
        
        if(response.getResult().equals(Results.SUCCESS) && !response.getMessage().contains("command under process")){
            return new Account(username, password);
        }
        
        return null;
    }
}