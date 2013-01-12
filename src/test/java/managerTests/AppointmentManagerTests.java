package managerTests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import manager.AppointmentManager;

import org.junit.Test;

import data.Appointment;
import data.Instructor;
import data.Student;

public class AppointmentManagerTests {

	private AppointmentManager am = new AppointmentManager();

	@Test
	public void add_new_appointment_Test() {
		am.deleteAll();
		List<Appointment> apps = am.getAll();
		Appointment a = new Appointment(new Instructor("Koles"), new Student("drugi koles"), "18 listopad", "20:00");
		am.makeAppointment(a);
		assertEquals(a, apps.get(0));

	}

	@Test
	public void delete_by_id() {
		List<Appointment> apps = am.getAll();

		Appointment a = new Appointment(new Instructor("Koles"), new Student("drugi koles"), "18 listopad", "20:00");
		am.makeAppointment(a);
		am.deleteById((int) a.getId());

	}

	@Test
	public void delete_by_student() {
		List<Appointment> apps = am.getAll();

		Appointment a = new Appointment(new Instructor("Koles"), new Student("drugi koles"), "18 listopad", "20:00");
		am.makeAppointment(a);
		am.deleteByStudent(a.getStudent().getName());
		
	}

	@Test
	public void delete_All_appointments() {

		am.deleteAll();
		List<Appointment> apps = am.getAll();
		assertNull("Nie usunelo, bo sa elementy", apps);
	}

	@Test
	public void get_all_appointments_Test() {
		List<Appointment> apps = am.getAll();
		assertNotNull("lista jest pusta", apps);
		assertTrue("Nie ma elementow", apps.size() > 0);
	}

}
