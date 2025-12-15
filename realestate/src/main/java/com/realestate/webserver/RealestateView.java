package com.realestate.webserver;

import com.realestate.Deal;
import com.realestate.RealestateController;
import com.google.inject.Inject;
import java.util.List;

public class RealestateView {

    private final RealestateController controller;

    @Inject
    public RealestateView(RealestateController controller) {
        this.controller = controller;
    }

    private String getHead(String title) {
        return "<html><head><title>" + title + "</title>" +
                "<meta charset='UTF-8'>" +
                "<link href='https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap' rel='stylesheet'>"
                +
                "<style>" +
                ":root { --primary: #4F46E5; --bg: #F9FAFB; --card: #FFFFFF; --text: #1F2937; --text-light: #6B7280; --border: #E5E7EB; }"
                +
                "body { font-family: 'Inter', sans-serif; background: var(--bg); color: var(--text); margin: 0; padding: 40px; line-height: 1.5; }"
                +
                "h1, h2, h3 { color: var(--text); margin-top: 0; }" +
                "a { color: var(--primary); text-decoration: none; }" +
                "a:hover { text-decoration: underline; }" +
                ".container { max-width: 1000px; margin: 0 auto; }" +
                ".card { background: var(--card); border: 1px solid var(--border); border-radius: 12px; padding: 24px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); margin-bottom: 24px; transition: transform 0.2s; }"
                +
                ".card:hover { transform: translateY(-2px); box-shadow: 0 4px 6px rgba(0,0,0,0.1); }" +
                ".grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 24px; }" +
                ".badge { display: inline-block; padding: 4px 12px; border-radius: 9999px; font-size: 0.875rem; font-weight: 500; }"
                +
                ".badge-success { background: #D1FAE5; color: #065F46; }" +
                ".badge-warning { background: #FEF3C7; color: #92400E; }" +
                ".badge-gray { background: #E5E7EB; color: #374151; }" +
                ".btn { display: inline-block; background: var(--primary); color: white; padding: 10px 20px; border-radius: 6px; font-weight: 500; text-decoration: none; transition: background 0.2s; }"
                +
                ".btn:hover { background: #4338CA; text-decoration: none; }" +
                "table { width: 100%; border-collapse: collapse; margin-top: 16px; }" +
                "th, td { text-align: left; padding: 12px; border-bottom: 1px solid var(--border); }" +
                "th { font-weight: 600; color: var(--text-light); }" +
                ".label { font-size: 0.875rem; color: var(--text-light); display: block; margin-bottom: 4px; }" +
                ".value { font-size: 1rem; font-weight: 500; word-break: break-all; }" +
                ".header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 32px; }" +
                ".details-list p { margin: 8px 0; }" +
                "</style></head><body><div class='container'>";
    }

    private String getFooter() {
        return "</div></body></html>";
    }

    public String renderDealsPage() {
        List<Deal> deals = controller.getAllDeals();
        StringBuilder sb = new StringBuilder();
        sb.append(getHead("Угоди нерухомості"));

        sb.append("<div class='header'>");
        sb.append("<h1>🏠 Угоди нерухомості</h1>");
        sb.append("</div>");

        if (deals.isEmpty()) {
            sb.append("<div class='card'><p>Угод не знайдено.</p></div>");
        } else {
            sb.append("<div class='card'>");
            sb.append("<table>");
            sb.append(
                    "<thead><tr><th>ID</th><th>Дата</th><th>Статус</th><th>Покупець</th><th>Продавець</th><th>Дії</th></tr></thead>");
            sb.append("<tbody>");
            for (Deal deal : deals) {
                String status = deal.getStatus() != null ? deal.getStatus() : "Невідомо";
                String statusClass = status.toLowerCase().contains("підтверджена") ? "badge-success"
                        : (status.toLowerCase().contains("скасована") ? "badge-warning" : "badge-gray");

                sb.append("<tr>");
                sb.append("<td>#").append(deal.getId()).append("</td>");
                sb.append("<td>").append(deal.getDate()).append("</td>");
                sb.append("<td><span class='badge ").append(statusClass).append("'>").append(status)
                        .append("</span></td>");
                sb.append("<td>").append(deal.getBuyer() != null ? deal.getBuyer().getName() : "-").append("</td>");
                sb.append("<td>").append(deal.getSeller() != null ? deal.getSeller().getName() : "-").append("</td>");
                sb.append("<td><a href='/deals/").append(deal.getId())
                        .append("' class='btn' style='padding: 6px 12px; font-size: 0.875rem;'>Детальніше</a></td>");
                sb.append("</tr>");
            }
            sb.append("</tbody></table>");
            sb.append("</div>");
        }

        sb.append(getFooter());
        return sb.toString();
    }

