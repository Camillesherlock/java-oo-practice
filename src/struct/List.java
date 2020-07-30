package struct;

public class List {
	private String title;
	private String describe;
	private boolean isSuper;
	private int achievement;

	public List(String title, String describe, boolean isSuper, int achievement) {
		super();
		this.title = title;
		this.describe = describe;
		this.isSuper = isSuper;
		this.achievement = achievement;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}

	public int getAchievement() {
		return achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

}
