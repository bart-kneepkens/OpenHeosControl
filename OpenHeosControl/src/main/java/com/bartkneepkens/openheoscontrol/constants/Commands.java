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
package com.bartkneepkens.openheoscontrol.constants;

/**
 *
 * @author BartKneepkens
 */
public class Commands {
    public static String HEARTBEAT = "heos://system/heart_beat";
    public static String GET_PLAYERS = "heos://player/get_players";
    
    public static String ACCOUNT_CHECK = "heos://system/check_account";
    public static String ACCOUNT_SIGN_IN(String username, String password){
        return "heos://system/sign_in?un=" + username + "&pw=" + password;
    } 
    public static String ACCOUNT_SIGN_OUT = "heos://system/sign_out";
}
