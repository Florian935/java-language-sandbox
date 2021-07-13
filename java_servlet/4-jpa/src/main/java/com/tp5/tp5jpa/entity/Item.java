/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp5.tp5jpa.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author flori narenaud
 */
@Entity
@Table(name = "ITEM")
@NamedQuery(name = "findAllItems", query = "SELECT i from Item as i")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    
    @Column(name = "name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Category category;

    public Item() { }

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Item(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Item(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.category);
        return hash;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.category, other.category);
    }

    

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", category=" + category + '}';
    }
    
    
}
