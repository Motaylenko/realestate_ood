package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Buyer з assisted injection
 */
public interface BuyerFactory {
    Buyer create(@Assisted int id, @Assisted String name, @Assisted String contactInfo, @Assisted float deposit);
}