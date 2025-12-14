package com.realestate;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Сервіс для роботи з агентами в базі даних
 */
@Singleton
public class AgentService {
    private final Connection connection;

    @Inject
    public AgentService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Зберігає агента в базу даних
     */
    public void saveAgent(Agent agent) {
        String sql = "INSERT OR REPLACE INTO agents (id, name, email, company) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, agent.getId());
            stmt.setString(2, agent.getName());
            stmt.setString(3, agent.getContactInfo());
            stmt.setString(4, agent.getAgency());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save agent", e);
        }
    }
}