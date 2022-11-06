/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author RaeKyo
 */
//@WebListener
//public class ContextListener implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent e) {
//        ServletContext context = e.getServletContext();
//        String siteMapFile = context.getInitParameter("siteMapFile");
//        System.out.println("PROJECT OPENED");
//        try {
//            Properties propSiteMap = LoadSiteMap.getStiteMap(context, siteMapFile);
//            context.setAttribute("SITEMAPS", propSiteMap);
//        } catch (IOException ex) {
//            Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent e) {
//        System.out.println("PROJECT CLOSED");
//    }
//
//}
