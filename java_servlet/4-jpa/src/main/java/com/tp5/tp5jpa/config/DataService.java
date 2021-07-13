/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp5.tp5jpa.config;

import com.tp5.tp5jpa.entity.Category;
import com.tp5.tp5jpa.entity.Item;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@Stateful
public class DataService {
    
    @PersistenceContext(unitName = "Tp5JpaPersistentUnit")
    public EntityManager entityManager;
    
    /**
     * Add a category in database
     * @param category category to add
     */
    public void addCategory(Category category) {
        entityManager.persist(category);
    }
    
    /**
     * Delete a category in database
     * @param category to delete
     */
    public void deleteCategory(Category category) {
        final Category managedCategory = entityManager.merge(category);
        entityManager.remove(managedCategory);
    }
    
    /**
     * Retrieves all the categories in the database
     * @return list of the categories
     */
    public List<Category> getCategories() {
        return entityManager
                .createQuery("SELECT c from Category as c")
                .getResultList();
    }
    
    /**
     * Add an item in database
     * @param item item to add
     */
    public void addItem(Item item) {
        entityManager.persist(item);
    }
    
    /**
     * Delete an item in database
     * @param item item to delete
     */
    public void deleteItem(Item item) {
        final Item managedItem = entityManager.merge(item);
        entityManager.remove(managedItem);
    }
    
    /**
     * Retrieves all the items in the database
     * @return list of the items
     */
    public List<Item> getItems() {
        return entityManager.createNamedQuery("findAllItems").getResultList();

    }
}
