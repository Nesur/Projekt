package manager;

import interfaces.AppointmentManagerInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Appointment;
import data.Instructor;
import data.Student;

import sql.connection.Connect;

public class AppointmentManager implements
		AppointmentManagerInterface<Appointment> {

	private Connect connect = new Connect();
	private String createAppointmentTableString = "CREATE TABLE Appointment(id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
			+ "Instructor varchar(30),Student varchar(30),Date varchar(15),Time varchar(15))";

	private Statement createAppointmentTableStatement;
	private PreparedStatement makeAppointmentStatement;
	private PreparedStatement deleteByIdStatement;
	private PreparedStatement deleteByStudentStatement;
	private PreparedStatement getAllAppointmentsStatement;

	public AppointmentManager() {
		try {
			connect.getConnection();

			createAppointmentTableStatement = connect.getConnection()
					.createStatement();

			ResultSet getAppointmentTable = connect.getConnection()
					.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (getAppointmentTable.next()) {
				if ("Appointment".equalsIgnoreCase(getAppointmentTable
						.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists) {
				createAppointmentTableStatement
						.executeUpdate(createAppointmentTableString);
			}
			makeAppointmentStatement = connect.getConnection()
					.prepareStatement(
							"INSERT INTO Appointment (Instructor,Student,Date,Time)"
									+ "VALUES (?,?,?, ?)");
			deleteByIdStatement = connect.getConnection().prepareStatement(
					"DELETE FROM Appointment WHERE id=?");
			deleteByStudentStatement = connect
					.getConnection()
					.prepareStatement("DELETE FROM Appointment WHERE Student=?");
			getAllAppointmentsStatement = connect
					.getConnection()
					.prepareStatement(
							"SELECT Instructor,Student,Date,Time FROM Appointment");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void makeAppointment(Appointment appointment) {
		try {
			makeAppointmentStatement.setString(1, appointment.getInstructor()
					.getName());
			makeAppointmentStatement.setString(2, appointment.getStudent()
					.getName());
			makeAppointmentStatement.setString(3, appointment.getDate());

			makeAppointmentStatement.setString(4, appointment.getTime());

			makeAppointmentStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(int id) {
		try {
			deleteByIdStatement.setInt(1, id);
			deleteByIdStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByStudent(String student) {
		try {
			deleteByStudentStatement.setString(1, student);
			deleteByStudentStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Appointment> getAll() {
		List<Appointment> result = new ArrayList<Appointment>();
		try {
			ResultSet rs = getAllAppointmentsStatement.executeQuery();
			while (rs.next()) {
				result.add(new Appointment(new Student(rs.getString("Name")),
						new Instructor(rs.getString("Name")), rs
								.getString("Date"), rs.getString("Time")));
				return result;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}
}
