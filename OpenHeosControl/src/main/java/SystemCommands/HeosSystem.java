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

import Connection.TelnetConnection;
import PlayerCommands.Player;
import Connection.Response;
import Connection.TelnetListener;
import Constants.Results;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class that represents a HEOS system, on a network.
 * Each physical HEOS system (player) has a HeosSystem.
 * However, all the players on a network can be contacted through a single HeosSystem.
 * @author bart-kneepkens
 */
public class HeosSystem {
    
    public HeosSystem(String ipAddress){
        if(ipAddress == null){
            // Also check with regex for the ip address signature.
            // Exception, BOOM.
        }
              
        TelnetConnection.connect(ipAddress);
        TelnetListener.connect(ipAddress);
    }
   
    /**
     * Checks whether or not the user is signed in to its HEOS system.
     * @return The signed in account; if not signed in, null.
     */
    public Account accountCheck(){
        Response response = TelnetConnection.write(SystemCommands.ACCOUNT_CHECK);
        
        if(response.getResult().equals(Results.SUCCESS)){
            if(response.getMessage().contains("signed_in")){
                String username = response.getMessage().substring(response.getMessage().indexOf("un=") + 3);
                return new Account(username);
            }
        }
        return null;
    }
    
    /**
     * Sign in to the HEOS system with specified HEOS account.
     * @param username
     * @param password
     * @return The signed in account; if not signed in, null.
     */
    public Account accountSignIn(String username, String password){
        Response response = TelnetConnection.write(SystemCommands.ACCOUNT_SIGN_IN(username, password));
        
        if(response.getResult().equals(Results.SUCCESS) && !response.getMessage().contains("command under process")){
            return new Account(username, password);
        }
        
        return null;
    }
    
    /**
     * Sign out of the HEOS system.
     * @return boolean indicating a successful operation.
     */
    public boolean accountSignOut(){
        Response response = TelnetConnection.write(SystemCommands.ACCOUNT_SIGN_OUT);
        return response.getResult().equals(Results.SUCCESS) && response.getMessage().equals("signed_out");
    }
    
    /**
     * Gets the HEOS System state
     * @return boolean indicating if the system is alive.
     */
    public boolean systemHeartBeat(){
        Response response = TelnetConnection.write(SystemCommands.HEARTBEAT);
        return response.getResult().equals(Results.SUCCESS);
    }
    
    /**
     * Gets the HEOS players that are connected to this system.
     * @return Players. If none, null.
     */
    public List<Player> getPlayers(){
        Response response = TelnetConnection.write(SystemCommands.GET_PLAYERS);
        
        if(response.getResult().equals(Results.SUCCESS)){
            List<Player> toBeReturned = new ArrayList<>();
            for (Map<String, Object> map : (List<Map<String, Object>>) response.getPayload() ) {
                toBeReturned.add(new Player(map));
            }
            return toBeReturned;
        }
        return null;
    }
    
}