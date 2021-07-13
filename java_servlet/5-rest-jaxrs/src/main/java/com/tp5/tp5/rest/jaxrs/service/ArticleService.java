/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp5.tp5.rest.jaxrs.service;

import com.tp5.tp5.rest.jaxrs.entity.Article;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@Stateful
public class ArticleService {
    
    @PersistenceContext(unitName = "ArticlePersistenceUnit")
    private EntityManager entityManager;
    
    /**
     * Add a article in database
     * @param article article to add
     */
    public void addArticle(Article article) {
        entityManager.persist(article);
    }
    
    /**
     * Update an article in database
     * @param article article to update
     */
    public void updateArticle(Article article) {
        entityManager.createQuery("UPDATE Article a SET a.title = :title WHERE a.id = :id")
                .setParameter("title", article.getTitle())
                .setParameter("id", article.getId())
                .executeUpdate();
    }
    
    /**
     * Retrieves all the articles in the database
     * @return list of the articles
     */
    public List<Article> getArticles() {
        return entityManager
                .createQuery("SELECT a FROM Article as a")
                .getResultList();
    }
    
    /**
     * Retrieves the article in the database
     * @param id
     * @return article corresponding
     */
    public Article getArticle(Long id) {
        return entityManager.find(Article.class, id);
    }
    
    /**
     * Delete a article in database
     * @param id id of article to delete
     */
    public void deleteArticle(Long id) {
        final Article article = getArticle(id);
        final Article managedArticle = entityManager.merge(article);
        entityManager.remove(managedArticle);
    }
}
