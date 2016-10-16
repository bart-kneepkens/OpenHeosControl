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
import SystemCommands.HeosSystem;
import com.bartkneepkens.openheoscontrol.Response;
import com.bartkneepkens.openheoscontrol.constants.Results;
import java.util.Map;

/**
 *
 * @author bart-kneepkens
 */
public class Player {
    private String name;
    private String pid;
    private String model;
    private String version;
    private Double gid;
    
    public Player(Map<String, Object> map){
        this.name = (String) map.get("name");
        
        // For some reason pid gets cast to a double by default, leaving a dot (.) in it.
        // For operations, it should just be a String
        this.pid = String.valueOf((Double) map.get("pid")).replace(".", "");
        this.model = (String) map.get("model");
        this.version = (String) map.get("version");
        
        if(map.containsKey("gid")){
            this.gid = (Double) map.get("gid");
        }
    }
    
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
}