    public String renderDealPage(int id) {
        Deal deal = controller.getDealById(id);
        if (deal == null) {
            return getHead("Угоду не знайдено") + "<h1>Угоду не знайдено</h1><a href='/deals'>Назад до списку</a>"
                    + getFooter();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getHead("Угода #" + deal.getId()));

        sb.append(
                "<div style='margin-bottom: 24px;'><a href='/deals' style='color: var(--text-light);'>&larr; Назад до списку</a></div>");

        sb.append("<div class='header'>");
        sb.append("<h1>Деталі угоди #").append(deal.getId()).append("</h1>");

        String status = deal.getStatus() != null ? deal.getStatus() : "Невідомо";
        String statusClass = status.toLowerCase().contains("підтверджена") ? "badge-success"
                : (status.toLowerCase().contains("скасована") ? "badge-warning" : "badge-gray");

        sb.append("<span class='badge ").append(statusClass).append("'>").append(status).append("</span>");
        sb.append("</div>");

        sb.append("<div class='card' style='margin-bottom: 32px;'>");
        sb.append("<span class='label'>Дата угоди</span><span class='value'>").append(deal.getDate()).append("</span>");
        sb.append("</div>");

        sb.append("<div class='grid'>");

        // Buyer
        sb.append("<div class='card details-list'>");
        sb.append("<h3>👤 Покупець</h3>");
        if (deal.getBuyer() != null) {
            sb.append("<p><span class='label'>Ім'я</span><span class='value'>").append(deal.getBuyer().getName())
                    .append("</span></p>");
            sb.append("<p><span class='label'>Контакти</span><span class='value'>")
                    .append(deal.getBuyer().getContactInfo()).append("</span></p>");
            sb.append("<p><span class='label'>Завдаток</span><span class='value'>$")
                    .append(String.format("%,.2f", deal.getBuyer().getDeposit())).append("</span></p>");
        } else {
            sb.append("<p class='text-light'>Не призначено</p>");
        }
        sb.append("</div>");

        // Seller
        sb.append("<div class='card details-list'>");
        sb.append("<h3>🏘️ Продавець</h3>");
        if (deal.getSeller() != null) {
            sb.append("<p><span class='label'>Ім'я</span><span class='value'>").append(deal.getSeller().getName())
                    .append("</span></p>");
            sb.append("<p><span class='label'>Контакти</span><span class='value'>")
                    .append(deal.getSeller().getContactInfo()).append("</span></p>");
            sb.append("<p><span class='label'>Нерухомість</span><span class='value'>")
                    .append(deal.getSeller().getProperty()).append("</span></p>");
        } else {
            sb.append("<p class='text-light'>Не призначено</p>");
        }
        sb.append("</div>");

        // Agent
        sb.append("<div class='card details-list'>");
        sb.append("<h3>🕴️ Агент</h3>");
        if (deal.getAgent() != null) {
            sb.append("<p><span class='label'>Ім'я</span><span class='value'>").append(deal.getAgent().getName())
                    .append("</span></p>");
            sb.append("<p><span class='label'>Контакти</span><span class='value'>")
                    .append(deal.getAgent().getContactInfo()).append("</span></p>");
            sb.append("<p><span class='label'>Агентство</span><span class='value'>").append(deal.getAgent().getAgency())
                    .append("</span></p>");
        } else {
            sb.append("<p class='text-light'>Не призначено</p>");
        }
        sb.append("</div>");

        // Bank
        sb.append("<div class='card details-list'>");
        sb.append("<h3>🏦 Банк</h3>");
        if (deal.getBank() != null) {
            sb.append("<p><span class='label'>Назва</span><span class='value'>").append(deal.getBank().getName())
                    .append("</span></p>");
            sb.append("<p><span class='label'>Контакти</span><span class='value'>")
                    .append(deal.getBank().getContactInfo()).append("</span></p>");
        } else {
            sb.append("<p style='color: var(--text-light); font-style: italic;'>Інформація про банк відсутня</p>");
        }
        sb.append("</div>");

        sb.append("</div>"); // End grid

        sb.append(getFooter());
        return sb.toString();
    }
}
