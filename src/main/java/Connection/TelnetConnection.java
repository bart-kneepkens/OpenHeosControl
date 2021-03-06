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
package Connection;

import SystemCommands.HeosSystem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class with static methods that handle the Telnet connection to the Heos system.
 * @author bart-kneepkens
 */
public class TelnetConnection {

    /* Networking */
    private static Socket socket;
    private static PrintWriter out;
    private static Scanner in;

    private static Gson gson;

    /**
     * Connect to a Heos System that is located at a certain ip.
     * @param ipAddress a valid IP address.
     */
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

    /**
     * Write a certain command to the Heos System. 
     * @param command a String describing the command (see Constants package)
     * @return a Response
     */
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
    
}
