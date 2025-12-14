package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Seller з assisted injection
 */
public interface SellerFactory {
    Seller create(@Assisted("id") int id, @Assisted("name") String name, @Assisted("contactInfo") String contactInfo, @Assisted("property") String property);
}