package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends Main implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu manage;
	private JMenu info;
	private JMenuItem about;
	private JMenuItem exit;
	private JMenuItem login;
	private JMenuItem appointments;
	private JMenuItem delete;

	public MainMenu() {
		initMenu();
		setVisible(true);
	}

	public void initMenu() {
		// bar
		menuBar = new JMenuBar();
		// menus
		menu = new JMenu("Menu");
		info = new JMenu("Info");
		manage = new JMenu("Zarz¹dzaj");
		// add to menu bar
		menuBar.add(menu);
		menuBar.add(manage);
		menuBar.add(info);
		// menu items
		login = new JMenuItem("Login");
		login.setToolTipText("Tylko dla uprawinonych!");
		menu.add(login);
		menu.add(new JSeparator());
		exit = new JMenuItem("Zakoñcz");
		menu.add(exit);
		// manage items
		appointments = new JMenuItem("Wyœwietl");
		delete = new JMenuItem("Usuñ");
		appointments.setToolTipText("Tylko po zalogownaiu!");
		delete.setToolTipText("Tylko po zalogowaniu!");
		manage.add(appointments);
		manage.add(new JSeparator());
		manage.add(delete);
		// about items
		about = new JMenuItem("Informacje");
		info.add(about);

		this.setJMenuBar(menuBar);
		// actions listeners
		about.addActionListener(this);
		exit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == about) {
			JOptionPane.showMessageDialog(this, "Made by Piotr Wysocki",
					"Aplikacja umówiaj¹ca na spotkania", 1);
		}
		if (e.getSource() == exit) {

			System.exit(0);
		}

	}

}
