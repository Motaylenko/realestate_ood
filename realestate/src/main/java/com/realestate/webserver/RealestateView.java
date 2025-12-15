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

    public String renderDealsPage() {
        List<Deal> deals = controller.getAllDeals();
        return renderDeals(deals);
    }

    public String renderDealPage(int id) {
        Deal deal = controller.getDealById(id);
        if (deal != null) {
            return renderDeal(deal);
        }
        return "<html><body><h1>Deal not found</h1><a href='/deals'>Back</a></body></html>";
    }

    private String renderDeals(List<Deal> deals) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Real Estate Deals</title>");
        sb.append("<style>");
        sb.append("body { font-family: Arial, sans-serif; margin: 20px; }");
        sb.append("table { border-collapse: collapse; width: 100%; }");
        sb.append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
        sb.append("th { background-color: #f2f2f2; }");
        sb.append("tr:hover { background-color: #f5f5f5; }");
        sb.append(
                ".btn { display: inline-block; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; }");
        sb.append("</style>");
        sb.append("</head><body>");
        sb.append("<h1>Deals</h1>");
        sb.append(
                "<table><tr><th>ID</th><th>Date</th><th>Status</th><th>Buyer</th><th>Seller</th><th>Agent</th><th>Actions</th></tr>");
        for (Deal deal : deals) {
            sb.append("<tr>");
            sb.append("<td>").append(deal.getId()).append("</td>");
            sb.append("<td>").append(deal.getDate()).append("</td>");
            sb.append("<td>").append(deal.getStatus()).append("</td>");
            sb.append("<td>").append(deal.getBuyer() != null ? deal.getBuyer().getName() : "N/A").append("</td>");
            sb.append("<td>").append(deal.getSeller() != null ? deal.getSeller().getName() : "N/A").append("</td>");
            sb.append("<td>").append(deal.getAgent() != null ? deal.getAgent().getName() : "N/A").append("</td>");
            sb.append("<td><a href='/deals/").append(deal.getId()).append("'>View Details</a></td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</body></html>");
        return sb.toString();
    }

    public String renderDeal(Deal deal) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head><title>Deal #").append(deal.getId()).append("</title>");
        sb.append("<style>");
        sb.append(
                "body { font-family: Arial, sans-serif; margin: 20px; max-width: 800px; margin: 0 auto; padding: 20px; }");
        sb.append(".card { border: 1px solid #ddd; padding: 20px; border-radius: 8px; margin-bottom: 20px; }");
        sb.append("h2 { border-bottom: 2px solid #eee; padding-bottom: 10px; }");
        sb.append(".back-link { display: inline-block; margin-top: 20px; color: #007bff; text-decoration: none; }");
        sb.append("</style>");
        sb.append("</head><body>");

        sb.append("<h1>Deal Details #").append(deal.getId()).append("</h1>");

        sb.append("<div class='card'>");
        sb.append("<p><b>Date:</b> ").append(deal.getDate()).append("</p>");
        sb.append("<p><b>Status:</b> ").append(deal.getStatus()).append("</p>");
        sb.append("</div>");

        if (deal.getBuyer() != null) {
            sb.append("<div class='card'>");
            sb.append("<h2>Buyer</h2>");
            sb.append("<p><b>Name:</b> ").append(deal.getBuyer().getName()).append("</p>");
            sb.append("<p><b>Contact:</b> ").append(deal.getBuyer().getContactInfo()).append("</p>");
            sb.append("<p><b>Deposit:</b> ").append(deal.getBuyer().getDeposit()).append("</p>");
            sb.append("</div>");
        }

        if (deal.getSeller() != null) {
            sb.append("<div class='card'>");
            sb.append("<h2>Seller</h2>");
            sb.append("<p><b>Name:</b> ").append(deal.getSeller().getName()).append("</p>");
            sb.append("<p><b>Contact:</b> ").append(deal.getSeller().getContactInfo()).append("</p>");
            sb.append("<p><b>Property:</b> ").append(deal.getSeller().getProperty()).append("</p>");
            sb.append("</div>");
        }

        if (deal.getAgent() != null) {
            sb.append("<div class='card'>");
            sb.append("<h2>Agent</h2>");
            sb.append("<p><b>Name:</b> ").append(deal.getAgent().getName()).append("</p>");
            sb.append("<p><b>Contact:</b> ").append(deal.getAgent().getContactInfo()).append("</p>");
            sb.append("<p><b>Agency:</b> ").append(deal.getAgent().getAgency()).append("</p>");
            sb.append("</div>");
        }

        sb.append("<a href='/deals' class='back-link'>&larr; Back to Listings</a>");
        sb.append("</body></html>");
        return sb.toString();
    }
}
