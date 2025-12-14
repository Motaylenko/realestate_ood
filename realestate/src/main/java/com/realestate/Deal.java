package com.realestate;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Клас угоди з нерухомістю
 */
public class Deal {
    private int id;
    private String date;
    private String status;
    private Seller seller;
    private Buyer buyer;
    private Agent agent;
    private Bank bank;
    private DealService dealService;

    @AssistedInject
    public Deal(DealService dealService, @Assisted("id") int id, @Assisted("date") String date) {
        this.id = id;
        this.date = date;
        this.status = "Нова";
        this.dealService = dealService;
    }
    
    public void confirm() {
        System.out.println("Угода #" + id + " підтверджена");
        this.status = "Підтверджена";
    }
    
    public void cancel() {
        System.out.println("Угода #" + id + " скасована");
        this.status = "Скасована";
    }
    
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
    
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
    public int getId() {
        return id;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getStatus() {
        return status;
    }
    
    public Seller getSeller() {
        return seller;
    }
    
    public Buyer getBuyer() {
        return buyer;
    }
    
    public Agent getAgent() {
        return agent;
    }
    
    public Bank getBank() {
        return bank;
    }
}