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
 *
 * @author bart-kneepkens
 */
public class HeosController {
    private HeosSystem system;
    private int selectedPlayerIndex;
    
    public boolean connect(String ipAddress){
        system = new HeosSystem(ipAddress);
        return system.systemHeartBeat();
    }
    
    public List<Player> getPlayers(){
        return system.getPlayers();
    }
    
    public void play(){
        system.getPlayers().get(selectedPlayerIndex).setPlayState(PlayStates.PLAY);
    }
    
    public void pause(){
        system.getPlayers().get(selectedPlayerIndex).setPlayState(PlayStates.PAUSE);
    }
    
    public void stop(){
        system.getPlayers().get(selectedPlayerIndex).setPlayState(PlayStates.STOP);
    }
    
    public void changeVolume(int volume){
        system.getPlayers().get(selectedPlayerIndex).setVolume(volume);
    }
}
