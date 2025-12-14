package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Seller з assisted injection
 */
public interface SellerFactory {
    Seller create(@Assisted int id, @Assisted String name, @Assisted String contactInfo, @Assisted String property);
}