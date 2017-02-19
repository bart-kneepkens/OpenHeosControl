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
package Connection;

import Constants.Events;
import Constants.PlayStates;
import Constants.Results;
import com.google.gson.Gson;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author bartkneepkens
 */
public class ChangeListenerRunnable implements Runnable {

    Scanner in;
    Gson gson;
    IChangeListener listener;

    @Override
    public void run() {
        while (true) {
            Response read = gson.fromJson(in.next(), Response.class);

            switch (read.getCommand()) {
                case Events.PLAYER_STATE_CHANGED:
                    if (read.getMessage().contains("state=")) {
                        String state = read.getMessage().substring(read.getMessage().indexOf("state=") + 6);
                        String pid = read.getMessage().substring(read.getMessage().indexOf("pid=") + 4, read.getMessage().indexOf("state="));
                        listener.playerStateChanged(pid, state);
                    }
                case Events.PLAYER_VOLUME_CHANGED:
                    if (read.getMessage().contains("level=")) {
                        int level = Integer.parseInt(read.getMessage().substring(read.getMessage().indexOf("level=") + 6, read.getMessage().indexOf("&mute=")));
                        String pid = read.getMessage().substring(read.getMessage().indexOf("pid=") + 4, read.getMessage().indexOf("level="));
                        listener.playerVolumeChanged(pid, level);
                    }
                case Events.PLAYER_NOW_PLAYING_CHANGED:
                    
                    if (read.getMessage().contains("state")) {

                        String pid = read.getMessage().substring(read.getMessage().indexOf("pid=") + 4);
                        // Event does not hold any info about the song.
                        Response r = TelnetConnection.write(PlayerCommands.PlayerCommands.GET_NOW_PLAYING_MEDIA(pid));

                        // Code repetition, fix this.
                        if (r.getResult().equals(Results.SUCCESS)) {
                            Map<String, Object> map = (Map<String, Object>) r.getPayload();

                            listener.playerNowPlayingChanged(pid, "'" + map.get("song") + "' by " + map.get("artist"));
                        }
                    }

                case Events.PLAYER_NOW_PLAYING_PROGRESS:
                    if (read.getMessage().contains("cur_pos")) {
                        String pid = read.getMessage().substring(read.getMessage().indexOf("pid=") + 4, read.getMessage().indexOf("cur_pos"));
                        int current = Integer.parseInt(read.getMessage().substring(read.getMessage().indexOf("cur_pos=") + 8, read.getMessage().indexOf("&duration")));
                        int duration = Integer.parseInt(read.getMessage().substring(read.getMessage().indexOf("duration=") + 9));
                        listener.playerNowPlayingProgress(pid, current, duration);
                    }

            }
        }
    }

}
