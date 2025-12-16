package com.realestate;

import java.util.List;
import com.realestate.Deal;

import com.google.inject.Inject;

/**
 * Контролер для отримання даних про нерухомість.
 * Реалізує патерн MVC як проміжну ланку між View та Model.
 */
public class RealestateController {
    private final DealService dealService;

    /**
     * Конструктор з впровадженням залежності DealService (Model).
     *
     * @param dealService сервіс для роботи з угодами
     */
    @Inject
    public RealestateController(DealService dealService) {
        this.dealService = dealService;
    }

    /**
     * Отримує всі угоди з бази даних.
     *
     * @return список всіх угод
     */
    public List<Deal> getAllDeals() {
        return dealService.findAllDeals();
    }

    /**
     * Отримує угоду за ID.
     *
     * @param id ідентифікатор угоди
     * @return угода або null
     */
    public Deal getDealById(int id) {
        return dealService.findDealById(id);
    }
}
