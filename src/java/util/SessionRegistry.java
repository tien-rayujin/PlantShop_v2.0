/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RaeKyo
 */
public class SessionRegistry {
    private static final Map<String, HttpSession> SESSIONS = new Hashtable<>();
    
    public static void addSession(HttpSession session){
        SESSIONS.put(session.getId(), session);
    }
    
    public static void updateSessionId(HttpSession session, String oldSessionId){
        synchronized(SESSIONS){
            SESSIONS.remove(oldSessionId);
            addSession(session);
        }
    }
    
    public static void removeSession(HttpSession session){
        SESSIONS.remove(session.getId());
    }
    
    public static List<HttpSession> getAllSessions(){
        return new ArrayList<>(SESSIONS.values());
    }
    
    public static int getNumberOfSessions(){
        return SESSIONS.size();
    }
}// end SessionRegistry
