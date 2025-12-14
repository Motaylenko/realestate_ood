package com.realestate;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Клас агента з нерухомості
 */
public class Agent extends Participant implements Reviewable {
    private int id;
    private String agency;
    private AgentService agentService;

    @AssistedInject
    public Agent(AgentService agentService, @Assisted("id") int id, @Assisted("name") String name, @Assisted("contactInfo") String contactInfo, @Assisted("agency") String agency) {
        super(name, contactInfo);
        this.id = id;
        this.agency = agency;
        this.agentService = agentService;
    }

    public void organizeTour() {
        System.out.println("Агент " + name + " з " + agency + " організовує 3D-тур");
        // Збереження агента в базу даних
        if (agentService != null) {
            agentService.saveAgent(this);
        }
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
}