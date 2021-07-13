/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp4.tp4.jsp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
    public final String FORM_VIEW = "/form.jsp";
    public final String GREETING_VIEW = "/greeting.jsp";


    /**
     * Dispatch the GET request to the form view
     * @param request 
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(FORM_VIEW)
                .forward(request, response);
    }

    /**
     * Dispatch the POST request to the greeting view
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(GREETING_VIEW)
                .forward(request, response);
    }

    
}
