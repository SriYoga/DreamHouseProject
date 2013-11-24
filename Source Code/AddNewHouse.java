package dh;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class AddNewHouse extends JFrame implements ActionListener {

	private JPanel contentPane;
	JFileChooser fc;
	JLabel lblImage;
	Connection connection = null;
	JButton btnClose;
	JButton btnLogout;
	JButton btnUpload;
	/**
	 * Create the frame.
	 */
	public AddNewHouse(UserDetail user) {
		setTitle("Home - Add new house");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 420);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(36, 11, 67, 14);
		contentPane.add(lblWelcome);

		JLabel label = new JLabel(user.FirstName + " " + user.LastName);
		label.setBounds(113, 11, 316, 14);
		contentPane.add(label);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(36, 51, 67, 14);
		contentPane.add(lblLocation);

		JLabel lblHouseType = new JLabel("House Type");
		lblHouseType.setBounds(36, 93, 87, 14);
		contentPane.add(lblHouseType);

		JLabel lblSquareFeet = new JLabel("Square Feet");
		lblSquareFeet.setBounds(36, 134, 87, 14);
		contentPane.add(lblSquareFeet);

		JLabel lblPriceRange = new JLabel("Price Range");
		lblPriceRange.setBounds(36, 178, 87, 14);
		contentPane.add(lblPriceRange);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(36, 219, 102, 14);
		contentPane.add(lblDescription);

		final JTextArea txtDescription = new JTextArea();
		txtDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(36, 244, 227, 77);
		contentPane.add(txtDescription);

		String[] locationStrings = { "Toronto","Missisauga", "Ottawa", "Hamilton",
				"Kingston", "Oshawa", "Montreal" };
		final JComboBox cmbLocation = new JComboBox(locationStrings);
		cmbLocation.setBounds(133, 51, 149, 20);
		contentPane.add(cmbLocation);

		String[] typeStrings = { "Appartment", "Condominiums",
				"Detached House", "Semi-detached House", "Townhouse",
				"Duplex/Triplex" };
		final JComboBox cmbType = new JComboBox(typeStrings);
		cmbType.setBounds(133, 93, 150, 20);
		contentPane.add(cmbType);

		final JTextField txtSqFeet = new JTextField();
		txtSqFeet.setBounds(133, 134, 150, 20);
		contentPane.add(txtSqFeet);

		final JTextField txtPrice = new JTextField();
		txtPrice.setBounds(133, 178, 150, 20);
		contentPane.add(txtPrice);

		JLabel lblBedRooms = new JLabel("Bed Rooms");
		lblBedRooms.setBounds(313, 51, 77, 14);
		contentPane.add(lblBedRooms);

		JLabel lblWashRooms = new JLabel("Wash Rooms");
		lblWashRooms.setBounds(313, 93, 77, 14);
		contentPane.add(lblWashRooms);

		JLabel lblGarage = new JLabel("Garage");
		lblGarage.setBounds(313, 134, 46, 14);
		contentPane.add(lblGarage);

		JLabel lblSelectPictureTo = new JLabel("Upload picture");
		lblSelectPictureTo.setBounds(313, 178, 87, 14);
		contentPane.add(lblSelectPictureTo);

		final JTextField txtBedRooms = new JTextField();
		txtBedRooms.setBounds(401, 48, 128, 20);
		contentPane.add(txtBedRooms);

		final JTextField txtWashRooms = new JTextField();
		txtWashRooms.setBounds(400, 90, 129, 20);
		contentPane.add(txtWashRooms);

		final JTextField txtGarage = new JTextField();
		txtGarage.setBounds(401, 131, 128, 20);
		contentPane.add(txtGarage);

		fc = new JFileChooser();

		btnUpload = new JButton("Upload");
		btnUpload.addActionListener(this);
		btnUpload.setBounds(401, 174, 128, 23);
		contentPane.add(btnUpload);

		lblImage = new JLabel("");
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImage.setBounds(313, 219, 232, 102);
		contentPane.add(lblImage);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:orcl", "sysman",
							"sysman123");
					PreparedStatement ps = connection
							.prepareStatement("insert into Home(location,type,sqfeet,price,description,bedrooms,washrooms,garage,image)"
									+ "values(?,?,?,?,?,?,?,?,?)");

					FileInputStream fin = new FileInputStream(fc
							.getSelectedFile().getPath());
					//System.out.println(fc.getSelectedFile().getPath());
					ps.setString(1, cmbLocation.getSelectedItem().toString());
					ps.setString(2, cmbType.getSelectedItem().toString());
					ps.setString(3, txtSqFeet.getText().toString());
					ps.setString(4, txtPrice.getText().toString());
					ps.setString(5, txtDescription.getText());
					ps.setString(6, txtBedRooms.getText().toString());
					ps.setString(7, txtWashRooms.getText().toString());
					ps.setString(8, txtGarage.getText().toString());
					ps.setBinaryStream(9, fin, fin.available());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null,
							"Home details successfully inserted",
							"Information Message",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException | IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(344, 347, 89, 23);
		contentPane.add(btnInsert);

		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setBounds(456, 347, 89, 23);
		contentPane.add(btnClose);

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(this);
		btnLogout.setBounds(456, 7, 89, 23);
		contentPane.add(btnLogout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnUpload) {
			int returnValue = fc.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				ImageIcon icon = new ImageIcon(fc.getSelectedFile().getPath());
				Image img = null;
				try {
					img = ImageIO.read(fc.getSelectedFile());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Image resizedImage = img.getScaledInstance(lblImage.getWidth(),
						lblImage.getHeight(), 0);
				lblImage.setIcon(new ImageIcon(resizedImage));
			}
		}
		if (e.getSource() == btnClose) {
			this.setVisible(false);
		}
		if (e.getSource() == btnLogout) {
			System.gc();
			for (Window window : Window.getWindows()) {
				window.dispose();
			}
			
			Login log= new Login();
			log.show();
		}
	}
}
