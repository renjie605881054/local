package qq;

import java.net.*;
import java.util.*;
import java.io.*;
public class BlogHeat {
	private Socket client; // 客户端套接字
	private ArrayList<BlogInfo> blogs;
	
	public ArrayList<BlogInfo> getBlogs() {
		return blogs;
	}
	@SuppressWarnings("deprecation")
	public boolean getBlogsInfo(String uin)
	{
		blogs = new ArrayList<BlogInfo>();// 初始化blogs
		
		try {
			client = new Socket("qzone.qq.com",80);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("服务器连接失败");
			return false;
		}
		InputStream in = null;
		try {
			in = client.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("服务器连接失败");
			return false;
		}
		OutputStream out = null;
		try {
			out = client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("服务器连接失败");
			return false;
		}
		
		DataInputStream dis = new DataInputStream(in);
		DataOutputStream dos = new DataOutputStream(out);
		
		StringBuffer sb=new StringBuffer();
		sb.append("GET /cgi-bin/blognew/blog_get_titlelist?uin="+uin+"&vuin=0&property=GoRE&category=&numperpage=50&sorttype=0&arch=0&pos=0&direct=1&r=0.8082843897968415 HTTP/1.1/r/n");//注意/r/n为回车换行
		sb.append("Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*/r/n");
		sb.append("User-Agent: Microsoft URL Control - 6.00.8169/r/n");
		sb.append("Host: b.qzone.qq.com/r/n");
		sb.append("Cache-Control: no-cache/r/n");
		
		sb.append("/r/n");
		
		try {
			dos.write(sb.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("服务器连接失败");
			return false;
		}
		String line;
		boolean first = true;
		String blogid = null;//开始把这两个变量放到while里面，是错的
		String title = null;//刚开始还没想到，出错后才发现 blogid为null
		try {
			while((line = dis.readLine())!=null)
			{
				line = new String(line.getBytes("8859_1"),"GB2312");
				
				if(line.contains("blogid"))
				{
					if(!first)
					{
						blogid = line.substring(10, line.length()-1);
						//System.out.println(blogid);
					}else
					{
						blogid = line.substring(23, line.length()-1);
						//System.out.println(blogid);
						first = false;
					}
				}
				if(line.contains("title"))
				{
					title = line.substring(9, line.length()-3);
					//System.out.println(title);
					BlogInfo blog = new BlogInfo(blogid,title,0);
					blogs.add(blog);
				}
				// 下面这样做是错的，会做很多重复的事，添加重复的类到blogs里面
				// 原因很简单
				/*if((blogid != null)&&(title!=null))
				{
					BlogInfo blog = new BlogInfo(blogid,title,0);
					blogs.add(blog);
					System.out.println("add!");
				}*/
				
				if(line.indexOf("/r/n/r/n")!=-1)
					break;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("服务器连接失败");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("服务器连接失败");
			return false;
		}
		if(blogs.isEmpty())
			return false;
		else
			return true;
	}
	
	// 获取特定某一篇日志的数据，即是增加此日志的访问量+1
	@SuppressWarnings("deprecation")
	public boolean addHeat(String uin,String blogid)
	{	
		boolean result = false;
		try {
			client = new Socket("b.qzone.qq.com",80);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		
		// 相关输入输出流
		InputStream in = null;
		try {
			in = client.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		OutputStream out = null;
		try {
			out = client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		DataInputStream dis = new DataInputStream(in);
		DataOutputStream dos = new DataOutputStream(out);
		
		// 填写报文数据
		StringBuffer sb=new StringBuffer();
		sb.append("GET /cgi-bin/blognew/blog_get_data?uin="+uin+"&numperpage=30&blogid="+blogid+"&arch=0&pos=0&direct=1&sx=991742 HTTP/1.1/r/n");//注意/r/n为回车换行
		sb.append("Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*/r/n");
		sb.append("User-Agent: Microsoft URL Control - 6.00.8169/r/n");
		sb.append("Host: b.qzone.qq.com/r/n");
		sb.append("Cache-Control: no-cache/r/n");
		sb.append("/r/n");
		
		try {
			dos.write(sb.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		String line = null;
		// 接收数据
		try {
			while((line = dis.readLine())!=null)
			{	
				/**
				 * 判断结果
				 * 之前只是判断是否收到“HTTP/1.1 200 OK”；
				 * 后来发现有个问题，
				 * 就是当服务器忙了的时候，服务器会发送系统忙的包过来
				 * 所以加上line.contains("{/"error/":{")的判断
				 * 进一步保证数据的准确性。
				 */
				if(line.contains("HTTP/1.1 200 OK"))
					result = true;
				if(line.contains("{error:{"))
				{
					result = false;
				}
				if(line.indexOf("/r/n/r/n")!=-1)
					break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		
		try {
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		try {
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("服务器连接失败");
			return false;
		}
		
		return result;
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		BlogHeat bh = new BlogHeat();
		String qq = "605881054";
		Scanner stdin = new Scanner(System.in); 
		
		if(args.length == 1)
		{
			qq = args[0];
		}else
			qq = "743355276";
		
		System.out.println("---------------------------------------------------");
		System.out.println("---             QQ空间日志刷阅读量");
		System.out.println("---             正常速度一秒4点 ");
		System.out.println("---------------------------------------------------");
		System.out.println("---请注意：使用本程序会增加QQ空间服务器负荷，but......");
		System.out.println("---------------------------------------------------");
		System.out.println("---任何问题可联系qq743355276 scwuhuiwen@vip.qq.com");
		System.out.print("输入QQ号：>");
		qq = stdin.next();
		if(bh.getBlogsInfo(qq))
			System.out.println("获取日志列表成功");
		else
			System.out.println("获取日志列表失败（或者用户没有日志）");
		
		/*for(int i=0; i<bh.blogs.size(); i++)
		{
			System.out.println(bh.blogs.get(i).getBlogid()+"/t"+bh.blogs.get(i).getTitle()+"/t"+bh.blogs.get(i).getCouter());
		}*/
		
		while(!bh.blogs.isEmpty())
		{	
			for(int i=0; i<bh.blogs.size(); i++)
			{
				if(bh.addHeat(qq, bh.blogs.get(i).getBlogid()))
				{
					bh.blogs.get(i).addCouter();
					System.out.println("<<"+bh.blogs.get(i).getTitle()+">>/n/t/t/t增加第--"+bh.blogs.get(i).getCouter()+ "--点访问量");	
				}else
				{
					System.out.println("<<"+bh.blogs.get(i).getTitle()+">>/n/t/t/t--失败（应该是系统忙了。）--");	
				}
				Thread.sleep(250); // 休息0.25秒，太快会不会有问题？服务器负担太大？
			}
		}
		
		System.out.println("任意键提出>");
		System.in.read();
	}
}

