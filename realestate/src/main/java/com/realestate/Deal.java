package com.realestate;

import com.google.inject.Inject;

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

    public Deal() {
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
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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