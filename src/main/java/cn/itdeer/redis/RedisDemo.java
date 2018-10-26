package cn.itdeer.redis;

import cn.itdeer.mode1.RedisClient;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Directions:
 * PackageName: cn.itdeer.redis.
 * ProjectName: KafkaRedis.
 * Creator: itdeer.
 * CreationTime: 2018/10/26 10:03.
 */
public class RedisDemo {

    private static JedisCluster jedisCluster;

    static  {
        Set<HostAndPort> sets = new HashSet();

        sets.add(new HostAndPort("10.154.96.93",7001));
        sets.add(new HostAndPort("10.154.96.93",7002));
        sets.add(new HostAndPort("10.154.96.93",7003));

        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(500);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(500);

        jedisCluster = new JedisCluster(sets,5000,10,config);
    }

    public static void main(String[] args) {
//        System.out.println(jedisCluster.set("CCC","456"));
//        System.out.println(jedisCluster.get("CCC"));
//        System.out.println(jedisCluster.set("AAA","123"));
//        System.out.println(jedisCluster.get("AAA"));

        System.out.println(jedisCluster.get("BBB"));

        jedisCluster.flushAll();




    }
}
