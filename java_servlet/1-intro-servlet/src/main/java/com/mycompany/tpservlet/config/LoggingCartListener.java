/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpservlet.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@WebListener
public class LoggingCartListener implements HttpSessionAttributeListener {

    /**
     * Log everytime that an attribute session is replaced 
     *
     * @param event event who allowing the loging
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        event.getSession()
                .getServletContext()
                .log("=======> Article remplacé dans le panier !");
    }

    /**
     * Log everytime that an attribute session is removed 
     *
     * @param event event who allowing the loging
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        event.getSession()
                .getServletContext()
                .log("=======> Article supprimé du panier !");
    }

    /**
     * Log everytime that an attribute session is added 
     *
     * @param event event who allowing the loging
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        event.getSession()
                .getServletContext()
                .log("=======> Article ajouté dans le panier !");
    }

}
