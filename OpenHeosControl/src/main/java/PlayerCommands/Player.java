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
package PlayerCommands;

import java.util.Map;

/**
 *
 * @author bart-kneepkens
 */
public class Player {
    private String name;
    private Double pid;
    private String model;
    private String version;
    private Double gid;
    
    public Player(Map<String, Object> map){
        this.name = (String) map.get("name");
        this.pid = (Double) map.get("pid");
        this.model = (String) map.get("model");
        this.version = (String) map.get("version");
        
        if(map.containsKey("gid")){
            this.gid = (Double) map.get("gid");
        }
    }
}
