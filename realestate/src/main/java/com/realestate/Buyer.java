package com.realestate;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Клас покупця нерухомості
 */
public class Buyer extends Participant {
    private int id;
    private float deposit;
    private BuyerService buyerService;

    @AssistedInject
    public Buyer(BuyerService buyerService, @Assisted("id") int id, @Assisted("name") String name, @Assisted("contactInfo") String contactInfo, @Assisted("deposit") float deposit) {
        super(name, contactInfo);
        this.id = id;
        this.deposit = deposit;
        this.buyerService = buyerService;
    }

    public void sendProposal() {
        System.out.println("Покупець " + name + " надіслав пропозицію");
        // Збереження покупця в базу даних
        if (buyerService != null) {
            buyerService.saveBuyer(this);
        }
    }

    public final void finalizeDeal() {
        System.out.println("Покупець " + name + " завершує угоду. Завдаток: " + deposit);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDeposit() {
        return deposit;
    }
}