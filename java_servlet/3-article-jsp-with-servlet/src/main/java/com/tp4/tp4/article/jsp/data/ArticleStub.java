/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp4.tp4.article.jsp.data;

import com.tp4.tp4.article.jsp.model.Article;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
public abstract class ArticleStub {

    /**
     * Stub for a list of articles
     * @return the articles list
     */
    public static List<Article> getArticles() {
        return new ArrayList<Article>() {
            {
                add(new Article(1, "Article 1", "Mon article 1"));
                add(new Article(2, "Article 2", "Mon article 2"));
                add(new Article(3, "Article 3", "Mon article 3"));
                add(new Article(4, "Article 4", "Mon article 4"));
            }
        };
    }
}
