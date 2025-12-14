package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Agent з assisted injection
 */
public interface AgentFactory {
    Agent create(@Assisted("id") int id, @Assisted("name") String name, @Assisted("contactInfo") String contactInfo, @Assisted("agency") String agency);
}