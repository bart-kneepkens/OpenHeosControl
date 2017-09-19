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

import Constants.Assets;
import Constants.PlayStates;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author bartkneepkens
 */
public class ControlsObserver implements PropertyChangeListener {

    private ImageIcon playIcon;
    private ImageIcon pauseIcon;
    private ImageIcon stopIcon;
    private ImageIcon playIconPressed;
    private ImageIcon pauseIconPressed;
    private ImageIcon stopIconPressed;

    private final JButton playPauseStopButton;
    private final JProgressBar songProgressBar;
    private final JLabel timePassedLabel;
    private final JLabel songDurationLabel;

    public ControlsObserver(JButton playPauseStopButton, JProgressBar songProgressBar,
            JLabel timePassedLabel, JLabel songDurationLabel) {
        this.playPauseStopButton = playPauseStopButton;
        this.songProgressBar = songProgressBar;
        this.timePassedLabel = timePassedLabel;
        this.songDurationLabel = songDurationLabel;
        this.loadAssets();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String changedPropertyName = evt.getPropertyName();

        switch (changedPropertyName) {
            case ObservablePropertyNames.PLAYSTATE:
                String newState = (String) evt.getNewValue();
                this.setPlayButtonState(newState);
                break;
            case ObservablePropertyNames.SONGPROGRESS:
                int newProgress = (int) evt.getNewValue();
                this.songProgressBar.setValue(newProgress);
                String formattedProgress = this.formattedTime(newProgress);
                this.timePassedLabel.setText(formattedProgress);
                break;
            case ObservablePropertyNames.SONGDURATION:
                int newDuration = (int) evt.getNewValue();
                this.songProgressBar.setMaximum(newDuration);
                String formattedDuration = this.formattedTime(newDuration);
                this.songDurationLabel.setText(formattedDuration);
            default:
                break;
        }
    }

    public void setPlayButtonState(String state) {
        switch (state) {
            case PlayStates.PLAY:
                this.playPauseStopButton.setIcon(playIcon);
                this.playPauseStopButton.setPressedIcon(playIconPressed);
                break;
            case PlayStates.PAUSE:
                this.playPauseStopButton.setIcon(pauseIcon);
                this.playPauseStopButton.setPressedIcon(pauseIconPressed);
                break;
            case PlayStates.STOP:
                this.playPauseStopButton.setIcon(stopIcon);
                this.playPauseStopButton.setPressedIcon(stopIconPressed);
                break;
            default:
                break;
        }
    }

    private void loadAssets() {
        this.playIcon = new ImageIcon(getClass().getResource(Assets.PLAY));
        this.playIconPressed = new ImageIcon(getClass().getResource(Assets.PLAY_PRESSED));
        this.pauseIcon = new ImageIcon(getClass().getResource(Assets.PAUSE));
        this.pauseIconPressed = new ImageIcon(getClass().getResource(Assets.PAUSE_PRESSED));
        this.stopIcon = new ImageIcon(getClass().getResource(Assets.STOP));
        this.stopIconPressed = new ImageIcon(getClass().getResource(Assets.STOP_PRESSED));
    }
    
    private String formattedTime(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }
}
