package com.swpu.constructionsitesafety.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class AccessLimiter {
    private final ConcurrentHashMap<Integer, Long> accessMap = new ConcurrentHashMap<>();

    public void incrementAccess(int userId) {
        accessMap.compute(userId, (key, value) -> {
            long currentTime = System.currentTimeMillis();
            if (value == null || (currentTime - value) > TimeUnit.HOURS.toMillis(2)) {
                return currentTime; // 允许访问，重置时间
            } else {
                return value; // 不允许访问，保持原时间
            }
        });
    }

    public boolean isAccessAllowed(int userId) {
        Long lastAccessTime = accessMap.get(userId);
        long currentTime = System.currentTimeMillis();
        if (lastAccessTime == null || (currentTime - lastAccessTime) > TimeUnit.HOURS.toMillis(2)) {
            return true; // 允许访问
        }
        return false; // 不允许访问
    }
}