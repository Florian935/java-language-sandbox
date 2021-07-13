/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp5.tp5jpa.servlet;

import com.tp5.tp5jpa.config.DataService;
import com.tp5.tp5jpa.entity.Category;
import com.tp5.tp5jpa.entity.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class DataServlet extends HttpServlet {

    @EJB
    public DataService dataService;

    /**
     * Process the GET request. Print all categories and items saved in the
     * databse
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final List<Category> categories = dataService.getCategories();
        final List<Item> items = dataService.getItems();

        try ( PrintWriter out = response.getWriter()) {
            out.println(categories.toString());
            out.println(items.toString());
        }
    }

    /**
     * Process the POST request. Add a category and an item
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final Category category = addCategory("science");
        addItem("Mon livre de science", category);

        final List<Category> categories = dataService.getCategories();
        final List<Item> items = dataService.getItems();
        try ( PrintWriter out = response.getWriter()) {
            out.println(categories.toString());
            out.println(items.toString());
        }
    }

    /**
     * Process the DELETE request. Delete a category and an item
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final List<Category> categories = dataService.getCategories();
        final List<Item> items = dataService.getItems();

        if (isNotEmpty(categories) && isNotEmpty(items)) {
            dataService.deleteItem(items.get(0));
            dataService.deleteCategory(categories.get(0));
        }
    }

    /**
     * Add a category in the database
     *
     * @param label label of the category
     * @return the category added
     */
    private Category addCategory(String label) {
        final Category category = new Category(label);
        dataService.addCategory(category);

        return category;
    }

    /**
     * Add an item in the database
     *
     * @param label label of the item
     * @param category category associated to the item
     */
    private void addItem(String label, Category category) {
        dataService.addItem(new Item(1L, label, category));
    }

    /**
     * Check that the list passed in parameter is not empty
     * @param <T> the type of the returned List
     * @param list the list to check
     * @return true if the list isn't empty, false else
     */
    private <T> boolean isNotEmpty(List<T> list) {
        return list != null && list.size() > 0;
    }
}
