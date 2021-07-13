/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp5.tp5.rest.jaxrs.controller;

import com.tp5.tp5.rest.jaxrs.service.ArticleService;
import com.tp5.tp5.rest.jaxrs.entity.Article;
import com.tp5.tp5.rest.jaxrs.utils.ArrayUtils;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@Path("articles")
public class ArticleController {
    
    @EJB
    private ArticleService dataService;
    
    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {
        return "Hello World from ArticleController !";
    }
    
    /**
     * GET one article by ID
     * @param id id of the article to retrieve
     * @return the article
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Long id) {
        final Article article = dataService.getArticle(id);
        
        if (article == null) {
            final String responseBody = "Article with id " + id + " not found";
            return buildResponse(Response.Status.NOT_FOUND, responseBody);
        }
        return buildResponse(Response.Status.OK, article);
    }
    
    /**
     * Retrieves all the articles
     * @return list of articles
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        final List<Article> articles =  dataService.getArticles();
       
        if (ArrayUtils.isNotEmpty(articles)) {
            return buildResponse(Response.Status.OK, articles);
        }
        final String responseBody = "No articles in table \"ARTICLE\"";
        return buildResponse(Response.Status.NOT_FOUND, responseBody);
    }
    
    /**
     * POST a new article
     * @param article article to save
     * @return the article saved
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Article article) {
        dataService.addArticle(article);
        
        return buildResponse(Response.Status.CREATED, article);
    }
    
    /**
     * Update the article passed in the request body for the article
     * corresponding to the id passed in parameter
     * @param id id of the article to update
     * @param article new values of the article to update
     * @return The response for the client 
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Article article) {
        article.setId(id);
        final Article tryGetArticle = dataService.getArticle(id);
        if (tryGetArticle == null) {
            final String responseBody = "Article with id " + id + " not found";
            return buildResponse(Response.Status.NOT_FOUND, responseBody);
        }
        dataService.updateArticle(article);
        
        return buildResponse(Response.Status.NO_CONTENT);
    }
    
    
   /**
    * Delete the article corresponding to the id parameter passed in the URI
    * @param id id of the article to delete
    * @return The response for the client
    */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOne(@PathParam("id") Long id) {
        final Article tryGetArticle = dataService.getArticle(id);
        if (tryGetArticle == null) {
            final String responseBody = "Article with id " + id + " not found";
            return buildResponse(Response.Status.NOT_FOUND, responseBody);
        }
        
        dataService.deleteArticle(id);
        return buildResponse(Response.Status.NO_CONTENT);
    }

    /**
     * return the HTTP response to send to the client
     * @param status http status code
     * @param responseBody body of the response
     * @return The response for the client
     */
    private Response buildResponse(Response.Status status, Object responseBody) {
           return Response
                    .status(status)
                    .entity(responseBody)
                    .build();
    }
    
    /**
     * return the HTTP response to send to the client
     * @param status http status code
     * @return the Response with the http status code passed in parameter
     */
    private Response buildResponse(Response.Status status) {
           return Response
                    .status(status)
                    .build();
    }
}
