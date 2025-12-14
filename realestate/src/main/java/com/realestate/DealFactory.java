package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Deal з assisted injection
 */
public interface DealFactory {
    Deal create(@Assisted int id, @Assisted String date);
}