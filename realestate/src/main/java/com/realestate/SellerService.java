package com.realestate;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Сервіс для роботи з продавцями в базі даних
 */
@Singleton
public class SellerService {
    private final Connection connection;

    @Inject
    public SellerService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Зберігає продавця в базу даних
     */
    public void saveSeller(Seller seller) {
        String sql = "INSERT OR REPLACE INTO sellers (id, name, email, property) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, seller.getId());
            stmt.setString(2, seller.getName());
            stmt.setString(3, seller.getContactInfo());
            stmt.setString(4, seller.getProperty());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save seller", e);
        }
    }
}