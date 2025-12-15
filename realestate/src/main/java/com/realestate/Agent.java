package com.realestate;

import com.google.inject.Inject;

/**
 * Клас агента з нерухомості
 */
public class Agent extends Participant implements Reviewable {
    private int id;
    private String agency;
    private DealService dealService;

    public Agent() {
        super("", "");
    }

    // @Inject
    // public Agent(DealService dealService) {
    // super("", "");
    // this.dealService = dealService;
    // }

    @Inject
    public void setDealService(DealService dealService) {
        this.dealService = dealService;
    }

    public void organizeTour() {
        System.out.println("Агент " + name + " з " + agency + " організовує 3D-тур");
        // Збереження агента в базу даних
        dealService.saveAgent(this);
    }

    public void showProperty() {
        System.out.println("Агент " + name + " показує нерухомість");
    }

    @Override
    public void reviewOffer() {
        System.out.println("Агент " + name + " переглядає пропозицію");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public static Agent fromResultSet(java.sql.ResultSet rs) throws java.sql.SQLException {
        Agent agent = new Agent();
        agent.setId(rs.getInt("id"));
        agent.setName(rs.getString("name"));
        agent.setContactInfo(rs.getString("contactInfo"));
        agent.setAgency(rs.getString("agency"));
        return agent;
    }
}