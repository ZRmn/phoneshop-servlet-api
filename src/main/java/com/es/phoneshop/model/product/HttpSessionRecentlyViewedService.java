package com.es.phoneshop.model.product;

import javax.servlet.http.HttpSession;

public class HttpSessionRecentlyViewedService implements RecentlyViewedService {

    private static RecentlyViewedService instance;
    private final int MAX_ON_PAGE = 5;
    private final String RECENTLY_VIEWED_ATTRIBUTE = "recentlyViewed";

    private HttpSessionRecentlyViewedService() {

    }

    public static synchronized RecentlyViewedService getInstance() {
        if (instance == null) {
            instance = new HttpSessionRecentlyViewedService();
        }

        return instance;
    }

    @Override
    public RecentlyViewed getRecentlyViewed(HttpSession session) {
        RecentlyViewed recentlyViewed = (RecentlyViewed) session.getAttribute(RECENTLY_VIEWED_ATTRIBUTE);

        if (recentlyViewed == null) {
            recentlyViewed = new RecentlyViewed();
            session.setAttribute(RECENTLY_VIEWED_ATTRIBUTE, recentlyViewed);
        }

        return recentlyViewed;
    }

    @Override
    public void add(HttpSession session, Product product) {
        RecentlyViewed recentlyViewed = getRecentlyViewed(session);

        boolean noneMatch = recentlyViewed.getProducts().stream()
                .noneMatch(p -> p.getId().equals(product.getId()));

        if (noneMatch) {
            recentlyViewed.getProducts().addFirst(product);
            if (recentlyViewed.getProducts().size() > MAX_ON_PAGE) {
                recentlyViewed.getProducts().removeLast();
            }
        }
    }
}
