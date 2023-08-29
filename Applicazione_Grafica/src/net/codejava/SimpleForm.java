package net.codejava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleForm extends JFrame {
	// attributi
	private JTextField nameField, emailField, ageField;
	private JCheckBox subscribeCheckBox;
	private JButton submitButton;

	// costruttore
	public SimpleForm() {
		// definizione della finestra dell'apllicativo
		setTitle("Iscrizione NewSletter");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// definizione del pannello interno alla finestra
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 10, 10));

		// creazione degli oggetti da inserire nel panel
		JLabel nameLabel = new JLabel("Name: ");
		nameField = new JTextField();

		JLabel emailLabel = new JLabel("Email: ");
		emailField = new JTextField();

		JLabel ageLabel = new JLabel("Age: ");
		ageField = new JTextField();

		JLabel subscribeLabel = new JLabel("Subscribe to newsletter: ");
		subscribeCheckBox = new JCheckBox();

		JLabel emptyLabel = new JLabel();

		// inseriamo gli oggetti creati nell'ordine in cui vogliamo vengano visualizzati
		// all'interno della finestra
		panel.add(nameLabel);
		panel.add(nameField);

		panel.add(emailLabel);
		panel.add(emailField);

		panel.add(ageLabel);
		panel.add(ageField);

		panel.add(subscribeLabel);
		panel.add(subscribeCheckBox);

		panel.add(emptyLabel);

		// gestione del bottone submit
		submitButton = new JButton("SubMit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String email = emailField.getText();
				// utilizziamo il metodo parse per trasformare un testo in un valore numerico
				int age = Integer.parseInt(ageField.getText());
				boolean subscribe = subscribeCheckBox.isSelected();

				System.out.println("Name: " + name);
				System.out.println("Email: " + email);
				System.out.println("Age: " + age);
				System.out.println("Subscribe to newSletter: " + subscribe);
			}
		});
		
		panel.add(submitButton);
		add(panel);
		setVisible(true);

	}
	public static void main(String[]args) {
		new SimpleForm();
	}

}
