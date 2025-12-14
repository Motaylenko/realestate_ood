package com.realestate;

import com.google.inject.Inject;

/**
 * Клас покупця нерухомості
 */
public class Buyer extends Participant {
    private int id;
    private float deposit;
    private DealService dealService;

    public Buyer() {
        super("", "");
    }

    // @Inject
    // public Buyer(DealService dealService) {
    //     super("", "");
    //     this.dealService = dealService;
    // }

    @Inject
    public void setDealService(DealService dealService) {
        this.dealService = dealService;
    }

    public void sendProposal() {
        System.out.println("Покупець " + name + " надіслав пропозицію");
        // Збереження покупця в базу даних
        dealService.saveBuyer(this);
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

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }
}