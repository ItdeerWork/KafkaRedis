package cn.itdeer.mode1;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Directions:
 * PackageName: cn.itdeer.
 * ProjectName: KafkaRedis.
 * Creator: itdeer.
 * CreationTime: 2018/10/25 12:46.
 */
public class RedisClient extends Thread{

    private static JedisCluster jedisCluster;
    private int num = 0;

    public RedisClient() {
        Set<HostAndPort> sets = new HashSet();

        sets.add(new HostAndPort("10.154.96.93",7001));
        sets.add(new HostAndPort("10.154.96.93",7002));
        sets.add(new HostAndPort("10.154.96.93",7003));

        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(500);
        config.setMaxIdle(10);
        config.setMaxWaitMillis(500);

        this.jedisCluster = new JedisCluster(sets,5000,10,config);
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while (num < 1000000){
            String key = getRandomValue() + "U70HFC60CP_ITDEER" + num;
            jedisCluster.set(key,getRandomValue() + num + "");
            num ++;
        }

        long endTime = System.currentTimeMillis();

        double totle_time = (double)(endTime-startTime)/1000;
        System.out.println("总用时：" + totle_time + "秒" + " 总数据为：" + num + "条 速率为：" + num/totle_time + "条/秒");
    }

    private static int getRandomValue(){
        return (int)(Math.random() * 1000);
    }

}
