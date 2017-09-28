package qq;

public class BlogInfo {
	private String blogid;	// 日志编号（需要这个属性访问日志）blogid
	private String title;	// 日志标题title
	private int couter;		// 计数器，记录访问次数
	
	// 构造函数
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
	
	// 计数器加1
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

