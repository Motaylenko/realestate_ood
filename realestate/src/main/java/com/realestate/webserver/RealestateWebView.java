package com.realestate.webserver;

import com.realestate.Deal;
import com.realestate.RealestateController;
import com.realestate.webserver.WebServer;
import com.google.inject.Inject;
import java.util.List;

/**
 * Клас-вигляд, який реалізує веб-інтерфейс відображення даних.
 * Відповідає за налаштування маршрутів та генерацію HTML/JSON.
 */
public class RealestateWebView {
    private final RealestateController controller;
    private final WebServer server;
    // Ми можемо перевикористати існуючий RealestateView для генерації HTML рядків,
    // щоб не дублювати код генерації таблиць.
    private final RealestateView htmlRenderer;

    /**
     * Конструктор.
     *
     * @param controller   контролер для отримання даних
     * @param server       веб-сервер
     * @param htmlRenderer допоміжний клас для генерації HTML (ми його збережемо як
     *                     "template engine")
     */
    @Inject
    public RealestateWebView(RealestateController controller, WebServer server, RealestateView htmlRenderer) {
        this.controller = controller;
        this.server = server;
        this.htmlRenderer = htmlRenderer;
        setupRoutes();
    }

    /**
     * Налаштування маршрутів веб-додатку.
     */
    private void setupRoutes() {
        // Root redirects to deals
        server.get("/", context -> context.redirect("/deals"));

        // HTML сторінка з угодами
        server.get("/deals", context -> {
            // Отримуємо HTML від рендерера (який бере дані з контролера)
            context.html(htmlRenderer.renderDealsPage());
        });

        // HTML сторінка окремої угоди
        server.get("/deals/:id", context -> {
            String idStr = context.pathParam("id");
            try {
                int id = Integer.parseInt(idStr);
                context.html(htmlRenderer.renderDealPage(id));
            } catch (NumberFormatException e) {
                context.status(400).result("Invalid Deal ID");
            }
        });

        // API для JSON (додатково, як у прикладі)
        server.get("/api/deals", context -> {
            try {
                context.json(controller.getAllDeals());
            } catch (Exception e) {
                context.status(500).result("Error: " + e.getMessage());
            }
        });
    }

    /**
     * Запускає веб-сервер.
     */
    public void start(int port) {
        server.start(port);
        System.out.println("──────────────────────────────────────────────");
        System.out.println("Real Estate Web Server Started!");
        System.out.println("HTML Interface: http://localhost:" + port + "/deals");
        System.out.println("JSON API:       http://localhost:" + port + "/api/deals");
        System.out.println("──────────────────────────────────────────────");
    }
}
