/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * can use @Weblistener and implements HttpSessionListener,
 * HttpSessionIdListener or define it in web.xml
 *
 * @author RaeKyo
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener {

    private SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

    // this function called any time an session is created
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        System.out.println(this.date() + ": Session " + e.getSession().getId()
                + " created.");
        
        // save session in a Map to manage
        SessionRegistry.addSession(e.getSession());
    }

    // this function called any time an session is destroyed
    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        System.out.println(this.date() + ": Session " + e.getSession().getId()
                + " destroyed.");
        
        // remove session from map
        SessionRegistry.removeSession(e.getSession());
    }

    // this function called any time have an change from sessoin
    @Override
    public void sessionIdChanged(HttpSessionEvent e, String oldSessionId) {
        System.out.println(this.date() + ": Session ID " + oldSessionId
                + " changed to " + e.getSession().getId());
        
        // update session from map
        SessionRegistry.updateSessionId(e.getSession(), oldSessionId);
    }

    private String date() {
        return this.formatter.format(new Date());
    }
}
