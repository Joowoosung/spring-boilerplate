package com.jws.settings.config.database;

import java.time.Duration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  @Value("${spring.redis.host}")
  private String host;
  @Value("${spring.redis.port}")
  private int port;
  @Value("${spring.redis.password}")
  private String password;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    LettuceConnectionFactory factory = new LettuceConnectionFactory();
    factory.setHostName(host);
    factory.setPort(port);
    if (!StringUtils.isEmpty(password)) factory.setPassword(password);
    return factory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    return redisTemplate;
  }

  private CacheManager getCacheManager(String prefix, Duration duration) {
    RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory());
    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
        .prefixKeysWith(prefix).entryTtl(duration);
    builder.cacheDefaults(configuration);
    return builder.build();
  }

  @Bean
  @Override
  public CacheManager cacheManager() {
    return this.getCacheManager("jws_cache_1d:", Duration.ofDays(1L));
  }

  @Bean
  public CacheManager hour1CacheMgr() {
    return this.getCacheManager("jws_cache_1H:", Duration.ofHours(1));
  }

  @Bean
  public CacheManager minute30CacheMgr() {
    return this.getCacheManager("jws_cache_30m:", Duration.ofMinutes(30));
  }

  @Bean
  public CacheManager minute10CacheMgr() {
    return this.getCacheManager("jws_cache_10m:", Duration.ofMinutes(10));
  }

  @Bean
  public CacheManager minute5CacheMgr() {
    return this.getCacheManager("jws_cache_5m:", Duration.ofMinutes(5));
  }
}
