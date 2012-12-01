package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import manager.AppointmentManager;
import data.Appointment;
import data.Instructor;
import data.Student;

public class Main extends JFrame {
	// frame
	private static final long serialVersionUID = 1L;
	private static int width = 260;
	private static int height = 365;
	private static String title = "Umów siê na jazdê";
	private Container container;

	// text fields
	private JTextField studentName;
	private JTextField instructorName;
	private JFormattedTextField date;
	private JFormattedTextField time;
	// mask formater fields
	private MaskFormatter dateMask;
	private MaskFormatter timeMask;
	// text field label
	private JLabel setStudentName;
	private JLabel setInstructorName;
	private JLabel setDate;
	private JLabel setTime;
	// button make appointment
	private JButton makeAppointment;

	public Main() {
		initForm();
		setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		setSize(width, height);
		setTitle(title);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	// main
	public static void main(String[] args) {
		
		new Main();
		new MainMenu();

	}

	public void initForm() {
		container = getContentPane();
		try {
			dateMask = new MaskFormatter("##-##-####");
			timeMask = new MaskFormatter("od ##.## do ##.##");
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		// text fields
		studentName = new JTextField();
		instructorName = new JTextField();
		date = new JFormattedTextField(dateMask);
		time = new JFormattedTextField(timeMask);

		// text field labels
		setStudentName = new JLabel("Podaj imie i nazwisko");
		setStudentName.setFont(new Font("Georgia", 0, 15));
		setInstructorName = new JLabel("Podaj imiê instruktora");
		setInstructorName.setFont(new Font("Georgia", 0, 15));
		setDate = new JLabel("Podaj date");
		setDate.setFont(new Font("Georgia", 0, 15));
		setTime = new JLabel("Podaj godzinê");
		setTime.setFont(new Font("Georgia", 0, 15));

		// button make
		makeAppointment = new JButton("Dodaj");

		setComponentsSize();
		container.add(Box.createVerticalStrut(20));
		container.add(setStudentName);
		container.add(Box.createVerticalStrut(10));
		container.add(studentName);
		container.add(Box.createVerticalStrut(10));
		container.add(setInstructorName);
		container.add(Box.createVerticalStrut(10));
		container.add(instructorName);
		container.add(Box.createVerticalStrut(10));
		container.add(setDate);
		container.add(Box.createVerticalStrut(10));
		container.add(date);
		container.add(Box.createVerticalStrut(10));
		container.add(setTime);
		container.add(Box.createVerticalStrut(10));
		container.add(time);
		container.add(Box.createVerticalStrut(10));
		container.add(Box.createVerticalStrut(20));
		container.add(makeAppointment);
		makeAppointment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AppointmentManager am = new AppointmentManager();

				if (e.getSource() == makeAppointment) {

					Appointment a = new Appointment(new Instructor(
							instructorName.getText()), new Student(studentName
							.getText()), date.getText(), time.getText());
					am.makeAppointment(a);
					JOptionPane.showMessageDialog(null, "Pomyœlnie dodano",
							"Potwierdzenie", 1);
					studentName.setText("");
					instructorName.setText("");
					date.setText("");
					time.setText("");

				}
			}
		});

	}

	public void setComponentsSize() {
		studentName.setMaximumSize(new Dimension(width + 100, studentName
				.getPreferredSize().height + 10));
		instructorName.setMaximumSize(new Dimension(width + 100, instructorName
				.getPreferredSize().height + 10));
		date.setMaximumSize(new Dimension(150,
				date.getPreferredSize().height + 10));
		time.setMaximumSize(new Dimension(222,
				time.getPreferredSize().height + 10));

	}

}
