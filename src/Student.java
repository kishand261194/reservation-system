
public class Student {
	private String name;
	private String email;
	private boolean isBanned;
	
    public Student(String name, String email) {
        this.setName(name);
        this.setEmail(email);
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}


}
