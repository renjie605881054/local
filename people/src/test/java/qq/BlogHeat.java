package qq;

import java.net.*;
import java.util.*;
import java.io.*;
public class BlogHeat {
	private Socket client; // �ͻ����׽���
	private ArrayList<BlogInfo> blogs;
	
	public ArrayList<BlogInfo> getBlogs() {
		return blogs;
	}
	@SuppressWarnings("deprecation")
	public boolean getBlogsInfo(String uin)
	{
		blogs = new ArrayList<BlogInfo>();// ��ʼ��blogs
		
		try {
			client = new Socket("qzone.qq.com",80);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("����������ʧ��");
			return false;
		}
		InputStream in = null;
		try {
			in = client.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("����������ʧ��");
			return false;
		}
		OutputStream out = null;
		try {
			out = client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("����������ʧ��");
			return false;
		}
		
		DataInputStream dis = new DataInputStream(in);
		DataOutputStream dos = new DataOutputStream(out);
		
		StringBuffer sb=new StringBuffer();
		sb.append("GET /cgi-bin/blognew/blog_get_titlelist?uin="+uin+"&vuin=0&property=GoRE&category=&numperpage=50&sorttype=0&arch=0&pos=0&direct=1&r=0.8082843897968415 HTTP/1.1/r/n");//ע��/r/nΪ�س�����
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
			System.out.println("����������ʧ��");
			return false;
		}
		String line;
		boolean first = true;
		String blogid = null;//��ʼ�������������ŵ�while���棬�Ǵ��
		String title = null;//�տ�ʼ��û�뵽�������ŷ��� blogidΪnull
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
				// �����������Ǵ�ģ������ܶ��ظ����£�����ظ����ൽblogs����
				// ԭ��ܼ�
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
			System.out.println("����������ʧ��");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("����������ʧ��");
			return false;
		}
		if(blogs.isEmpty())
			return false;
		else
			return true;
	}
	
	// ��ȡ�ض�ĳһƪ��־�����ݣ��������Ӵ���־�ķ�����+1
	@SuppressWarnings("deprecation")
	public boolean addHeat(String uin,String blogid)
	{	
		boolean result = false;
		try {
			client = new Socket("b.qzone.qq.com",80);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		}
		
		// ������������
		InputStream in = null;
		try {
			in = client.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		}
		OutputStream out = null;
		try {
			out = client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		}
		DataInputStream dis = new DataInputStream(in);
		DataOutputStream dos = new DataOutputStream(out);
		
		// ��д��������
		StringBuffer sb=new StringBuffer();
		sb.append("GET /cgi-bin/blognew/blog_get_data?uin="+uin+"&numperpage=30&blogid="+blogid+"&arch=0&pos=0&direct=1&sx=991742 HTTP/1.1/r/n");//ע��/r/nΪ�س�����
		sb.append("Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*/r/n");
		sb.append("User-Agent: Microsoft URL Control - 6.00.8169/r/n");
		sb.append("Host: b.qzone.qq.com/r/n");
		sb.append("Cache-Control: no-cache/r/n");
		sb.append("/r/n");
		
		try {
			dos.write(sb.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		}
		String line = null;
		// ��������
		try {
			while((line = dis.readLine())!=null)
			{	
				/**
				 * �жϽ��
				 * ֮ǰֻ���ж��Ƿ��յ���HTTP/1.1 200 OK����
				 * ���������и����⣬
				 * ���ǵ�������æ�˵�ʱ�򣬷������ᷢ��ϵͳæ�İ�����
				 * ���Լ���line.contains("{/"error/":{")���ж�
				 * ��һ����֤���ݵ�׼ȷ�ԡ�
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
			System.out.println("����������ʧ��");
			return false;
		}
		
		try {
			dis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		}
		try {
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
			return false;
		}
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����������ʧ��");
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
		System.out.println("---             QQ�ռ���־ˢ�Ķ���");
		System.out.println("---             �����ٶ�һ��4�� ");
		System.out.println("---------------------------------------------------");
		System.out.println("---��ע�⣺ʹ�ñ����������QQ�ռ���������ɣ�but......");
		System.out.println("---------------------------------------------------");
		System.out.println("---�κ��������ϵqq743355276 scwuhuiwen@vip.qq.com");
		System.out.print("����QQ�ţ�>");
		qq = stdin.next();
		if(bh.getBlogsInfo(qq))
			System.out.println("��ȡ��־�б�ɹ�");
		else
			System.out.println("��ȡ��־�б�ʧ�ܣ������û�û����־��");
		
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
					System.out.println("<<"+bh.blogs.get(i).getTitle()+">>/n/t/t/t���ӵ�--"+bh.blogs.get(i).getCouter()+ "--�������");	
				}else
				{
					System.out.println("<<"+bh.blogs.get(i).getTitle()+">>/n/t/t/t--ʧ�ܣ�Ӧ����ϵͳæ�ˡ���--");	
				}
				Thread.sleep(250); // ��Ϣ0.25�룬̫��᲻�������⣿����������̫��
			}
		}
		
		System.out.println("��������>");
		System.in.read();
	}
}

