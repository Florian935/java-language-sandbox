/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpservlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.tpservlet.data.ArticleStub;
import com.mycompany.tpservlet.model.Article;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@WebServlet(name = "ArticleServlet", urlPatterns = {"/ArticleServlet"})
public class ArticleServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Collections of articles to return
    private final List<Article> articles = ArticleStub.getArticles();

    /**
     * Processes GET request and return articles in responses
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException is a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");

            String indexArticle = request.getParameter("id");
            if (indexArticle == null) {
                response.setStatus(HttpServletResponse.SC_OK);
                articles.forEach(a -> out.print("<h2>" + a.getName() + "</h2>"));
            } else {
                Integer indexArticleAsInteger = castStringToInteger(indexArticle);
                
                final String bodyToReturn = String.format(
                        "<h2>%s</h2>", articles.get(indexArticleAsInteger - 1).getName());
                returnResponse(out, response, HttpServletResponse.SC_OK, bodyToReturn);
            }
        }
    }

    /**
     * Processes POST request and post new articles
     *
     * @param request servlet request
     * @param response list of articles with the new posted article
     * @throws ServletException is a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            Article articleToAdd = objectMapper.readValue(request.getReader()
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator())), Article.class);

            articles.add(articleToAdd);

            response.setStatus(HttpServletResponse.SC_CREATED);
            articles.forEach(a -> out.println(a.getName()));
        }
    }

    /**
     * Processes DELETE request and delete articles from id in query param
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException is a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String articleId = request.getParameter("id");

            if (assertParameterNotNull(articleId)) {
                Integer articleIdToInteger = castStringToInteger(articleId);

                Optional<Article> articleToDelete = articles
                        .stream()
                        .filter(a -> a.getId().equals(articleIdToInteger))
                        .findFirst();

                if (articleToDelete.isPresent()) {
                    articles.remove(articleToDelete.get());
                    returnResponse(response, HttpServletResponse.SC_NO_CONTENT);
                } else {
                    returnResponse(out, response, HttpServletResponse.SC_NOT_FOUND, "Article not found");
                }
            } else {
                returnResponse(out, response, HttpServletResponse.SC_NOT_FOUND, "Article not found");
            }
        }
    }

    /**
     * Processes PUT request and update article from id in query param
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException is a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String articleId = request.getParameter("id");

            if (assertParameterNotNull(articleId)) {
                Article articleFromBody = getArticleFromJsonBodyRequest(request, Article.class);
                Optional<Article> articleToUpdate = getArticleToUpdate(articleId);

                if (articleToUpdate.isPresent()) {
                    updateArticle(articleToUpdate.get(), articleFromBody.getName(), articleId);
                    updateArticleInList(articleToUpdate.get());
                    returnResponse(out, response, HttpServletResponse.SC_CREATED, articleToUpdate.get());
                } else {
                    returnResponse(out, response, HttpServletResponse.SC_NOT_FOUND, "Article not found");
                }
            } else {
                returnResponse(out, response, HttpServletResponse.SC_NOT_FOUND, "Article not found");
            }
        }
    }
    
    /**
     * Parse the json string value from request and return an object
     * @param request 
     * @return the object from the json string value
     * @throws IOException 
     */
    private Article getArticleFromJsonBodyRequest(
            HttpServletRequest request, Class<Article> toClass) throws IOException {
        String parseBodyRequest = request.getReader().lines().collect(
                        Collectors.joining(System.lineSeparator()));
        
        return fromJsonToObject(parseBodyRequest, toClass);
    }
    
    /**
     * Return the article to update
     * @param articleId
     * @return article to update
     */
    private Optional<Article> getArticleToUpdate(String articleId) {
        Integer articleIdToInteger = castStringToInteger(articleId);
        return getArticleById(articleIdToInteger);
    }
    
    /**
     * Update the article passed in parameter in the list
     * @param articleToUpdate 
     */
    private void updateArticleInList(Article articleToUpdate) {
        final int articleIndexFromArticlesList = articles.indexOf(articleToUpdate);
        articles.set(articleIndexFromArticlesList, articleToUpdate);
    }

    /**
     * Convert json string to an wanted object class
     * @param <T>
     * @param json string to convert
     * @param toClass class wanted
     * @return the object wanted from the json string value
     * @throws IOException 
     */
    private <T> T fromJsonToObject(String json, Class<T> toClass) throws IOException {
        try {
            return objectMapper.readValue(json, toClass);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
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
     * Filter and get the article from the list by Id. Return an empty Optional
     * if the article is not found
     * @param id
     * @return the Optional<Article> matching the id passed in parameter
     */
    private Optional<Article> getArticleById(Integer id) {
        return articles
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    /**
     * Update the name and id of the article passed in paramter
     * @param article
     * @param name
     * @param id 
     */
    private void updateArticle(Article article, String name, String id) {
        final Integer idToInteger = castStringToInteger(id);
        article.setName(name);
        article.setId(idToInteger);
    }

    /**
     * Assert that the value passed is not null and lower than articles size
     *
     * @param parameter to check
     * @return boolean
     */
    private boolean assertParameterNotNull(String parameter) {
        return parameter != null;
    }
    
    /**
     * Set the http status code of the response passed in parameter
     * @param response
     * @param statusCode 
     */
    private void returnResponse(HttpServletResponse response, int statusCode) {
        response.setStatus(statusCode);
    }

    /**
     * Set the http status code and body of the response passed in parameter
     * the body passed in parameter.
     * @param out
     * @param response
     * @param statusCode
     * @param body 
     */
    private void returnResponse(PrintWriter out, HttpServletResponse response, int statusCode, Object body) {
        response.setStatus(statusCode);
        out.println(body);
    }
}
