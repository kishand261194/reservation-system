import java.util.Date;

public class Appoinment {
	private Student student;
	private String question;
	private Date date;
	
    public Appoinment(Student student, String question, Date date) {
    	this.setStudent(student);
    	this.setQuestion(question);
    	this.setDate(date);
    }

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
