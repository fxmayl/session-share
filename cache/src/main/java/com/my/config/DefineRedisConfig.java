/*
 * Copyright: 浙报传媒控股集团有限公司版权所有
 * Copyright (c) 2019, Copyright Zhejiang Daily Media Holding Group Co.,Ltd. All Rights Reserved.
 */

package com.my.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;
import java.time.Duration;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:TODO<BR>
 *
 * @author 方小明
 * @version 1.0.0
 * @department 产品研发中心
 * @date 2019年05月19日 14:04
 */
@Configuration
@Slf4j
public class DefineRedisConfig {
    @Bean
    public <K, V> RedisTemplate<K, V> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<K, V> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Primary
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration =
            RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration =
            redisCacheConfiguration.entryTtl(Duration.ofSeconds(30L)).disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
        log.info("use prefix : {}", redisCacheConfiguration.usePrefix());
        return RedisCacheManager
            .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
            .cacheDefaults(redisCacheConfiguration).build();
    }

    private <K> RedisSerializer<K> keySerializer() {
        return (RedisSerializer<K>)new StringRedisSerializer(Charset.forName("UTF-8"));
    }

    private <V> RedisSerializer<V> valueSerializer() {
        return (RedisSerializer<V>)new GenericJackson2JsonRedisSerializer();
    }
}
