package qq;

public class BlogInfo {
	private String blogid;	// ��־��ţ���Ҫ������Է�����־��blogid
	private String title;	// ��־����title
	private int couter;		// ����������¼���ʴ���
	
	// ���캯��
	public BlogInfo(String bid,String tit,int couter)
	{
		this.setBlogid(bid);
		this.setTitle(tit);
		this.setCouter(couter);
	}
	
	/**
	 * @param blogid the blogid to set
	 */
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	/**
	 * @return the blogid
	 */
	public String getBlogid() {
		return blogid;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param couter the couter to set
	 */
	public void setCouter(int couter) {
		this.couter = couter;
	}
	/**
	 * @return the couter
	 */
	public int getCouter() {
		return couter;
	}
	
	// ��������1
	public void addCouter()
	{
		++couter;
	}
	
	public String[] getInfo()
	{
		String[] result = {this.blogid,this.title,String.valueOf(this.couter)};
		return result;
	}
	
}

