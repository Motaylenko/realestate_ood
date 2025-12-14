package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Buyer з assisted injection
 */
public interface BuyerFactory {
    Buyer create(@Assisted("id") int id, @Assisted("name") String name, @Assisted("contactInfo") String contactInfo, @Assisted("deposit") float deposit);
}