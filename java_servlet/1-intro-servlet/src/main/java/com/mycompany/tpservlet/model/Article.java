/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpservlet.model;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
public class Article {
    private Integer id;
    private String name;

    public Article() {}
    
    public Article(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Article{id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
    
    
}
