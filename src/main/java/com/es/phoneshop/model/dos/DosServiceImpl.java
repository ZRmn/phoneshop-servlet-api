package com.es.phoneshop.model.dos;

import java.util.HashMap;
import java.util.Map;

public class DosServiceImpl implements DosService {

    private static DosServiceImpl instance;

    private Map<String, Integer> numberOfRequestsByIp;
    private final int REQUESTS_LIMIT = 20;
    private final int MINUTE = 60000;
    private long refreshTime;

    private DosServiceImpl() {
        numberOfRequestsByIp = new HashMap<>();
        refreshTime = System.currentTimeMillis();
    }

    public static synchronized DosServiceImpl getInstance() {
        if (instance == null) {
            instance = new DosServiceImpl();
        }

        return instance;
    }

    @Override
    public boolean isAllowed(String ip) {
        refresh();

        Integer numberOfRequests = numberOfRequestsByIp.get(ip);

        if (numberOfRequests == null) {
            numberOfRequests = 1;
        }

        numberOfRequests++;
        numberOfRequestsByIp.put(ip, numberOfRequests);

        return numberOfRequests < REQUESTS_LIMIT;
    }

    private void refresh() {
        long currentTime = System.currentTimeMillis();
        long difference = currentTime - refreshTime;

        if (difference > MINUTE) {
            refreshTime = currentTime;
            numberOfRequestsByIp.clear();
        }
    }
}
