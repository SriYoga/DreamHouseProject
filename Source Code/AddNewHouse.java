package DreamHouse;
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
import java.util.Random;

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
		setBounds(100, 100, 769, 579);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(165, 21, 67, 14);
		contentPane.add(lblWelcome);

		JLabel label = new JLabel(user.FirstName + " " + user.LastName);
		label.setBounds(242, 21, 316, 14);
		contentPane.add(label);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(195, 169, 67, 14);
		contentPane.add(lblLocation);

		JLabel lblHouseType = new JLabel("House Type");
		lblHouseType.setBounds(195, 201, 87, 14);
		contentPane.add(lblHouseType);

		JLabel lblSquareFeet = new JLabel("Square Feet");
		lblSquareFeet.setBounds(195, 238, 87, 14);
		contentPane.add(lblSquareFeet);

		JLabel lblPriceRange = new JLabel("Price Range");
		lblPriceRange.setBounds(195, 279, 87, 14);
		contentPane.add(lblPriceRange);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(192, 341, 102, 14);
		contentPane.add(lblDescription);

		final JTextArea txtDescription = new JTextArea();
		txtDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(192, 366, 250, 84);
		contentPane.add(txtDescription);

		String[] locationStrings = { "Toronto", "Ottawa", "Hamilton",
				"Kingston", "Oshawa", "Montreal" };
		final JComboBox cmbLocation = new JComboBox(locationStrings);
		cmbLocation.setBounds(280, 166, 149, 20);
		contentPane.add(cmbLocation);

		String[] typeStrings = { "Apartment", "Condominiums",
				"Detached House", "Semi-detached House", "Townhouse",
				"Duplex/Triplex" };
		final JComboBox cmbType = new JComboBox(typeStrings);
		cmbType.setBounds(280, 199, 150, 20);
		contentPane.add(cmbType);

		final JTextField txtSqFeet = new JTextField();
		txtSqFeet.setBounds(279, 238, 150, 20);
		contentPane.add(txtSqFeet);

		final JTextField txtPrice = new JTextField();
		txtPrice.setBounds(278, 277, 150, 20);
		contentPane.add(txtPrice);

		JLabel lblBedRooms = new JLabel("Bed Rooms");
		lblBedRooms.setBounds(481, 169, 77, 14);
		contentPane.add(lblBedRooms);

		JLabel lblWashRooms = new JLabel("Wash Rooms");
		lblWashRooms.setBounds(481, 212, 77, 14);
		contentPane.add(lblWashRooms);

		JLabel lblGarage = new JLabel("Garage");
		lblGarage.setBounds(481, 248, 46, 14);
		contentPane.add(lblGarage);

		JLabel lblSelectPictureTo = new JLabel("Upload picture");
		lblSelectPictureTo.setBounds(481, 283, 87, 14);
		contentPane.add(lblSelectPictureTo);

		final JTextField txtBedRooms = new JTextField();
		txtBedRooms.setBounds(569, 166, 128, 20);
		contentPane.add(txtBedRooms);

		final JTextField txtWashRooms = new JTextField();
		txtWashRooms.setBounds(568, 209, 129, 20);
		contentPane.add(txtWashRooms);

		final JTextField txtGarage = new JTextField();
		txtGarage.setBounds(569, 245, 128, 20);
		contentPane.add(txtGarage);

		fc = new JFileChooser();

		btnUpload = new JButton("Upload");
		btnUpload.addActionListener(this);
		btnUpload.setBounds(569, 279, 128, 23);
		contentPane.add(btnUpload);

		lblImage = new JLabel("");
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImage.setBounds(475, 355, 232, 102);
		contentPane.add(lblImage);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Random rd=new Random();
					int homeId=rd.nextInt();
					connection = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:VTM", "sys as sysdba",
							"sysman123");
					PreparedStatement ps = connection
							.prepareStatement("insert into Home(homeid,location,type,sqfeet,price,description,bedrooms,washrooms,garage,image)"
									+ "values(?,?,?,?,?,?,?,?,?,?)");

					FileInputStream fin = new FileInputStream(fc.getSelectedFile().getPath());
					//System.out.println(fc.getSelectedFile().getPath());
					ps.setLong(1,homeId);
					ps.setString(2, cmbLocation.getSelectedItem().toString());
					ps.setString(3, cmbType.getSelectedItem().toString());
					ps.setString(4, txtSqFeet.getText().toString());
					ps.setString(5, txtPrice.getText().toString());
					ps.setString(6, txtDescription.getText());
					ps.setString(7, txtBedRooms.getText().toString());
					ps.setString(8, txtWashRooms.getText().toString());
					ps.setString(9, txtGarage.getText().toString());
					ps.setBinaryStream(10, fin, fin.available());
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
		btnInsert.setBounds(496, 489, 89, 23);
		contentPane.add(btnInsert);

		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setBounds(608, 489, 89, 23);
		contentPane.add(btnClose);

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(this);
		btnLogout.setBounds(618, 31, 89, 23);
		contentPane.add(btnLogout);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(AddNewHouse.class.getResource("/Resources/AddNewHouse1.jpg")));
		label_1.setBounds(0, 0, 753, 540);
		contentPane.add(label_1);
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
