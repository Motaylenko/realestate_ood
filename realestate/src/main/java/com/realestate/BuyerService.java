package com.realestate;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Сервіс для роботи з покупцями в базі даних
 */
@Singleton
public class BuyerService {
    private final Connection connection;

    @Inject
    public BuyerService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Зберігає покупця в базу даних
     */
    public void saveBuyer(Buyer buyer) {
        String sql = "INSERT OR REPLACE INTO buyers (id, name, email, budget) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, buyer.getId());
            stmt.setString(2, buyer.getName());
            stmt.setString(3, buyer.getContactInfo());
            stmt.setDouble(4, buyer.getDeposit());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save buyer", e);
        }
    }
}