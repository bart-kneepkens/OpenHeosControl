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
package Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author bart-kneepkens
 */
public class SsdpClient {
    private final String SSDP_HOST = "239.255.255.250";
    private final int SSDP_PORT = 1900;
    private final int SSDP_MX = 1;
    private final String SSDP_ST = "urn:schemas-denon-com:device:ACT-Denon:1";
    
    public String SSDP_REQUEST(){
        return "M-SEARCH * HTTP/1.1\r\n" 
                + "HOST: " + SSDP_HOST + ":" + SSDP_PORT + "\r\n" 
                + "MAN: \"ssdp:discover\"" + "\r\n"  
                + "MX: " + SSDP_MX + "\r\n"
                + "ST: " + SSDP_ST + "\r\n"
                + "\r\n";
    }
    
    public String findHeosIp() throws UnknownHostException, IOException{
        
        InetAddress multicastAddress = InetAddress.getByName(this.SSDP_HOST);
       
        MulticastSocket socket = new MulticastSocket(SSDP_PORT);
        socket.setReuseAddress(true);
        socket.setSoTimeout(10000);
        socket.joinGroup(multicastAddress);
        
        
        byte[] packetBuffer = SSDP_REQUEST().getBytes("UTF-8");
        DatagramPacket mSearchPacket = new DatagramPacket(packetBuffer, packetBuffer.length, multicastAddress, SSDP_PORT);
        socket.send(mSearchPacket);
        
        /**
         * Should run until either:
         * - A compatible device is found
         * - The socket times out
         */
        while(true){
            
            byte[] responseBuffer = new byte[8192];
                DatagramPacket packet = new DatagramPacket(responseBuffer, responseBuffer.length);
                socket.receive(packet);

                String packetAddress = packet.getAddress().getHostAddress();
                
                String fullResponse = new String(responseBuffer, 0, packet.getLength());
                
                /**
                 * Make sure the response is not from localhost and the USN contains the HEOS urn.
                 */
                if(!packetAddress.equals(InetAddress.getLocalHost().getHostAddress())){
                  if(fullResponse.contains(SSDP_ST)){
                        return packetAddress;
                    }
                }                
        }
    }
}
