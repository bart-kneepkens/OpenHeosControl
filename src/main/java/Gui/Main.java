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

import Connection.IChangeListener;
import Connection.TelnetListener;
import Constants.PlayStates;
import Ssdp.SsdpClient;
import PlayerCommands.Player;
import java.awt.event.ItemEvent;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author bart-kneepkens
 */
public class Main extends javax.swing.JFrame implements IChangeListener {

    //HeosSystem sys;
    HeosController sys;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        volumeSlider = new javax.swing.JSlider();
        stopButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        ipTextField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        findButton = new javax.swing.JButton();
        nowPlayingLabel = new javax.swing.JLabel();
        playerStateLabel = new javax.swing.JLabel();
        playersComboBox = new javax.swing.JComboBox();
        songProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenHeosController");

        volumeSlider.setMajorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setSnapToTicks(true);
        volumeSlider.setValue(0);
        volumeSlider.setEnabled(false);
        volumeSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                volumeSliderMouseReleased(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Pause");
        pauseButton.setEnabled(false);
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        playButton.setText("Play");
        playButton.setEnabled(false);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        ipTextField.setEditable(false);

        connectButton.setText("Connect");
        connectButton.setEnabled(false);
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        findButton.setText("Find HEOS");
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        playerStateLabel.setFont(new java.awt.Font("Malayalam MN", 2, 13)); // NOI18N

        playersComboBox.setEnabled(false);
        playersComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                playersComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(volumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ipTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(findButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playersComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pauseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playerStateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nowPlayingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(songProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectButton)
                    .addComponent(findButton)
                    .addComponent(playersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(pauseButton)
                    .addComponent(stopButton))
                .addGap(18, 18, 18)
                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(songProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nowPlayingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(playerStateLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        String ip = this.ipTextField.getText();

        sys = new HeosController();

        if (sys.connect(ip)) {
            System.out.println("CONNECTED TO IP: " + ip);
            volumeSlider.setValue(sys.getPlayers().get(0).getVolume());

            for (Player p : sys.getPlayers()) {
                this.playersComboBox.addItem(p.getName());
            }

            this.ipTextField.setEnabled(false);
            this.connectButton.setEnabled(false);
            this.findButton.setEnabled(false);
        }

        this.playersComboBox.setEnabled(true);
        this.playButton.setEnabled(true);
        this.stopButton.setEnabled(true);
        this.pauseButton.setEnabled(true);
        this.volumeSlider.setEnabled(true);
        TelnetListener.registerForChanges(this);

        this.playerNowPlayingChanged(sys.currentPlayerPid(), sys.getNowPlayingMedia());
        this.playerStateChanged(sys.currentPlayerPid(), sys.getState());
    }//GEN-LAST:event_connectButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        sys.play();
        System.out.println("PLAY");
    }//GEN-LAST:event_playButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        sys.pause();
        System.out.println("PAUSE");
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        sys.stop();
        System.out.println("STOP");
    }//GEN-LAST:event_stopButtonActionPerformed

    private void volumeSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeSliderMouseReleased
        sys.changeVolume(volumeSlider.getValue());
        System.out.println("New volume: " + volumeSlider.getValue());
    }//GEN-LAST:event_volumeSliderMouseReleased

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        final SsdpClient cl = new SsdpClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ipTextField.setText(cl.getHeosIp());
                    connectButton.setEnabled(true);
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Trouble connecting to HEOS. Make sure you are in the same network.");
                }
            }
        }).start();
    }//GEN-LAST:event_findButtonActionPerformed

    private void playersComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_playersComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int index = this.playersComboBox.getSelectedIndex();
            sys.changePlayerIndex(index);
            System.out.println("Player changed. to: " + index);
            volumeSlider.setValue(sys.getVolume());
            this.playerNowPlayingChanged(sys.currentPlayerPid(), sys.getNowPlayingMedia());
            this.playerStateChanged(sys.currentPlayerPid(), sys.getState());
        }
    }//GEN-LAST:event_playersComboBoxItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JButton findButton;
    private javax.swing.JTextField ipTextField;
    private javax.swing.JLabel nowPlayingLabel;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JLabel playerStateLabel;
    private javax.swing.JComboBox playersComboBox;
    private javax.swing.JProgressBar songProgressBar;
    private javax.swing.JButton stopButton;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables

    @Override
    public void playerStateChanged(String pid, String state) {

        if (sys.isCurrentPlayer(pid)) {

            String formatted = "";
            if (state.equals(PlayStates.PLAY)) {
                formatted = "Playing";
            }
            if (state.equals(PlayStates.PAUSE)) {
                formatted = "Paused";
            }
            if (state.equals(PlayStates.STOP)) {
                formatted = "Stopped";
            }

            playerStateLabel.setText(formatted);
        }
    }

    @Override
    public void playerVolumeChanged(String pid, int level) {
        if (sys.isCurrentPlayer(pid)) {
            volumeSlider.setValue(level);
        }
    }

    @Override
    public void playerNowPlayingChanged(String pid, String nowPlaying) {
        if (sys.isCurrentPlayer(pid)) {
            this.nowPlayingLabel.setText(nowPlaying);
        }
    }

    @Override
    public void playerNowPlayingProgress(String pid, int current, int duration) {
        if (sys.isCurrentPlayer(pid)) {
            songProgressBar.setValue(current);
            songProgressBar.setMaximum(duration);
        }
    }
}
