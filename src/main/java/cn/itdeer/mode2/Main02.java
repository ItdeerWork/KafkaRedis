package cn.itdeer.mode2;

/**
 * Directions:
 * PackageName: cn.itdeer.
 * ProjectName: KafkaRedis.
 * Creator: itdeer.
 * CreationTime: 2018/10/25 9:17.
 */
public class Main02 {
    public static void main(String[] args) {
        for(int i=0;i<1;i++){
            Thread thread = new RedisClient();
            thread.start();
        }
    }
}
