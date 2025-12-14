package com.realestate;

import com.google.inject.assistedinject.Assisted;

/**
 * Фабрика для створення Agent з assisted injection
 */
public interface AgentFactory {
    Agent create(@Assisted int id, @Assisted String name, @Assisted String contactInfo, @Assisted String agency);
}