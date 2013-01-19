package interfaces;

import java.util.List;

import data.Appointment;

public interface AppointmentManagerInterface<TEntity> {

	public void deleteById(int id);
	
	public void deleteAll();

	public List<Appointment> getAll();

	void makeAppointment(TEntity obj);
}
