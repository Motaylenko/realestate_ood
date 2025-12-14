package com.realestate;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class DealService {
    private final Connection connection;
    private final DealFactory dealFactory;

    @Inject
    public DealService(Connection connection, DealFactory dealFactory) {
        this.connection = connection;
        this.dealFactory = dealFactory;
    }

    public void saveDeal(Deal deal) {
        String sql = "INSERT OR REPLACE INTO deals (id, date, status, seller_id, buyer_id, agent_id, bank_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, deal.getId());
            stmt.setString(2, deal.getDate());
            stmt.setString(3, deal.getStatus());

            // Для seller, buyer, agent, bank потрібно буде додати методи для отримання їх ID
            // Поки що встановлюємо null або 0
            stmt.setNull(4, java.sql.Types.INTEGER); // seller_id
            stmt.setNull(5, java.sql.Types.INTEGER); // buyer_id
            stmt.setNull(6, java.sql.Types.INTEGER); // agent_id
            stmt.setNull(7, java.sql.Types.INTEGER); // bank_id

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save deal", e);
        }
    }

    /**
     * Знаходить угоду за ID
     */
    public Deal findDealById(int id) {
        String sql = "SELECT id, date, status FROM deals WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Deal deal = dealFactory.create(rs.getInt("id"), rs.getString("date"));
                    // Статус встановлюється в конструкторі як "Нова"
                    return deal;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find deal", e);
        }
        return null;
    }

    /**
     * Оновлює статус угоди
     */
    public void updateDealStatus(int dealId, String status) {
        String sql = "UPDATE deals SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, dealId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update deal status", e);
        }
    }
}