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
package Gui;

import Constants.PlayStates;
import PlayerCommands.Player;
import SystemCommands.HeosSystem;
import java.util.List;

/**
 *  Represents a layer between GUI and actual HeosSystem, which can have multiple players.
 * @author bart-kneepkens
 */
public class HeosController {
    private HeosSystem system;
    private int selectedPlayerIndex;
    private List<Player> players;
    
    public boolean connect(String ipAddress){
        system = new HeosSystem(ipAddress);
        players = system.getPlayers();
        return system.systemHeartBeat();
    }
    
    public String currentPlayerPid(){
        return this.players.get(selectedPlayerIndex).getPid();
    }
    
    public boolean isCurrentPlayer(String pid){
        return pid.substring(0, 9).equals(this.players.get(selectedPlayerIndex).getPid().substring(0, 9));
    }
    
    public List<Player> getPlayers(){
        return system.getPlayers();
    }
    
    public void changePlayerIndex(int playerIndex){
        selectedPlayerIndex = playerIndex;
    }
    
    public void play(){
        players.get(selectedPlayerIndex).setPlayState(PlayStates.PLAY);
    }
    
    public void pause(){
        players.get(selectedPlayerIndex).setPlayState(PlayStates.PAUSE);
    }
    
    public void stop(){
        players.get(selectedPlayerIndex).setPlayState(PlayStates.STOP);
    }
    
    public void changeVolume(int volume){
        players.get(selectedPlayerIndex).setVolume(volume);
    }
    
    public int getVolume(){
        return players.get(selectedPlayerIndex).getVolume();
    }
    
    public String getState(){
        return players.get(selectedPlayerIndex).getPlayState();
    }
    
    public String getNowPlayingMedia(){
        return players.get(selectedPlayerIndex).getNowPlayingMedia();
    }
}
