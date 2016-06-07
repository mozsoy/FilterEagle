package com.master.metehan.filtereagle;

import java.util.HashMap;

/**
 * Created by Metehan on 5/29/2016.
 */
public class URLCache {

    private static URLCache urlCacheInstance = null;
    private static int CACHE_SIZE = 1000;
    private HashMap<String, Integer> urlMap = new HashMap<>();

    public static URLCache getInstance() {
        if (urlCacheInstance == null)
            urlCacheInstance = new URLCache();
        return urlCacheInstance;
    }

    public  void clearCache() {
        if(this.urlMap.isEmpty()) {
            this.urlMap = new HashMap<>();
        } else {
            this.urlMap.clear();
        }
    }

    public  HashMap<String, Integer> getMap() {
        return this.urlMap;
    }

}
