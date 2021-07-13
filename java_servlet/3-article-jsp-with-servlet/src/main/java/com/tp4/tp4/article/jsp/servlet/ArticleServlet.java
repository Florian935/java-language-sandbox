/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp4.tp4.article.jsp.servlet;

import com.tp4.tp4.article.jsp.data.ArticleStub;
import com.tp4.tp4.article.jsp.model.Article;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@WebServlet(name = "ArticleServlet", urlPatterns = {"/ArticleServlet"})
public class ArticleServlet extends HttpServlet {
    
    private final String ARTICLES_VIEW = "/articles.jsp";
    private final List<Article> articles = ArticleStub.getArticles();
    private final String ARTICLES_ATTRIBUTE = "articles";
    private final String CART_ATTRIBUTE = "carts";
    private final List<Article> cartArticles = new ArrayList<>();


    
    /**
     * Dispatch the GET request to the articles view
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        updateArticleAttributeSession(session);
        
        getServletContext()
                .getRequestDispatcher(ARTICLES_VIEW)
                .forward(request, response);
    }

    /**
     * Dispatch the POST request to the articles view
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String articlesId[] = request.getParameterValues("id");
        addSelectedArticlesToCart(articlesId);
        updateCartAttributeSession(session);
        updateArticleAttributeSession(session);
        
        getServletContext()
                .getRequestDispatcher(ARTICLES_VIEW)
                .forward(request, response);
    }
    
    /**
     * Update the article attribute session
     *
     * @param session object in which we update the attribute session
     */
    private void updateArticleAttributeSession(HttpSession session) {
        if (!Objects.isNull(session.getAttribute(ARTICLES_ATTRIBUTE))) {
            session.removeAttribute(ARTICLES_ATTRIBUTE);
        }
        session.setAttribute(ARTICLES_ATTRIBUTE, articles);
    }
    
    /**
     * Add the selected articles to the cart
     * @param articlesId 
     */
    private void addSelectedArticlesToCart(String[] articlesId) {
        for (String articleId : articlesId) {
            Integer articleIdAsInteger = castStringToInteger(articleId);
            cartArticles.add(articles.get(articleIdAsInteger - 1));
        }
    }
    
    /**
     * Cast String value to Integer value
     *
     * @param valueToCast value to cast
     * @return the casted value
     */
    private Integer castStringToInteger(String valueToCast) {
        return Integer.parseInt(valueToCast);
    }
    
    /**
     * Update the cart attribute session
     *
     * @param session object in which we update the attribute session
     */
    private void updateCartAttributeSession(HttpSession session) {
        if (!Objects.isNull(session.getAttribute(CART_ATTRIBUTE))) {
            session.removeAttribute(CART_ATTRIBUTE);
        }
        session.setAttribute(CART_ATTRIBUTE, cartArticles);
    }
}
