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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author bartkneepkens
 */
public class MediaObserver implements PropertyChangeListener {

    private final JLabel imageLabel;
    private final JLabel songNameLabel;
    private final JLabel artistNameLabel;
    private final JLabel albumNameLabel;

    public MediaObserver(JLabel imageLabel, JLabel songNameLabel, JLabel artistNameLabel, JLabel albumNameLabel) {
        this.imageLabel = imageLabel;
        this.songNameLabel = songNameLabel;
        this.artistNameLabel = artistNameLabel;
        this.albumNameLabel = albumNameLabel;
    }

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
    
    private void aSyncLoadImageFromURLIntoLabel(final String url, final JLabel label) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL l = new URL(url);
                    BufferedImage img = ImageIO.read(l);
                    Image scaledImage = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaledImage);
                    label.setIcon(icon);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(MediaObserver.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MediaObserver.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
}
