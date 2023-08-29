package net.codejava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormApp extends JFrame {
	// attributi
	private JTextField nomeField, cognomeField, emailField, telefonoField;
	private JButton inviaButton;
	private JLabel messaggioLabel;

	// costruttore
	public FormApp() {
		// definiamo la finestra che verra visualizzata a schermo
		// definiamo il titolo della finestra
		setTitle("Semplice form per inserimento dati");
		// definiamo la dimendione della finestra
		setSize(400, 250);
		// definiamo il tipo di finestra e la ua chiusura
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// definiamo la posizione relativa della finestra all'interno dello schermo
		setLocationRelativeTo(null);

		// definiamo il pannello della nostra finestra
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 10, 10));

		// andiamo a creare gli oggetti che popoleranno il pannello
		JLabel nomeLabel = new JLabel("Nome: ");
		nomeField = new JTextField();

		JLabel cognomeLabel = new JLabel("Cognome: ");
		cognomeField = new JTextField();

		JLabel emailLabel = new JLabel("Email: ");
		emailField = new JTextField();

		JLabel telefonoLabel = new JLabel("Telefono: ");
		telefonoField = new JTextField();

		// aggiungiamo gli oggetti appena creatii al pannello
		panel.add(nomeLabel);
		panel.add(nomeField);

		panel.add(cognomeLabel);
		panel.add(cognomeField);

		panel.add(emailLabel);
		panel.add(emailField);

		panel.add(telefonoLabel);
		panel.add(telefonoField);

		// gestione del button
		inviaButton = new JButton("Invia");
		inviaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = nomeField.getText();
				String cognome = cognomeField.getText();
				String email = emailField.getText();
				String telefono = telefonoField.getText();

				// creiamo una stringa di testpo che conterra tutti i valori prelevati dal fild
				String messaggio = "Grazie per aver mandato i dati:\nNome: " + nome + "\nCognome: " + cognome
						+ "\nEmail: " + email + "\nTelefono: " + telefono;
				messaggioLabel.setText(messaggio);
			}
		}
					);
		messaggioLabel = new JLabel();
		
		panel.add(inviaButton);
		panel.add(messaggioLabel);
		
		//aggiungiamo il pannello alla finestra 
		add(panel);
		setVisible(true);

	}
	public static void main(String[]args) {
		new FormApp();
	}
}
