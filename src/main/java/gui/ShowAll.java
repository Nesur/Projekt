package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import manager.AppointmentManager;

import sql.connection.Connect;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowAll extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField UsunIDtextField;
	private JTextField UsunKursantTextField;
	private Vector columnNames = new Vector();

	private Vector data = new Vector();
	JTable table;
	private Connect con = new Connect();
	private JPanel tablePanel = new JPanel();

	private AppointmentManager am = new AppointmentManager();

	public ShowAll() {
		table = new JTable(data, columnNames);

		setResizable(false);
		displayAll();
		setTitle("Wszystkie jazdy");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
		setBounds(100, 100, 577, 353);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUsun = new JLabel("Usu\u0144 przez id");
		lblUsun.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsun.setBounds(20, 202, 118, 23);
		contentPanel.add(lblUsun);

		JLabel lblUsunKursant = new JLabel("Usu\u0144 przez kursanta");
		lblUsunKursant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsunKursant.setBounds(20, 255, 131, 23);
		contentPanel.add(lblUsunKursant);

		UsunIDtextField = new JTextField();
		UsunIDtextField.setBounds(161, 204, 96, 23);
		contentPanel.add(UsunIDtextField);
		UsunIDtextField.setColumns(10);

		UsunKursantTextField = new JTextField();
		UsunKursantTextField.setBounds(161, 257, 96, 23);
		contentPanel.add(UsunKursantTextField);
		UsunKursantTextField.setColumns(10);

		JButton UsunIDbtnOk = new JButton("Usuñ");
		UsunIDbtnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteById();
			}
		});
		UsunIDbtnOk.setBounds(267, 204, 81, 23);
		contentPanel.add(UsunIDbtnOk);

		JButton UsunKursantbtnOk = new JButton("Usuñ");
		UsunKursantbtnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteByStudent();
			}
		});
		UsunKursantbtnOk.setBounds(267, 257, 81, 23);
		contentPanel.add(UsunKursantbtnOk);

		tablePanel = new JPanel();
		tablePanel.setBounds(10, 11, 534, 180);
		contentPanel.add(tablePanel);
		table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(520, 160));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		tablePanel.add(scrollPane);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ZamknijokButton = new JButton("Zamknij");
				ZamknijokButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				ZamknijokButton.setActionCommand("OK");
				buttonPane.add(ZamknijokButton);
				getRootPane().setDefaultButton(ZamknijokButton);
			}
		}
	}

	public void displayAll() {

		try {
			String sql = "Select * from Appointment";
			Statement stmt = con.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				columnNames.addElement(md.getColumnName(i));
			}
			while (rs.next()) {
				Vector row = new Vector(columns);
				for (int i = 1; i <= columns; i++) {
					row.addElement(rs.getObject(i));
				}
				data.addElement(row);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		TableColumn col;
		for (int i = 0; i < table.getColumnCount(); i++) {
			col = table.getColumnModel().getColumn(i);
			col.setMaxWidth(250);
		}

	}

	public void deleteById() {
		int toDelete = Integer.parseInt(UsunIDtextField.getText());
		am.deleteById(toDelete);

	}

	public void deleteByStudent() {
		am.deleteByStudent(UsunKursantTextField.getText());
	}

	public void refresh() {

	}
}
