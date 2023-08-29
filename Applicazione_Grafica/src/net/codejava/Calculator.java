package net.codejava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
	// attributi
	private JTextField num1Field, num2Field, resultField;
	private JButton addButton, subtractButton, multiplyButton, divideButton;

	// costruttore
	public Calculator() {
		// definiamo la finestra che verra visualizzata a schermo
		// definiamo il titolo della finestra
		setTitle("Calcolatrice BitCamp");
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
		JLabel num1Label = new JLabel("Number1: ");
		num1Field = new JTextField();

		JLabel num2Label = new JLabel("Number2: ");
		num2Field = new JTextField();

		JLabel resultLabel = new JLabel("Result: ");
		resultField = new JTextField();

		// aggiungiamo gli oggetti appena creatii al pannello
		panel.add(num1Label);
		panel.add(num1Field);

		panel.add(num2Label);
		panel.add(num2Field);

		panel.add(resultLabel);
		panel.add(resultField);

		// gestione del button dell' addizione
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(num1Field.getText());
				double num2 = Double.parseDouble(num2Field.getText());
				double result = num1 + num2;
				resultField.setText(String.format("%.2f", result));
			}
		});

		// gestione del button della sottrazione
		subtractButton = new JButton("Subtract");
		subtractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(num1Field.getText());
				double num2 = Double.parseDouble(num2Field.getText());
				double result = num1 - num2;
				resultField.setText(String.format("%.2f", result));
			}
		});

		// gestione del button della moltiplicazione
		multiplyButton = new JButton("Multiply");
		multiplyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(num1Field.getText());
				double num2 = Double.parseDouble(num2Field.getText());
				double result = num1 * num2;
				resultField.setText(String.format("%.2f", result));
			}
		});

		// gestione del button della divisione
		divideButton = new JButton("Divide");
		divideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(num1Field.getText());
				double num2 = Double.parseDouble(num2Field.getText());
				if (num2 == 0) {
					resultField.setText("Error: cannot divide by zero");
				} else {
					double result = num1 / num2;
					resultField.setText(String.format("%.2f", result));
				}
			}
		});

		panel.add(addButton);
		panel.add(subtractButton);
		panel.add(multiplyButton);
		panel.add(divideButton);

		add(panel);
		setVisible(true);
	}
	
	public static void main(String[]args) {
		new Calculator();
	}
}
