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
import java.beans.PropertyChangeSupport;

/**
 *
 * @author bartkneepkens
 */
public class MainViewModel {
    public PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private int volume;
    private String state;
    private int songProgress;
    
    public MainViewModel() {
        
    }
    
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

}
