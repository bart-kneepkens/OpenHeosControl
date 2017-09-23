/*
 * Copyright (C) 2017 bartkneepkens
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
package ViewModel;

import Connection.TelnetListener;
import Gui.Main;
import Gui.Observers.ObservablePropertyNames;
import PlayerCommands.Player;
import Ssdp.SsdpClient;
import SystemCommands.HeosSystem;
import SystemCommands.Song;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bartkneepkens
 */
public class MainViewModel implements Connection.IChangeListener {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
//    private int volume;
    
    private int songProgress;
    
    private Player[] players;
    private Player currentPlayer;
    
    private Song nowPlaying;
    private Song[] queue;
    
    public MainViewModel() {
        
    }
    
    public void postInit() {
       final SsdpClient cl = new SsdpClient();
        try {
            String ip = cl.getHeosIp();
            
            HeosSystem sys = new HeosSystem(ip);
            
            System.out.println(sys.systemHeartBeat());
            
            this.setPlayers(sys.getPlayers().toArray(new Player[sys.getPlayers().size()]));
            
            System.out.println(sys.getPlayers());
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MainViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(MainViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        TelnetListener.registerForChanges(this);
    }
    
    public void addObserverForProperty(String propertyName, PropertyChangeListener listener) {
        changes.addPropertyChangeListener(propertyName, listener);
    }
    
    // MAKE these setters private.
    // Public for now, for testing purposes.
    private void setVolume(int volume) {
        int oldVolume = this.currentPlayer.getVolume();
        this.currentPlayer.setVolume(volume);
        changes.firePropertyChange(ObservablePropertyNames.VOLUME, oldVolume, volume);
    }
    
    private void setPlayState(String state) {
        String oldState = this.currentPlayer.getPlayState();
        this.currentPlayer.setPlayState(state);
        changes.firePropertyChange(ObservablePropertyNames.PLAYSTATE, oldState, state);
    }
    
    private void setSongProgress(int progress) {
        int oldProgress = this.songProgress;
        this.songProgress = progress;
        changes.firePropertyChange(ObservablePropertyNames.SONGPROGRESS, oldProgress, progress);
    }
    
    private void setPlayers(Player[] players) {
        Player[] oldPlayers = this.players;
        this.players = players;
        changes.firePropertyChange(ObservablePropertyNames.PLAYERS, oldPlayers, players);
    }
    
    private void setQueue(Song[] queue) {
        Song[] oldQueue = this.queue;
        this.queue = queue;
        changes.firePropertyChange(ObservablePropertyNames.QUEUE, oldQueue, queue);
    }
    
    private void setNowPlaying(Song song) {
        Song oldSong = this.nowPlaying;
        this.nowPlaying = song;
        changes.firePropertyChange(ObservablePropertyNames.NOWPLAYING, oldSong, song);
    }
    
    public void volumeUpdated(int newValue) {
        this.setVolume(newValue);
    }
    
    public void changePlayer(int index) {
        this.currentPlayer = this.players[index];
    }

    @Override
    public void playerStateChanged(String pid, String state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playerVolumeChanged(String pid, int level) {
        this.setVolume(level);
    }

    @Override
    public void playerNowPlayingChanged(String pid, String nowPlaying) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playerNowPlayingProgress(String pid, int current, int duration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
