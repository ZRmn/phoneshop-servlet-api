package com.es.phoneshop.model.product;

import javax.servlet.http.HttpSession;

public interface RecentlyViewedService {

    RecentlyViewed getRecentlyViewed(HttpSession session);

    void add(HttpSession session, Product product);
}
