package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Deal з assisted injection
 */
public interface DealFactory {
    Deal create(@Assisted("id") int id, @Assisted("date") String date);
}