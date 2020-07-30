package struct;

public class User {
	private long account;
	private String name;
	private String password;
	private boolean isAdmin;

	public User(long account, String name, String password, boolean isAdmin) {
		super();
		this.account = account;
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public long getAccount() {
		return account;
	}

	public void setAccount(long account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
