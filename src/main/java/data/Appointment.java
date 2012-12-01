package data;

public class Appointment {

	private long id;
	private Student student;
	private Instructor instructor;
	private String date;
	private String time;

	public Appointment(Instructor instructor, Student student, String date,
			String time) {
		this.student = student;
		this.instructor = instructor;
		this.date = date;
		this.time = time;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
