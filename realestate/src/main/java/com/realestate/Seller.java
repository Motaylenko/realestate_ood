package com.realestate;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Клас продавця нерухомості
 */
public class Seller extends Participant implements Reviewable {
    private int id;
    private String property;
    private SellerService sellerService;

    @AssistedInject
    public Seller(SellerService sellerService, @Assisted("id") int id, @Assisted("name") String name, @Assisted("contactInfo") String contactInfo, @Assisted("property") String property) {
        super(name, contactInfo);
        this.id = id;
        this.property = property;
        this.sellerService = sellerService;
    }

    public void counterOffer() {
        System.out.println("Продавець " + name + " зробив зустрічну пропозицію щодо " + property);
        // Збереження продавця в базу даних
        if (sellerService != null) {
            sellerService.saveSeller(this);
        }
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
}