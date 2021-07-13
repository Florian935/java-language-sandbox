/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tp5.tp5.rest.jaxrs.utils;

import java.util.List;

/**
 *
 * @author Florian Martin, Nathan Renaud
 */
public abstract class ArrayUtils {
    
    /**
     * Check that the list passed in parameter is not empty
     * @param <T> the type of the returned List
     * @param list the list to check
     * @return true if the list isn't empty, false else
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && list.size() > 0;
    }
}
