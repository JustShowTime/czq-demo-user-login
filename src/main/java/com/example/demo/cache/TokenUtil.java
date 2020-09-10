package com.example.demo.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class TokenUtil {

    private static Cache<String, String> TOKEN_CACHE = CacheBuilder.newBuilder() //
            .maximumSize(100) // 
            .expireAfterAccess(Integer.valueOf(1), TimeUnit.MINUTES) // 
            .concurrencyLevel(10) // 设置并发级别为10
            .build();

    private static Cache<String, String> LOCK_CACHE = CacheBuilder.newBuilder() //
            .maximumSize(100) // 
            .expireAfterAccess(Integer.valueOf(1), TimeUnit.MINUTES) // 
            .concurrencyLevel(10) // 设置并发级别为10
            .build();

    public static void put(String key, String value) {
        TOKEN_CACHE.put(key, value);
    }

    public static String get(String key) {
        return TOKEN_CACHE.getIfPresent(key);
    }

    public static void delete(String key) {
        TOKEN_CACHE.invalidate(key);
    }

    public static void put1(String key, String value) {
        LOCK_CACHE.put(key, value);
    }

    public static String get1(String key) {
        return LOCK_CACHE.getIfPresent(key);
    }

    public static void delete1(String key) {
        LOCK_CACHE.invalidate(key);
    }

}
