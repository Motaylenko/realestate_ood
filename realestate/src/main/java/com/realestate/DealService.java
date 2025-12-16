package com.realestate;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DealService {
    private final Connection connection;

    @Inject
    public DealService(Connection connection) {
        this.connection = connection;
    }

    public void saveDeal(Deal deal) {
        String sql = "INSERT OR REPLACE INTO deals (id, date, status, buyer_id, seller_id, agent_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, deal.getId());
            stmt.setString(2, deal.getDate());
            stmt.setString(3, deal.getStatus());
            if (deal.getBuyer() != null)
                stmt.setInt(4, deal.getBuyer().getId());
            else
                stmt.setNull(4, java.sql.Types.INTEGER);
            if (deal.getSeller() != null)
                stmt.setInt(5, deal.getSeller().getId());
            else
                stmt.setNull(5, java.sql.Types.INTEGER);
            if (deal.getAgent() != null)
                stmt.setInt(6, deal.getAgent().getId());
            else
                stmt.setNull(6, java.sql.Types.INTEGER);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save deal", e);
        }
    }

    public void saveBuyer(Buyer buyer) {
        String sql = "INSERT OR REPLACE INTO buyers (id, name, contactInfo, deposit) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, buyer.getId());
            stmt.setString(2, buyer.getName());
            stmt.setString(3, buyer.getContactInfo());
            stmt.setFloat(4, buyer.getDeposit());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save buyer", e);
        }
    }

    public void saveAgent(Agent agent) {
        String sql = "INSERT OR REPLACE INTO agents (id, name, contactInfo, agency) VALUES (?, ?, ?, ?)";

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

    public void saveSeller(Seller seller) {
        String sql = "INSERT OR REPLACE INTO sellers (id, name, contactInfo, property) VALUES (?, ?, ?, ?)";

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

    /**
     * Знаходить угоду за ID
     */
    public Deal findDealById(int id) {
        String sql = "SELECT * FROM deals WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Deal deal = Deal.fromResultSet(rs);

                    int buyerId = rs.getInt("buyer_id");
                    if (buyerId > 0)
                        deal.setBuyer(findBuyerById(buyerId));

                    int sellerId = rs.getInt("seller_id");
                    if (sellerId > 0)
                        deal.setSeller(findSellerById(sellerId));

                    int agentId = rs.getInt("agent_id");
                    if (agentId > 0)
                        deal.setAgent(findAgentById(agentId));

                    return deal;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find deal", e);
        }
        return null;
    }

    public List<Deal> findAllDeals() {
        List<Deal> deals = new ArrayList<>();
        String sql = "SELECT * FROM deals";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Deal deal = Deal.fromResultSet(rs);

                int buyerId = rs.getInt("buyer_id");
                if (buyerId > 0)
                    deal.setBuyer(findBuyerById(buyerId));

                int sellerId = rs.getInt("seller_id");
                if (sellerId > 0)
                    deal.setSeller(findSellerById(sellerId));

                int agentId = rs.getInt("agent_id");
                if (agentId > 0)
                    deal.setAgent(findAgentById(agentId));

                deals.add(deal);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find deals", e);
        }
        return deals;
    }

    public Buyer findBuyerById(int id) {
        String sql = "SELECT * FROM buyers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return Buyer.fromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find buyer", e);
        }
        return null;
    }

    public Seller findSellerById(int id) {
        String sql = "SELECT * FROM sellers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return Seller.fromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find seller", e);
        }
        return null;
    }

    public Agent findAgentById(int id) {
        String sql = "SELECT * FROM agents WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return Agent.fromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find agent", e);
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