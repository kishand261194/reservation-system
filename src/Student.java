
public class Student {
	private String name;
	private String email;
	
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


}
