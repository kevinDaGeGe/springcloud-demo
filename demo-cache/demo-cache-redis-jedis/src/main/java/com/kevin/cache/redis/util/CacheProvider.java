package com.kevin.cache.redis.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.google.gson.Gson;

/**
 * 缓存提供类
 */
public class CacheProvider {

	@Autowired
	private StringRedisTemplate template;

    public static <T> boolean set(String key, T value) {
        Gson gson = new Gson();
        return set(key, gson.toJson(value));
    }

    public boolean set(String key, String value, long validTime) {
        boolean result = template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = template.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                connection.expire(serializer.serialize(key), validTime);
                return true;
            }
        });
        return result;
    }

    public <T> T get(String key, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(get(key), clazz);
    }

    public String get(String key) {
        String result = template.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = template.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    public boolean del(String key) {
        return template.delete(key);
    }
}