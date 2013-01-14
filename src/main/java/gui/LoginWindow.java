package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sql.connection.Connect;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import login.Login;

public class LoginWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userNametextField;
	private Connect con = new Connect();
	private Login admin = new Login();
	private JPasswordField passwordField;

	public LoginWindow() {
		setResizable(false);

		setTitle("Zaloguj si\u0119");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(false);
		setBounds(100, 100, 234, 232);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nazwa u\u017Cytkownika");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(48, 24, 119, 26);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblHas這 = new JLabel("Has\u0142o");
			lblHas這.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblHas這.setBounds(88, 80, 46, 26);
			contentPanel.add(lblHas這);
		}

		userNametextField = new JTextField();
		userNametextField.setBounds(48, 49, 119, 20);
		contentPanel.add(userNametextField);
		userNametextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(48, 125, 119, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();

						if (userNametextField.getText().equalsIgnoreCase(admin.getUsername()) && passwordField.getText().equalsIgnoreCase(admin.getPassword())) {

							new ShowAll().show();

						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Anuluj");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void getAllUsers() {

	}
}
