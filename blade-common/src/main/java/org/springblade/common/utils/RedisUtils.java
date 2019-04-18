/**
 * Copyright 2018 首页 http://www.finepetro.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springblade.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.*;
import redis.clients.jedis.params.sortedset.ZAddParams;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * 这里拓展了两个接口，如果以后使用接着拓展
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-17 21:12
 */
@Service
public class RedisUtils {
    @Resource
    RedisTemplate redisTemplate;
    @Resource(name = "redisTemplate")
    ValueOperations<String, String> valueOperations;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;
    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOperations;
    @Resource(name = "redisTemplate")
    private SetOperations<String, Object> setOperations;
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, Object> zSetOperations;
    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }


    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }



    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }


    /**
     * 批量获取
     * @param keys
     * @param <T>
     * @return
     */
    public <T> List<T> mget(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }


    //----------------------------------------------------------


    //---------   String 类型 ------------
    /**
     * 获取类型
     * @param s
     * @return
     */
    public String type(String s) {
        return redisTemplate.type(s).name();
    }

    /**
     * 获取并设置
     * @param s
     * @param t
     * @return
     */
    public <T> T getSet(String s, T t) {
        return (T)redisTemplate.opsForValue().getAndSet(s,t);
    }

    /**
     * 原子性减
     * @param s
     * @param l
     * @return
     */
    public Long decrBy(String s, long l) {
        return redisTemplate.opsForValue().increment(s,(-1) * l);
    }

    public Long decr(String s) {
        return redisTemplate.opsForValue().increment(s,-1);
    }

    public Long incrBy(String s, long l) {
        return redisTemplate.opsForValue().increment(s, l);
    }

    public Double incrByFloat(String s, double v) {
        return redisTemplate.opsForValue().increment(s, v);
    }

    public Long incr(String s) {
        return redisTemplate.opsForValue().increment(s,-1);
    }


    //---------   hash 类型 ------------


    public void hset(String s, String s1, String s2) {
        redisTemplate.opsForHash().put(s,s1,s2);
    }

    public String hget(String s, String s1) {
        return (String) redisTemplate.opsForHash().get(s,s1);
    }

    public void hmset(String s, Map<Object, String> map) {
        redisTemplate.opsForHash().putAll(s,map);
    }

    public List<String> hmget(String s, String... strings) {
        List<String> fields = Arrays.<String>asList(strings);
        return redisTemplate.opsForHash().multiGet(s,fields);
    }

    public Map<String, String> hgetAll(String s) {
        return redisTemplate.opsForHash().entries(s);
    }


    //---------   list 类型 ------------


    /**
     * I'm not sure
     * @param s
     * @param strings
     * @return
     */
    public Long rpush(String s, String... strings) {
        return redisTemplate.opsForList().rightPush(s,strings);
    }

    public Long lpush(String s, String... strings) {
        return redisTemplate.opsForList().leftPush(s,strings);
    }

    public Long llen(String s) {
        return redisTemplate.opsForList().size(s);
    }

    public List<String> lrange(String s, long l, long l1) {
        return redisTemplate.opsForList().range(s,l,l1);
    }

    public void ltrim(String s, long l, long l1) {
        redisTemplate.opsForList().trim(s,l,l1);
    }

    /**
     * 一般就是stirng
     * @param s
     * @param l
     * @param <T>
     * @return
     */
    public <T> T lindex(String s, long l) {
        return (T)redisTemplate.opsForList().index(s,l);
    }

    public Long lrem(String s, long l, String s1) {
        return null;
    }

    public String lpop(String s) {
        return null;
    }

    public String rpop(String s) {
        return null;
    }



    //---------   set 类型 ------------


    public Long sadd(String s, String... strings) {
        return null;
    }

    public Set<String> smembers(String s) {
        return null;
    }

    public Long srem(String s, String... strings) {
        return null;
    }

    public Long scard(String s) {
        return null;
    }

    public Boolean sismember(String s, String s1) {
        return null;
    }

    public String srandmember(String s) {
        return null;
    }

    public List<String> srandmember(String s, int i) {
        return null;
    }

    //---------   sortedset 类型 ------------


    public Long zadd(String s, double v, String s1) {
        return null;
    }

    public Long zadd(String s, double v, String s1, ZAddParams zAddParams) {
        return null;
    }

    public Long zadd(String s, Map<String, Double> map) {
        return null;
    }

    public Long zadd(String s, Map<String, Double> map, ZAddParams zAddParams) {
        return null;
    }

    public Set<String> zrange(String s, long l, long l1) {
        return null;
    }

    public Long zrem(String s, String... strings) {
        return null;
    }

    public Set<String> zrevrange(String s, long l, long l1) {
        return null;
    }

    public Set<Tuple> zrangeWithScores(String s, long l, long l1) {
        return null;
    }

    public Set<Tuple> zrevrangeWithScores(String s, long l, long l1) {
        return null;
    }

    public Long zcard(String s) {
        return null;
    }

    public Double zscore(String s, String s1) {
        return null;
    }

    public List<String> sort(String s) {
        return null;
    }


    public Set<String> zrangeByScore(String s, double v, double v1) {
        return null;
    }

    public Set<String> zrangeByScore(String s, String s1, String s2) {
        return null;
    }

    public Set<String> zrevrangeByScore(String s, double v, double v1) {
        return null;
    }

    public Set<String> zrangeByScore(String s, double v, double v1, int i, int i1) {
        return null;
    }

    public Set<String> zrevrangeByScore(String s, String s1, String s2) {
        return null;
    }

    public Set<String> zrangeByScore(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    public Set<String> zrevrangeByScore(String s, double v, double v1, int i, int i1) {
        return null;
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1) {
        return null;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1) {
        return null;
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, double v, double v1, int i, int i1) {
        return null;
    }

    public Set<String> zrevrangeByScore(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, String s1, String s2) {
        return null;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, String s1, String s2) {
        return null;
    }

    public Set<Tuple> zrangeByScoreWithScores(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, double v, double v1, int i, int i1) {
        return null;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(String s, String s1, String s2, int i, int i1) {
        return null;
    }

    public Long zremrangeByRank(String s, long l, long l1) {
        return null;
    }

    public Long zremrangeByScore(String s, double v, double v1) {
        return null;
    }

    public Long zremrangeByScore(String s, String s1, String s2) {
        return null;
    }






}
