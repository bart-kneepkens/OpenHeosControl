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
import Constants.Results;
import SystemCommands.HeosSystem;
import SystemCommands.SystemCommands;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bartkneepkens
 */
public class TelnetListener {
    
    /* Networking */
    private static Socket socket;
    private static PrintWriter out;
    private static Scanner in;

    private static Gson gson;

    public static void connect(final String ipAddress) {
        try {
            socket = new Socket(ipAddress, 1255);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            in.useDelimiter("\r\n");
            gson = new GsonBuilder().create();
        } catch (IOException ex) {
            Logger.getLogger(HeosSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Response write(final String command) {
        if (socket == null || out == null || in == null || gson == null) {
            return null;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                out.println(command);
                out.flush();
            }
        }).start();

        return gson.fromJson(in.next(), Response.class);
    }
    
    public static void registerForChanges(final IChangeListener listener) {
        if (socket == null || in == null || listener == null ) {
            return;
        }

        Response r = TelnetListener.write(SystemCommands.REGISTER_FOR_CHANGE_EVENTS(true));

        if (r.getResult().equals(Results.SUCCESS)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Response read = gson.fromJson(in.next(), Response.class);
                        
                        switch(read.getCommand()){
                            case Events.PLAYER_STATE_CHANGED:
                                //skip
                            case Events.PLAYER_VOLUME_CHANGED:
                                if(read.getMessage().contains("level=")){
                                    //int playerId = Integer.parseInt(read.getMessage().substring(read.getMessage().indexOf("pid=") + 4, read.getMessage().indexOf("&level=")));
                                    int level = Integer.parseInt(read.getMessage().substring(read.getMessage().indexOf("level=") + 6, read.getMessage().indexOf("&mute=")));
                                    
                                    listener.playerVolumeChanged(-1, level);
                                }
                        }
                    }
                }
            }).start();
        }
    }
}
