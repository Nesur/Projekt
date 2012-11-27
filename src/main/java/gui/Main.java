package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {
	// frame
	private static final long serialVersionUID = 1L;
	private static int width = 290;
	private static int height = 365;
	private static String title = "Umów siê na jazdê";
	private Container container;

	// text fields
	private JTextField studentName;
	private JTextField instructorName;
	private JTextField date;
	private JTextField time;
	// text field label
	private JLabel setStudentName;
	private JLabel setInstructorName;
	private JLabel setDate;
	private JLabel setTime;
	// button make appointment
	private JButton makeAppointment;

	public Main() {
		initForm();
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
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

		// text fields
		studentName = new JTextField();
		instructorName = new JTextField();
		date = new JTextField();
		time = new JTextField();
		// text field labels
		setStudentName = new JLabel("Podaj imie i nazwisko");
		setStudentName.setFont(new Font("Georgia", 0, 15));
		setInstructorName = new JLabel("Wybierz instruktora");
		setInstructorName.setFont(new Font("Georgia", 0, 15));
		setDate = new JLabel("Podaj date");
		setDate.setFont(new Font("Georgia", 0, 15));
		setTime = new JLabel("Podaj godzinê");
		setTime.setFont(new Font("Georgia", 0, 15));

		// button make
		makeAppointment = new JButton("Add");
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

	}

	public void setComponentsSize() {
		studentName.setMaximumSize(new Dimension(width + 100, studentName
				.getPreferredSize().height + 10));
		instructorName.setMaximumSize(new Dimension(width + 100, instructorName
				.getPreferredSize().height + 10));
		date.setMaximumSize(new Dimension(width + 100,
				date.getPreferredSize().height + 10));
		time.setMaximumSize(new Dimension(width + 100,
				time.getPreferredSize().height + 10));

	}

}
