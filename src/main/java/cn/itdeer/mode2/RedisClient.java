package cn.itdeer.mode2;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Directions:
 * PackageName: cn.itdeer.
 * ProjectName: KafkaRedis.
 * Creator: itdeer.
 * CreationTime: 2018/10/25 12:46.
 */
public class RedisClient extends Thread{

    private JedisClusterPipeline jcp;
    private JedisCluster jc;
    private int num = 0;
    private int loop_num = 0;

    public RedisClient() {
        Set<HostAndPort> sets = new HashSet();

        sets.add(new HostAndPort("10.154.96.93",7001));
        sets.add(new HostAndPort("10.154.96.93",7002));
        sets.add(new HostAndPort("10.154.96.93",7003));

        jc = new JedisCluster(sets);

        this.jcp = JedisClusterPipeline.pipelined(jc);
        jcp.refreshCluster();
    }

    @Override
    public void run() {

        try {
            long startTime = System.currentTimeMillis();
            /*while (num < 10){
                String key = "U70HFC60CP_ITDEER" + num;
                String value = getRandomValue() + num + "";
                jcp.set(key,value);
                num ++;
            }
            jcp.sync();*/
            while (loop_num < 10){
                String key = "U70HFC60CP_ITDEER" + num;
                jcp.get(key);
                loop_num ++;
            }
            jcp.get("AAA");
            jcp.get("BBB");
            jcp.get("AAA");
            jcp.get("BBB");
            jcp.get("CCC");

            List<Object> batchResult = jcp.syncAndReturnAll();
            long endTime = System.currentTimeMillis();

            double totle_time = (double)(endTime-startTime)/1000;


            System.out.println("结果集："+batchResult.size());

            for (int i =0;i<batchResult.size();i++){
                System.out.println(i);
                System.out.println(batchResult.get(i));
                // System.out.println("数据："+oo.toString());

            }
            batchResult.clear();
            System.out.println("总用时：" + totle_time + "秒" + " 总数据为：" + 1000000 + "条 速率为：" + 1000000/totle_time + "条/秒");
        }finally {
            jcp.close();
            try {
                jc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getRandomValue(){
        return (int)(Math.random() * 1000);
    }

}
