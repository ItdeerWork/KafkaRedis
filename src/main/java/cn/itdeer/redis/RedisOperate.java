package cn.itdeer.redis;

import redis.clients.jedis.*;

import java.util.Iterator;
import java.util.Set;

/**
 * Directions:
 * PackageName: cn.itdeer.redis.
 * ProjectName: KafkaRedis.
 * Creator: itdeer.
 * CreationTime: 2018/10/25 14:55.
 */
public class RedisOperate {
    private Jedis jedis;
    private JedisPool jedisPool;

    public RedisOperate()
    {
        initialPool();
        jedis = jedisPool.getResource();
    }

    /**
     * 初始化非切片池
     */
    private void initialPool()
    {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config,"10.154.96.93",7002);
    }

    private void StringOperate() {
        int num =0;
        //System.out.println("清空库中所有数据："+jedis.flushDB());
        jedis.set("BBB","qqq");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();

            num ++;
            System.out.println(key + "  Value:" + jedis.get(key));
        }

        System.out.println(num);
    }

    public static void main(String[] args) {
        RedisOperate rt = new RedisOperate();
        rt.StringOperate();
    }
}
