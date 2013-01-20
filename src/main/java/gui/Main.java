package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.faces.model.ListDataModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import data.Appointment;
import data.Instructor;
import data.Student;

import manager.AppointmentManager;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginWindow window = new LoginWindow();

	private ShowAll all = new ShowAll();
	private AppointmentManager am = new AppointmentManager();
	private JTextField student;

	private JLabel confirmation;
	private String[] instructors = new String[5];
	private JComboBox instructor;
	private String[] hours = new String[10];
	private JComboBox time;
	private JDateChooser date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public Main() throws ParseException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\wysoc_000\\Desktop\\wheel.png"));
		setTitle("Um\u00F3w si\u0119 na jazde");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 282);
		setLocationRelativeTo(null);
		instructors[0] = "";
		instructors[1] = "Jan Kowalski";
		instructors[2] = "Tomek Mróz";
		instructors[3] = "Arek Pieczarek";

		hours[0] = "";
		hours[1] = "8:00 - 10:00";
		hours[2] = "10:00 - 12:00";
		hours[3] = "12:00 - 14:00";
		hours[4] = "14:00 - 16:00";
		hours[5] = "16:00 - 18:00";
		hours[6] = "18:00 - 20:00";

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnSystem.add(mntmExit);

		JMenu Manage = new JMenu("Manage");
		menuBar.add(Manage);

		JMenuItem mntmAll = new JMenuItem("Show all");
		mntmAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginWindow().show(true);
				confirmation.setText("");

			}
		});
		mntmAll.setToolTipText("Tylko dla zalogowanych!");
		Manage.add(mntmAll);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStudentName = new JLabel("Podaj imi\u0119 i nazwisko");
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentName.setBounds(10, 39, 174, 18);
		contentPane.add(lblStudentName);

		student = new JTextField();
		student.setFont(new Font("Tahoma", Font.PLAIN, 13));
		student.setBounds(188, 37, 140, 20);
		contentPane.add(student);
		student.setColumns(10);

		JLabel lblInstructorName = new JLabel("Wybierz instruktora");
		lblInstructorName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstructorName.setBounds(10, 75, 174, 18);
		contentPane.add(lblInstructorName);

		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setBounds(10, 113, 174, 18);
		contentPane.add(lblData);

		JLabel lblGodzina = new JLabel("Godzina");
		lblGodzina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGodzina.setBounds(10, 151, 174, 18);
		contentPane.add(lblGodzina);

		date = new JDateChooser();
		date.setBounds(188, 111, 140, 20);

		contentPane.add(date);

		JButton btnZatwierd = new JButton("Zatwierd\u017A");
		btnZatwierd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!((String) instructor.getSelectedItem()).equalsIgnoreCase("") && !student.getText().equalsIgnoreCase("") && !((JTextField) date.getDateEditor().getUiComponent()).getText().equalsIgnoreCase("") && !((String) time.getSelectedItem()).equalsIgnoreCase("")) {
					boolean alreadyIn = false;
					for (Appointment a : am.getAll()) {

						if (((String) instructor.getSelectedItem()).equalsIgnoreCase(a.getInstructor().getName()) && ((JTextField) date.getDateEditor().getUiComponent()).getText().equalsIgnoreCase(a.getDate()) && ((String) time.getSelectedItem()).equalsIgnoreCase(a.getTime())) {
							alreadyIn = true;
							confirmation.setText("Niestety takie dane ju¿ istniej¹!");
						} else
							alreadyIn = false;
					}
					if (!alreadyIn) {
						addAppointment();
					}
				}

				else {
					confirmation.setText("Podaj wszystkie dane!");
				}
			}
		});
		btnZatwierd.setBounds(10, 194, 101, 23);
		contentPane.add(btnZatwierd);

		confirmation = new JLabel("");
		confirmation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmation.setBounds(10, 11, 318, 25);
		contentPane.add(confirmation);

		instructor = new JComboBox<Object>();
		instructor.setBounds(188, 76, 140, 20);
		instructor.addItem(instructors[0]);
		instructor.addItem(instructors[1]);
		instructor.addItem(instructors[2]);
		instructor.addItem(instructors[3]);
		contentPane.add(instructor);

		time = new JComboBox<Object>();
		time.setBounds(188, 152, 140, 20);
		time.addItem(hours[0]);
		time.addItem(hours[1]);
		time.addItem(hours[2]);
		time.addItem(hours[3]);
		time.addItem(hours[4]);
		time.addItem(hours[5]);
		time.addItem(hours[6]);

		contentPane.add(time);

	}

	public void addAppointment() {

		am.makeAppointment(new Appointment(new Instructor((String) instructor.getSelectedItem()), new Student(student.getText()), ((JTextField) date.getDateEditor().getUiComponent()).getText(), (String) time.getSelectedItem()));
		clearTextFields();
		confirmation.setText("Dodano pomyœlnie!");

	}

	public void clearTextFields() {
		instructor.setSelectedIndex(0);
		time.setSelectedIndex(0);
		student.setText("");
		((JTextField) date.getDateEditor().getUiComponent()).setText("");

	}
}
