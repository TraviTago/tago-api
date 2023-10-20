package com.tago.api.infra.fcm;

import com.tago.api.common.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FCMTokenUtil {

    private final RedisUtil redisUtil;
    private static final String REDIS_KEY_TYPE = "fcm-token::";
    private static final long expiredTime = 60 * 24 * 60 * 60L;  // 2개월

    public void save(String key, String token) {
        redisUtil.setDataExpire(REDIS_KEY_TYPE + key, token, expiredTime);
    }

    public String get(String key) {
        return redisUtil.getData(REDIS_KEY_TYPE + key);
    }

    public void delete(String key) {
        redisUtil.delete(REDIS_KEY_TYPE + key);
    }

    public Boolean hasKey(String key) {
        return redisUtil.hasKey(REDIS_KEY_TYPE + key);
    }
}
