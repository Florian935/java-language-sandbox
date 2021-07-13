/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpservlet.data;

import com.mycompany.tpservlet.model.Article;
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
        return new ArrayList<>() {
            {
                add(new Article(1, "Article 1"));
                add(new Article(2, "Article 2"));
                add(new Article(3, "Article 3"));
                add(new Article(4, "Article 4"));
            }
        };
    }

    /**
     * Stub of a list of articles in String type
     * @return the articles list in String type
     */
    public static List<String> getArticlesStringList() {
        return new ArrayList<>() {
            {
                add("Article 1");
                add("Article 2");
                add("Article 3");
                add("Article 4");
            }
        };
    }
}
