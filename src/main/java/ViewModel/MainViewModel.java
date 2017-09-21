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

import Gui.Observers.ObservablePropertyNames;
import PlayerCommands.Player;
import SystemCommands.Song;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author bartkneepkens
 */
public class MainViewModel {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private int volume;
    
    private String state;
    private int songProgress;
    private int songDuration;
    
    private String mediaImageUrl;
    private String artist;
    private String songTitle;
    private String albumTitle;
    
    private Player[] players;
    
    private Song[] queue;
    
    public MainViewModel() {
        
    }
    
    public void addObserverForProperty(String propertyName, PropertyChangeListener listener) {
        changes.addPropertyChangeListener(propertyName, listener);
    }
    
    // MAKE these setters private.
    // Public for now, for testing purposes.
    public void setVolume(int volume) {
        int oldVolume = this.volume;
        this.volume = volume;
        changes.firePropertyChange(ObservablePropertyNames.VOLUME, oldVolume, volume);
    }
    
    public void setPlayState(String state) {
        String oldState = this.state;
        this.state = state;
        changes.firePropertyChange(ObservablePropertyNames.PLAYSTATE, oldState, state);
    }
    
    public void setSongProgress(int progress) {
        int oldProgress = this.songProgress;
        this.songProgress = progress;
        changes.firePropertyChange(ObservablePropertyNames.SONGPROGRESS, oldProgress, progress);
    }
    
    public void setSongDuration(int duration) {
        int oldDuration = this.songDuration;
        this.songDuration = duration;
        changes.firePropertyChange(ObservablePropertyNames.SONGDURATION, oldDuration, duration);
    }
    
    public void setMediaImage(String url) {        
        String oldURl = this.mediaImageUrl;
        this.mediaImageUrl = url;
        changes.firePropertyChange(ObservablePropertyNames.SONGIMAGE, oldURl, url);
    }
    
    public void setSongTitle(String title) {
        String oldTitle = this.songTitle;
        this.songTitle = title;
        changes.firePropertyChange(ObservablePropertyNames.SONGTITLE, oldTitle, title);
    }
    
    public void setArtist(String artist) {
        String oldArtist = this.artist;
        this.artist = artist;
        changes.firePropertyChange(ObservablePropertyNames.ARTIST, oldArtist, artist);
    }
    
    public void setAlbumTitle(String title) {
        String oldTitle = this.albumTitle;
        this.albumTitle = title;
        changes.firePropertyChange(ObservablePropertyNames.ALBUMTITLE, oldTitle, title);
    }
    
    public void setPlayers(Player[] players) {
        Player[] oldPlayers = this.players;
        this.players = players;
        changes.firePropertyChange(ObservablePropertyNames.PLAYERS, oldPlayers, players);
    }
    
    public void setQueue(Song[] queue) {
        Song[] oldQueue = this.queue;
        this.queue = queue;
        changes.firePropertyChange(ObservablePropertyNames.QUEUE, oldQueue, queue);
    }

}
