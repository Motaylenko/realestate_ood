package com.realestate;

import com.google.inject.Inject;

/**
 * Клас агента з нерухомості
 */
public class Agent extends Participant implements Reviewable {
    private int id;
    private String agency;
    private DealService dealService;

    @Inject
    public Agent(DealService dealService) {
        super("", "");
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
}