package com.nish.springannotation.atconfiguration;

public class TradeFeedService {

    private String productName;
    private String feedDate;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setFeedDate(String feedDate) {
        this.feedDate = feedDate;
    }

    public void loadTradeFeed() {
        String result = "TradeFeed for productName - %s is been loaded for the date of - %s ";
        System.out.printf(result, productName, feedDate);
    }
}
