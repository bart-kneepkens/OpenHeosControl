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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author bartkneepkens
 */
public class MediaObserver implements PropertyChangeListener {

    private JLabel imageLabel;
    private JLabel songNameLabel;
    private JLabel artistNameLabel;
    private JLabel albumNameLabel;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String changedPropertyName = evt.getPropertyName();

        switch (changedPropertyName) {
            case ObservablePropertyNames.SONGIMAGE:
                this.aSyncLoadImageFromURLIntoLabel((String) evt.getNewValue(), imageLabel);
                break;
            case ObservablePropertyNames.SONGTITLE:
                this.songNameLabel.setText((String) evt.getNewValue());
                break;
            case ObservablePropertyNames.ARTIST:
                this.artistNameLabel.setText((String) evt.getNewValue());
                break;
            case ObservablePropertyNames.ALBUMTITLE:
                this.albumNameLabel.setText((String) evt.getNewValue());
                break;
            default: break;
        }
    }
    
    private ImageIcon aSyncLoadImageFromURLIntoLabel(String url, JLabel label) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }).start();
        return null;
    }
}
