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
package PlayerCommands;

import Connection.TelnetConnection;
import Connection.Response;
import Constants.Results;
import java.util.List;
import java.util.Map;

/**
 * Player class represents a physical HEOS player.
 * @author bart-kneepkens
 */
public class Player {
    private final String name;
    private final String pid;
    private final String model;
    private final String version;
    private Double gid;
    
    /**
     * Constructor to be called from the 'getPlayers' response. (See HeosSystem -> getPlayers)
     * @param map 
     */
    public Player(Map<String, Object> map){
        this.name = (String) map.get("name");
        // For some reason pid gets cast to a double by default, leaving a dot (.) in it.
        // For operations, it should just be a String
        this.pid = String.valueOf((Double) map.get("pid")).replace(".", "");
        this.model = (String) map.get("model");
        this.version = (String) map.get("version");
        
        // Gid is not always contained.
        if(map.containsKey("gid")){
            this.gid = (Double) map.get("gid");
        }
    }
    
    public String getName(){
        return name;
    }

    public String getPid() {
        return pid;
    }

    public String getModel() {
        return model;
    }

    public String getVersion() {
        return version;
    }

    public Double getGid() {
        return gid;
    }
    
    /**
     * Get this player's current state.
     * @return a String that is either "start", "stop", or "pause".
     */
    public String getPlayState(){
        Response response = TelnetConnection.write(PlayerCommands.GET_PLAY_STATE(this.pid));
        
        if(response.getResult().equals(Results.SUCCESS)){
            if(response.getMessage().contains("state=")){
                String state = response.getMessage().substring(response.getMessage().indexOf("state=") + 6);
                return state;
            }
        }
        return null;
    }
    
    /**
     * Will set this player's state to the parameter.
     * @param state - a String that is either "start", "stop", or "pause".
     * @return the newly set state, a String that is either "start", "stop", or "pause".
     */
    public String setPlayState(String state){
        Response response = TelnetConnection.write(PlayerCommands.SET_PLAY_STATE(this.pid, state));
        
        if(response.getResult().equals(Results.SUCCESS)){
            if(response.getMessage().contains("state=")){
                String currentState = response.getMessage().substring(response.getMessage().indexOf("state=") + 6);
                return currentState;
            }
        }
        return null;
    }
    
    /**
     * Gets this player's volume.
     * @return the volume (0-100). -1 if operation failed.
     */
    public int getVolume(){
        Response response = TelnetConnection.write(PlayerCommands.GET_VOLUME(this.pid));
        
        if(response.getResult().equals(Results.SUCCESS)){
            if(response.getMessage().contains("level=")){
                String currentVolumeString = response.getMessage().substring(response.getMessage().indexOf("level=") + 6);
                int currentVolume = Integer.parseInt(currentVolumeString);
                return currentVolume;
            }
        }
        return -1;
    }
    
    /**
     * Sets this player's volume.
     * @param newVolume (between 0-100).
     * @return The volume after setting it. -1 if operation failed.
     */
    public int setVolume(int newVolume){
        Response response = TelnetConnection.write(PlayerCommands.SET_VOLUME(this.pid, newVolume));
        
        if(response.getResult().equals(Results.SUCCESS)){
            if(response.getMessage().contains("level=")){
                String currentVolumeString = response.getMessage().substring(response.getMessage().indexOf("level=") + 6);
                int currentVolume = Integer.parseInt(currentVolumeString);
                return currentVolume;
            }
        }
        return -1;
    }
    
    /**
     * Ticks the volume up by a certain step.
     * @param step (between 1-10)
     * @return boolean indicating a successful operation.
     */
    public boolean volumeUp(int step){
        Response response = TelnetConnection.write(PlayerCommands.VOLUME_UP(this.pid, step));
        
        return response.getResult().equals(Results.SUCCESS);            
    }
    
    /**
     * Ticks the volume down by a certain step.
     * @param step (between 1-10)
     * @return boolean indicating a successful operation.
     */
    public boolean volumeDown(int step){
        Response response = TelnetConnection.write(PlayerCommands.VOLUME_DOWN(this.pid, step));
        
        return response.getResult().equals(Results.SUCCESS);    
    }
    
    public String getNowPlayingMedia(){
        Response response = TelnetConnection.write(PlayerCommands.GET_NOW_PLAYING_MEDIA(this.pid));
        
        if(response.getResult().equals(Results.SUCCESS)){
            Map<String, Object> map = (Map<String, Object>) response.getPayload();
            
            return "'" + map.get("song") + "' by "+ map.get("artist");
        }
        
        return null;
    }
}
