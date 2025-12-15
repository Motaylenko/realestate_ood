package com.realestate;

import com.google.inject.Inject;

/**
 * Клас продавця нерухомості
 */
public class Seller extends Participant implements Reviewable {
    private int id;
    private String property;
    private DealService dealService;

    public Seller() {
        super("", "");
    }

    // @Inject
    // public Seller(DealService dealService) {
    //     super("", "");
    //     this.dealService = dealService;
    // }

    @Inject
    public void setDealService(DealService dealService) {
        this.dealService = dealService;
    }

    public void counterOffer() {
        System.out.println("Продавець " + name + " зробив зустрічну пропозицію щодо " + property);
        // Збереження продавця в базу даних
        dealService.saveSeller(this);
    }

    @Override
    public void reviewOffer() {
        System.out.println("Продавець " + name + " переглядає пропозицію");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}