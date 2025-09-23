package model;

public class Category {
	private int cateid;
	private int id;
	private String catename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String icon;
	public int getCateid() {
		return cateid;
	}
	public void setCateid(int cateid) {
		this.cateid = cateid;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Category(int id,int cateid, String catename, String icon) {
		super();
		this.id = id;
		this.cateid = cateid;
		this.catename = catename;
		this.icon = icon;
	}
	public Category() {
		super();
	}
	@Override
	public String toString() {
		return "Category [cateid=" + cateid + ", catename=" + catename + ", icon=" + icon + "]";
	}
	
}
