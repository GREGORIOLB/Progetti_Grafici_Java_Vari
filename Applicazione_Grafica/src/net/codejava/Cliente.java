package net.codejava;

import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Cliente extends JFrame {
	// attributi SQL
	// connessione database
	private static final String url = "jdbc:mysql://localhost:3306/dbcentroestetico";
	private static final String username = "root";
	private static final String password = "#";

	// attributi per l'interfaccia grafica
	private JLabel lblNome, lblCognome, lblEmail, lblTelefono, lblCodiceFiscale, lblIndirizzo, lblPreferenzaOperatore,
			lblEventualiAllergie, lblId;
	private JTextField txtNome, txtCognome, txtEmail, txtTelefono, txtCodiceFiscale, txtIndirizzo,
			txtPreferenzaOperatore, txtEventualiAllergie, txtId;
	private JButton btnInserisci, btnAggiorna, btnVisualizza, btnRimuovi;
	private JTextArea textArea;

	public Cliente() {

		// inizializzazione componenti interfaccia
		lblNome = new JLabel(" Nome: ");
		lblCognome = new JLabel(" Cognome: ");
		lblEmail = new JLabel(" Email: ");
		lblTelefono = new JLabel(" Telefono: ");
		lblCodiceFiscale = new JLabel(" Codice Fiscale: ");
		lblIndirizzo = new JLabel(" Indirizzo: ");
		lblPreferenzaOperatore = new JLabel(" Preferenza operatore: ");
		lblEventualiAllergie = new JLabel(" Eventuali allergie: ");
		lblId = new JLabel(" Id: ");

		txtNome = new JTextField(15);
		txtCognome = new JTextField(15);
		txtEmail = new JTextField(15);
		txtTelefono = new JTextField(20);
		txtCodiceFiscale = new JTextField(16);
		txtIndirizzo = new JTextField(20);
		txtPreferenzaOperatore = new JTextField(20);
		txtEventualiAllergie = new JTextField(20);
		txtId = new JTextField(5);

		// inizializziamo i bottoni
		btnInserisci = new JButton("Inserisci");
		btnAggiorna = new JButton("Aggiorna");
		btnVisualizza = new JButton("Visualizza");
		btnRimuovi = new JButton("Rimuovi");

		// inizializzazione area di testo per visualizzare tutti i clienti
		textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);

		// aggiunta componenti all'interfaccia grafica
		Border blackline = BorderFactory.createLineBorder(Color.black);
		JPanel panelCampi = new JPanel(new GridLayout(10, 2));
		panelCampi.setBackground(Color.decode("#B0C4DE"));
		JPanel panelBottoni = new JPanel(new GridLayout(5, 1));
		panelBottoni.setBackground(Color.decode("#B0C4DE"));
		JPanel panelTextArea = new JPanel(new GridLayout(1, 2));
		panelCampi.setBorder(blackline);
		panelBottoni.setBorder(blackline);

		JFrame frame = new JFrame();
		// impostiamo le dimensioni e la posizione dell'interfaccia grafica
		// titolo interfaccia
		frame.setTitle("Cliente");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().setBackground(Color.decode("#B0C4DE"));

		frame.add(panelCampi, BorderLayout.WEST);
		frame.add(panelBottoni, BorderLayout.SOUTH);
		frame.add(panelTextArea, BorderLayout.CENTER);

		lblNome.setBorder(blackline);
		panelCampi.add(lblNome);
		panelCampi.add(txtNome);

		lblCognome.setBorder(blackline);
		panelCampi.add(lblCognome);
		panelCampi.add(txtCognome);

		lblEmail.setBorder(blackline);
		panelCampi.add(lblEmail);
		panelCampi.add(txtEmail);

		lblTelefono.setBorder(blackline);
		panelCampi.add(lblTelefono);
		panelCampi.add(txtTelefono);

		lblCodiceFiscale.setBorder(blackline);
		panelCampi.add(lblCodiceFiscale);
		panelCampi.add(txtCodiceFiscale);

		lblIndirizzo.setBorder(blackline);
		panelCampi.add(lblIndirizzo);
		panelCampi.add(txtIndirizzo);

		lblPreferenzaOperatore.setBorder(blackline);
		panelCampi.add(lblPreferenzaOperatore);
		panelCampi.add(txtPreferenzaOperatore);

		lblEventualiAllergie.setBorder(blackline);
		panelCampi.add(lblEventualiAllergie);
		panelCampi.add(txtEventualiAllergie);

		lblId.setBorder(blackline);
		panelCampi.add(lblId);
		panelCampi.add(txtId);

		panelTextArea.add(scrollPane);
		frame.add(panelTextArea);
		frame.setVisible(true);

		// Gestione del bottone inserisci con il proprio listener
		btnInserisci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// recupero i dati inseriti dall'utente
				String nome = txtNome.getText();
				String cognome = txtCognome.getText();
				String email = txtEmail.getText();
				String telefono = txtTelefono.getText();
				String codiceFiscale = txtCodiceFiscale.getText();
				// contiamo il numero di caratteri contenuti in una frase
				int numeriCaratteri = codiceFiscale.length();
				if (numeriCaratteri != 16) {
					JOptionPane.showMessageDialog(null, "Spiacente codice fiscale non valido!");
				}
				String indirizzo = txtIndirizzo.getText();
				String preferenzaOperatore = txtPreferenzaOperatore.getText();
				String eventualiAllergie = txtEventualiAllergie.getText();

				// apertura connessione al database ed inserimento del nuovo cliente
				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					conn = DriverManager.getConnection(url, username, password);
					String sql = "INSERT INTO cliente(nome, cognome, email, telefono, codiceFiscale, indirizzo, preferenzaOperatore, eventualiAllergie) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, nome);
					pstmt.setString(2, cognome);
					pstmt.setString(3, email);
					pstmt.setString(4, telefono);
					pstmt.setString(5, codiceFiscale);
					pstmt.setString(6, indirizzo);
					pstmt.setString(7, preferenzaOperatore);
					pstmt.setString(8, eventualiAllergie);

					int righeInsrite = pstmt.executeUpdate();
					// creiamo una finestra di pop up che ci da conferma se l'nserimento del record
					// e avvennuto
					if (righeInsrite > 0) {
						JOptionPane.showMessageDialog(null, "Cliente inserito con successo!");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					try {
						// chiusura connessioni del databse
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				// reset dei campi del form
				txtNome.setText("");
				txtCognome.setText("");
				txtEmail.setText("");
				txtTelefono.setText("");
				txtCodiceFiscale.setText("");
				txtIndirizzo.setText("");
				txtPreferenzaOperatore.setText("");
				txtEventualiAllergie.setText("");
				txtId.setText("");
			}
		});
		panelBottoni.add(btnInserisci);
		frame.setVisible(true);

		// gestione del bottone aggiorna
		btnAggiorna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// recupero i dati aggiornati dall'utente
				String nome = txtNome.getText();
				String cognome = txtCognome.getText();
				String email = txtEmail.getText();
				String telefono = txtTelefono.getText();
				String codiceFiscale = txtCodiceFiscale.getText();
				// contiamo il numero di caratteri contenuti in una frase
				int numeriCaratteri = codiceFiscale.length();
				if (numeriCaratteri != 16) {
					JOptionPane.showMessageDialog(null, "Spiacente codice fiscale non valido!");
				}
				String indirizzo = txtIndirizzo.getText();
				String preferenzaOperatore = txtPreferenzaOperatore.getText();
				String eventualiAllergie = txtEventualiAllergie.getText();
				int id = Integer.parseInt(txtId.getText());

				// apertura connessione al database ed inserimento del nuovo cliente
				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
					conn = DriverManager.getConnection(url, username, password);
					String sql = "UPDATE cliente SET nome = ?, cognome = ?, email = ?,telefono = ?, codiceFiscale = ?, indirizzo = ?, preferenzaOperatore = ?, eventualiAllergie = ? WHERE id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, nome);
					pstmt.setString(2, cognome);
					pstmt.setString(3, email);
					pstmt.setString(4, telefono);
					pstmt.setString(5, codiceFiscale);
					pstmt.setString(6, indirizzo);
					pstmt.setString(7, preferenzaOperatore);
					pstmt.setString(8, eventualiAllergie);
					pstmt.setInt(9, id);

					int righeInserite = pstmt.executeUpdate();
					if (righeInserite > 0) {
						JOptionPane.showMessageDialog(null, "Cliente aggiornato con successo!");
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				// reset dei campi del form
				txtNome.setText("");
				txtCognome.setText("");
				txtEmail.setText("");
				txtTelefono.setText("");
				txtCodiceFiscale.setText("");
				txtIndirizzo.setText("");
				txtPreferenzaOperatore.setText("");
				txtEventualiAllergie.setText("");
				txtId.setText("");
			}
		});
		panelBottoni.add(btnAggiorna);
		frame.setVisible(true);

		// Gestione del bottone visualizza con il proprio listener
		btnVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// apertura connessione al database ed inserimento del nuovo cliente
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				// reset del form precedentemente stampato
				textArea.setText("");
				try {
					conn = DriverManager.getConnection(url, username, password);
					String sql = "SELECT * FROM cliente";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					// scorriamo all'interno del result set per visualizzare tutti i record presenti
					while (rs.next()) {
						String nome = rs.getString("nome");
						String cognome = rs.getString("cognome");
						String email = rs.getString("email");
						String telefono = rs.getString("telefono");
						String codiceFiscale = rs.getString("codiceFiscale");
						String indirizzo = rs.getString("indirizzo");
						String preferenzaOperatore = rs.getString("preferenzaOperatore");
						String eventualiAllergie = rs.getString("eventualiAllergie");
						int id = rs.getInt("id");

						String ris = ("ID: " + id + "\nNome: " + nome + "\nCognome: " + cognome + "\nEmail: " + email
								+ "\nTelefono: " + telefono + "\ncodiceFiscale: " + codiceFiscale + "\nIndirizzo "
								+ indirizzo + "\nPreferenza Operatore: " + preferenzaOperatore
								+ "\nEventuali Allergie: " + eventualiAllergie);
						// aggiungiamo risultati alla textArea
						textArea.append(ris);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					try {
						// chiudiamo le connessioni se sono state aperte
						if (rs != null)
							rs.close();
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

				}
			}
		});
		panelBottoni.add(btnVisualizza);
		frame.setVisible(true);

		// gestione del bottone rimuovi
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtId.getText());

				Connection conn = null;
				PreparedStatement pstmt = null;
				try {
					conn = DriverManager.getConnection(url, username, password);
					String sql = "DELETE FROM cliente WHERE id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, id);

					int righeInserite = pstmt.executeUpdate();
					if (righeInserite > 0) {
						JOptionPane.showMessageDialog(null, "Cliente rimosso con successo!");
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (pstmt != null)
							pstmt.close();
						if (conn != null)
							conn.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				// reset campi del form
				txtId.setText("");
			}

		});
		panelBottoni.add(btnRimuovi);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Cliente();

	}
}
