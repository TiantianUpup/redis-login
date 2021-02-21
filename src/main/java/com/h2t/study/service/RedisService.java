package com.h2t.study.service;

/**
 * RedisService
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2021/01/11 10:35
 */
public interface RedisService {
    void set(String key, String value);

    String get(String key);

    boolean delete(String key);

    Long getExpireTime(String key);
}
