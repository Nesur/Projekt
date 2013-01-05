package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Timer;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import data.Appointment;
import data.Instructor;
import data.Student;
import login.Login;
import manager.AppointmentManager;
import javax.swing.JButton;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginWindow window = new LoginWindow();
	private Login admin = new Login("admin", "1234");
	private ShowAll all = new ShowAll();
	private AppointmentManager am = new AppointmentManager();
	private JTextField student;
	private JTextField instructor;
	private JFormattedTextField date;
	private JFormattedTextField time;
	private JLabel confirmation;

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
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);

		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.show();
			}
		});
		mnSystem.add(mntmLogin);

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
				
					all.show();
				
			}
		});
		mntmAll.setToolTipText("Logged in only");
		Manage.add(mntmAll);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStudentName = new JLabel("Imi\u0119 i nazwisko kursanta");
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentName.setBounds(64, 50, 174, 18);
		contentPane.add(lblStudentName);

		student = new JTextField();
		student.setFont(new Font("Tahoma", Font.PLAIN, 13));
		student.setBounds(242, 48, 140, 20);
		contentPane.add(student);
		student.setColumns(10);

		JLabel lblInstructorName = new JLabel("Imi\u0119 i nazwisko instruktora");
		lblInstructorName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstructorName.setBounds(64, 86, 174, 18);
		contentPane.add(lblInstructorName);

		instructor = new JTextField();
		instructor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		instructor.setBounds(242, 84, 140, 20);
		contentPane.add(instructor);
		instructor.setColumns(10);

		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblData.setBounds(64, 124, 174, 18);
		contentPane.add(lblData);

		JLabel lblGodzina = new JLabel("Godzina");
		lblGodzina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGodzina.setBounds(64, 162, 174, 18);
		contentPane.add(lblGodzina);

		MaskFormatter dateMask = new MaskFormatter("##-##-####");
		date = new JFormattedTextField(dateMask);
		date.setFont(new Font("Tahoma", Font.PLAIN, 13));
		date.setBounds(242, 122, 140, 20);
		contentPane.add(date);

		MaskFormatter timeMask = new MaskFormatter("##:##");
		time = new JFormattedTextField(timeMask);
		time.setFont(new Font("Tahoma", Font.PLAIN, 13));
		time.setBounds(242, 160, 140, 20);
		contentPane.add(time);

		JButton btnZatwierd = new JButton("Zatwierd\u017A");
		btnZatwierd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (instructor.getText() != "" && student.getText() != "" && date.getText() != "" && time.getText() != "") {
					addAppointment();
				}

			}
		});
		btnZatwierd.setBounds(64, 205, 101, 23);
		contentPane.add(btnZatwierd);

		confirmation = new JLabel("");
		confirmation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmation.setBounds(64, 14, 174, 25);
		contentPane.add(confirmation);

	}

	public void addAppointment() {
	
		
		am.makeAppointment(new Appointment(new Instructor(instructor.getText()), new Student(student.getText()), date.getText(), time.getText()));
		clearTextFields();
		confirmation.setText("Dodano pomyœlnie!");
		
	}

	public void clearTextFields() {
		instructor.setText("");
		student.setText("");
		date.setText("");
		time.setText("");

	}
}
