import redis.clients.jedis.Jedis;


public class TestRedis {

	private static Jedis redis;
	public static void main(String[] args) {
		getConnection();
		
		data();
		
		close();
	}
	public static void getConnection(){
		redis = new Jedis("47.91.236.230", 6379);
		System.out.println("服务正在运行" + redis.ping());
		
	}
	public static void close(){
		redis.close();
	}
	
	public static void data(){
		redis.set("name", "xiaoming");
		System.out.println(redis.get("name"));
		
		redis.append("name", " is a student");
		System.out.println(redis.get("name"));
		
		redis.del("name");
		System.out.println(redis.get("name"));
		
		redis.mset("name","TT","age","20");
		System.out.println(redis.get("name") + "-" + redis.get("age"));
	}

}
