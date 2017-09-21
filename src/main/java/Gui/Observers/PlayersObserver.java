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
package Gui.Observers;

import PlayerCommands.Player;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author bartkneepkens
 */
public class PlayersObserver implements PropertyChangeListener {
    
    private JComboBox playersComboBox;
    
    public PlayersObserver(JComboBox playersComboBox) {
        this.playersComboBox = playersComboBox;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Player[] newPlayers = (Player[]) evt.getNewValue();
        this.updateComboBoxWithItems(newPlayers, playersComboBox);
    }
    
    private void updateComboBoxWithItems(final Player[] players, JComboBox playersComboBox) {        
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        // Change this to a stream when the internet works!
        for (Player player : players) {
            model.addElement(player.getName());
        }
        
        playersComboBox.setModel(model);
    }
    
}
