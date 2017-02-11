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

import java.util.Map;

/**
 *  This class is used for holding the response data that is returned when executing a command. 
 *  This class has get-only access because it is given its values by Gson.
 * @author bart-kneepkens
 */
public class Response {
    
    private Map<String, String> heos;
    
    /**
     * Can be either null, a Map or an array of maps.
     */
    private Object payload;

    public String getCommand() {
        return heos.get("command");
    }

    public String getResult() {
        return heos.get("result");
    }

    public String getMessage() {
        return heos.get("message");
    }
    
    public Object getPayload(){
        return payload;
    }
}
