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
package SystemCommands;

/**
 * Commands that can be sent to a HEOS System.
 * @author bart-kneepkens
 */
public class SystemCommands {
    public static final String HEARTBEAT = "heos://system/heart_beat";
    public static final String GET_PLAYERS = "heos://player/get_players";
    public static final String ACCOUNT_CHECK = "heos://system/check_account";
    public static final String ACCOUNT_SIGN_OUT = "heos://system/sign_out";    
    
    public static final String ACCOUNT_SIGN_IN(String username, String password){
        return "heos://system/sign_in?un=" + username + "&pw=" + password;
    } 
    
    public static final String REGISTER_FOR_CHANGE_EVENTS(boolean enabled){
        if(enabled){
            return  "heos://system/register_for_change_events?enable=on";
        }
        return  "heos://system/register_for_change_events?enable=off";
    }
}
