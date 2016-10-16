/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartkneepkens.openheoscontrol;

import java.util.Map;

/**
 *  This class is used for holding the response data that is returnet when executing a command. 
 *  This class has get-only access because it is given its values by Gson.
 * @author BartKneepkens
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
