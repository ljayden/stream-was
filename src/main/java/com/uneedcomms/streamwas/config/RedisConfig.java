//package com.uneedcomms.streamwas.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.cache.CacheProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//public class RedisConfig {
//
//    Logger logger = LoggerFactory.getLogger(RedisConfig.class);
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//
//
//    @Bean
//    public JedisPoolConfig jedisPoolConfig() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        JedisPool pool = new JedisPool(jedisPoolConfig, "ec2-54-180-120-133.ap-northeast-2.compute.amazonaws.com", 6379, 1000, "60976712c7f60fa538bcacf402529b41589f589de3058713b4759062cc0110ea");
//        return jedisPoolConfig;
//    }
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
//        return jedisConnectionFactory;
//    }
//
//
////    @Bean JedisConnectionFactory jedisConnectionFactory()
//
//
//    @Bean(name="redisTemplate")
//    public RedisTemplate redisTemplateConfig(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate redisTemplate = new RedisTemplate();
//
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        return redisTemplate;
//    }
//
//
//}