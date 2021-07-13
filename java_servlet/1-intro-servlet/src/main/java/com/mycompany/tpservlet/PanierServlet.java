/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpservlet;

import com.mycompany.tpservlet.data.ArticleStub;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "PanierServlet", urlPatterns = {"/PanierServlet"})
public class PanierServlet extends HttpServlet {

    private final String CART_ATTRIBUTE = "cart";
    private final List<String> articles = ArticleStub.getArticlesStringList();
    private final List<String> cartArticles = new ArrayList<>();

    /**
     * Process GET method and return the articles view with the cart
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final List<String> cart
                    = (List) (session.getAttribute(CART_ATTRIBUTE));
            response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            renderCartPage(out, cart);
        }
    }

    /**
     * Process POST method and add in user session the actual cart
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
        response.sendRedirect(request.getContextPath() + "/PanierServlet");
    }

    /**
     * Render the cart page in the browser
     * @param out object which allow to print in the browser
     * @param cart cart to display
     */
    private void renderCartPage(PrintWriter out, List<String> cart) {
        printBaliseWithText(out, "h1", "Mon panier");

        if (!Objects.isNull(cart)) {
            printCart(out, cart);
        }
        printBaliseWithText(out, "h1", "Articles disponibles");
        printForm(out);
    }

    /**
     * Print string value in the html balise passed in parameter
     * @param out object which allow to print in the browser
     * @param balise
     * @param text text to print
     */
    private void printBaliseWithText(PrintWriter out, String balise, String text) {
        out.println(String.format("<%1$s>%2$s</%1$s>", balise, text));
    }

    /**
     * Print the articles in the cart
     * @param out object which allow to print in the browser
     * @param cart cart to display
     */
    private void printCart(PrintWriter out, List<String> cart) {
        out.println("<p><ul>");
        cart.forEach(c -> out.println(String.format("<li>%s</li>", c)));
        out.println("</ul></p>");
    }

    /**
     * Print the form in the browser
     * @param out object which allow to print in the browser
     */
    private void printForm(PrintWriter out) {
        out.println("<form method=\"post\" action=\"PanierServlet\">");

        printAvailableArticles(out);
        out.println("<input type=\"submit\" value=\"Ajouter\">\n");
        out.println("</form >");
    }

    /**
     * Print the available articles
     * @param out object which allow to print in the browser
     */
    private void printAvailableArticles(PrintWriter out) {
        out.println("<p>");
        articles.forEach(a -> out.println(
                String.format(
                        "<input type=\"checkbox\" name=\"id\" value=%d>%s<br>",
                        articles.indexOf(a),
                        a)));
        out.println("</p>");
    }

    /**
     * Update the cart attribute session
     * @param session object in which we update the attribute session
     */
    private void updateCartAttributeSession(HttpSession session) {
        if (!Objects.isNull(session.getAttribute(CART_ATTRIBUTE))) {
            session.removeAttribute(CART_ATTRIBUTE);
        }
        session.setAttribute(CART_ATTRIBUTE, cartArticles);
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
     * Add the selected articles to the cart
     * @param articlesId 
     */
    private void addSelectedArticlesToCart(String[] articlesId) {
        for (String articleId : articlesId) {
            Integer articleIdAsInteger = castStringToInteger(articleId);
            cartArticles.add(articles.get(articleIdAsInteger));
        }
    }
}
