package com.tago.api.infra.fcm;

import com.tago.api.common.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FCMTokenUtil {

    private final RedisUtil redisUtil;
    private static final long expiredTime = 60 * 24 * 60 * 60L;  // 2개월

    public void save(String key, String token) {
        redisUtil.setDataExpire(key, token, expiredTime);
    }

    public String get(String key) {
        return redisUtil.getData(key);
    }

    public void delete(String key) {
        redisUtil.delete(key);
    }

    public Boolean hasKey(String key) {
        return redisUtil.hasKey(key);
    }
}
