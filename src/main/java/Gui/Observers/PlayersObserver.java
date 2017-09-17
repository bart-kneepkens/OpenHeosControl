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
import SystemCommands.HeosSystem;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author bartkneepkens
 */
public class PlayersObserver implements PropertyChangeListener {
    
    private JList playersList;
    
    public PlayersObserver(JList playersList) {
        this.playersList = playersList;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Player[] newPlayers = (Player[]) evt.getNewValue();
        this.updateListWithItems(newPlayers, playersList);
    }
    
    private void updateListWithItems(final Player[] players, JList playersList) {
        playersList.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return players.length;
            }
            @Override
            public Object getElementAt(int index) {
                return players[index].getName();
            }
        });
    }
    
}
