/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp4.tp4.article.jsp.model;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
public class Article {
    private int id;
    private String name;
    private String description;

    public Article() {}
    
    public Article(int id, String name, String descrption) {
        this.id = id;
        this.name = name;
        this.description = descrption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
    
}
