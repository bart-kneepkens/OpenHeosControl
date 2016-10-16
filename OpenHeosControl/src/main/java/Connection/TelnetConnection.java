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
 *
 * @author bart-kneepkens
 */
public class TelnetConnection {
    
    /* Networking */
    private static Socket socket;
    private static PrintWriter out;
    private static Scanner in;
    
    private static Gson gson;
    
    public static boolean connect(String ipAddress){
         // Set up the socket, in and out
        try {
            socket = new Socket(ipAddress, 1255);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            in.useDelimiter("\r\n");
            gson = new GsonBuilder().create();
            
        } catch (IOException ex) {
            Logger.getLogger(HeosSystem.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    public static Response write(String command){
        // Check for null socket etc
        out.println(command);
        out.flush();
        return gson.fromJson(in.next(), Response.class);
    }
}
